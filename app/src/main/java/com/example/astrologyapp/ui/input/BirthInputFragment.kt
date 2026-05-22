package com.example.astrologyapp.ui.input

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.astrologyapp.AstrologyApp
import com.example.astrologyapp.R
import com.example.astrologyapp.data.local.CityData
import com.example.astrologyapp.data.local.CityInfo
import com.example.astrologyapp.data.model.BirthDetails
import com.example.astrologyapp.data.model.Gender
import com.example.astrologyapp.databinding.FragmentBirthInputBinding
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BirthInputFragment : Fragment() {

    private var _binding: FragmentBirthInputBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BirthInputViewModel by viewModels {
        BirthInputViewModel.Factory(com.example.astrologyapp.data.local.AstrologyCalculator())
    }

    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var selectedCity: CityInfo? = null
    private var isTimeUnknown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBirthInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        
        setupInputs()
        setupPlaceAutocomplete()
    }

    private fun setupInputs() {
        binding.etDateOfBirth.setOnClickListener {
            val now = LocalDate.now()
            val date = selectedDate ?: now
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    binding.etDateOfBirth.setText(selectedDate!!.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")))
                    binding.tilDate.error = null
                },
                date.year, date.monthValue - 1, date.dayOfMonth
            ).show()
        }

        binding.etTimeOfBirth.setOnClickListener {
            if (isTimeUnknown) return@setOnClickListener
            val time = selectedTime ?: LocalTime.NOON
            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    selectedTime = LocalTime.of(hourOfDay, minute)
                    binding.etTimeOfBirth.setText(selectedTime!!.format(DateTimeFormatter.ofPattern("hh:mm a")))
                    binding.tilTime.error = null
                },
                time.hour, time.minute, false
            ).show()
        }

        binding.cbTimeUnknown.setOnCheckedChangeListener { _, isChecked ->
            isTimeUnknown = isChecked
            binding.etTimeOfBirth.isEnabled = !isChecked
            if (isChecked) {
                binding.etTimeOfBirth.setText(R.string.input_time_warning)
                selectedTime = LocalTime.NOON
                binding.tilTime.error = null
            } else {
                binding.etTimeOfBirth.setText(selectedTime?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "")
            }
        }

        binding.btnGenerate.setOnClickListener {
            if (validateInput()) {
                generateChart()
            }
        }
    }

    private fun setupPlaceAutocomplete() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            CityData.cities.map { it.displayName }
        )
        binding.actvPlace.setAdapter(adapter)

        binding.actvPlace.setOnItemClickListener { _, _, position, _ ->
            val selection = adapter.getItem(position)
            selectedCity = CityData.cities.find { it.displayName == selection }
            binding.tilPlace.error = null
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true
        
        if (binding.etName.text.isNullOrBlank()) {
            binding.tilName.error = getString(R.string.error_name_required)
            isValid = false
        } else {
            binding.tilName.error = null
        }
        
        if (selectedDate == null) {
            binding.tilDate.error = getString(R.string.error_dob_required)
            isValid = false
        } else if (selectedDate!!.isAfter(LocalDate.now())) {
            binding.tilDate.error = getString(R.string.error_future_date)
            isValid = false
        }
        
        if (!isTimeUnknown && selectedTime == null) {
            binding.tilTime.error = "Time of birth is required"
            isValid = false
        }
        
        if (selectedCity == null && binding.actvPlace.text.isNotBlank()) {
            // Fallback: try to find city that matches the text
            selectedCity = CityData.cities.find { it.displayName.equals(binding.actvPlace.text.toString(), ignoreCase = true) }
        }
        
        if (selectedCity == null) {
            binding.tilPlace.error = getString(R.string.error_place_required)
            isValid = false
        }

        return isValid
    }

    private fun generateChart() {
        val name = binding.etName.text.toString().trim()
        
        val gender = when (binding.rgGender.checkedRadioButtonId) {
            R.id.rbMale -> Gender.MALE
            R.id.rbFemale -> Gender.FEMALE
            R.id.rbOther -> Gender.OTHER
            else -> null
        }
        
        val details = BirthDetails(
            name = name,
            dateOfBirth = selectedDate!!,
            timeOfBirth = selectedTime ?: LocalTime.NOON,
            placeOfBirth = selectedCity!!.displayName,
            latitude = selectedCity!!.latitude,
            longitude = selectedCity!!.longitude,
            timezone = selectedCity!!.timezone,
            gender = gender
        )

        lifecycleScope.launch {
            val chart = viewModel.generateChart(details)
            val app = requireActivity().application as AstrologyApp
            val chartJson = app.repository.chartToJson(chart)
            
            val bundle = Bundle().apply {
                putString("chartJson", chartJson)
            }
            findNavController().navigate(R.id.action_birthInput_to_chartResult, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
