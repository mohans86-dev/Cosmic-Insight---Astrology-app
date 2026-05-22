package com.example.astrologyapp.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.astrologyapp.AstrologyApp
import com.example.astrologyapp.R
import com.example.astrologyapp.data.model.BirthChart
import com.example.astrologyapp.databinding.FragmentChartResultBinding
import com.example.astrologyapp.databinding.ItemPlanetRowBinding
import com.example.astrologyapp.util.DateTimeUtils

class ChartResultFragment : Fragment() {

    private var _binding: FragmentChartResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChartResultViewModel by viewModels {
        val app = requireActivity().application as AstrologyApp
        ChartResultViewModel.Factory(app.repository)
    }

    private lateinit var chart: BirthChart
    private lateinit var chartJson: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chartJson = arguments?.getString("chartJson") ?: ""
        if (chartJson.isEmpty()) {
            findNavController().navigateUp()
            return
        }
        val app = requireActivity().application as AstrologyApp
        chart = app.repository.chartFromJson(chartJson)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChartResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        // Setup Toolbar
        binding.toolbar.title = getString(R.string.chart_title)
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // Summary Info
        binding.tvName.text = chart.birthDetails.name
        binding.tvBirthInfo.text = "${DateTimeUtils.formatDate(chart.birthDetails.dateOfBirth)} at ${DateTimeUtils.formatTime(chart.birthDetails.timeOfBirth)}\n${chart.birthDetails.placeOfBirth}"

        // Ascendant
        binding.tvAscendant.text = chart.ascendant.displayName
        binding.tvAscendantDegree.text = String.format("%.1f°", chart.ascendantDegree)

        // Setup Chart View
        binding.kundliChartView.setChart(chart)
        binding.kundliChartView.setOnSignClickListener { sign ->
            val bundle = Bundle().apply {
                putString("signName", sign.name)
            }
            findNavController().navigate(R.id.action_chartResult_to_signProfile, bundle)
        }

        // Populate Planet Table
        populatePlanetTable()

        // Populate Dasha
        if (chart.dashas.isNotEmpty()) {
            val currentDasha = chart.dashas.first() // simplified, getting first logic
            binding.tvMahadasha.text = "${currentDasha.planet.displayName} Mahadasha"
            binding.tvAntardasha.text = "Ends on: ${DateTimeUtils.formatDate(currentDasha.endDate)}"
        }

        binding.sectionDasha.setOnClickListener {
            val bundle = Bundle().apply {
                putString("chartJson", chartJson)
            }
            findNavController().navigate(R.id.action_chartResult_to_dashaTimeline, bundle)
        }

        binding.fabSave.setOnClickListener {
            viewModel.saveChart(chart) {
                Toast.makeText(requireContext(), getString(R.string.chart_saved_success), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populatePlanetTable() {
        binding.tablePlanets.removeAllViews()
        
        // Add header row manually or assume it's in xml.
        // For simplicity, we just add items directly.

        for (position in chart.planets) {
            val rowBinding = ItemPlanetRowBinding.inflate(layoutInflater, binding.tablePlanets, true)
            
            rowBinding.tvPlanet.text = position.planet.displayName
            rowBinding.tvSign.text = position.sign.displayName
            rowBinding.tvDegree.text = String.format("%.1f°", position.degree)
            rowBinding.tvHouse.text = position.house.toString()
            rowBinding.tvNakshatra.text = position.nakshatra.displayName
            rowBinding.tvRetrograde.text = if (position.isRetrograde) "R" else ""
            
            rowBinding.root.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("planetName", position.planet.name)
                    putString("chartJson", chartJson)
                }
                findNavController().navigate(R.id.action_chartResult_to_planetDetail, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
