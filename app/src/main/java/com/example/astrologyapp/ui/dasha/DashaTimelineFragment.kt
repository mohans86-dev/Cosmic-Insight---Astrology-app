package com.example.astrologyapp.ui.dasha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrologyapp.AstrologyApp
import com.example.astrologyapp.databinding.FragmentDashaTimelineBinding
import com.example.astrologyapp.util.DateTimeUtils

class DashaTimelineFragment : Fragment() {

    private var _binding: FragmentDashaTimelineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashaTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.toolbar.title = "Dasha Timeline"
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val chartJson = arguments?.getString("chartJson") ?: return
        val app = requireActivity().application as AstrologyApp
        val chart = app.repository.chartFromJson(chartJson)
        
        if (chart.dashas.isNotEmpty()) {
            val currentDasha = chart.dashas.first()
            binding.tvCurrentMahadasha.text = currentDasha.planet.displayName
            binding.tvCurrentAntardasha.text = "${DateTimeUtils.formatDate(currentDasha.startDate)} - ${DateTimeUtils.formatDate(currentDasha.endDate)}"
            
            val adapter = DashaAdapter(chart.dashas)
            binding.rvDashaTimeline.layoutManager = LinearLayoutManager(requireContext())
            binding.rvDashaTimeline.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
