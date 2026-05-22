package com.example.astrologyapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.astrologyapp.AstrologyApp
import com.example.astrologyapp.data.model.Planet
import com.example.astrologyapp.data.model.PlanetInterpretation
import com.example.astrologyapp.databinding.FragmentPlanetDetailBinding

class PlanetDetailFragment : Fragment() {

    private var _binding: FragmentPlanetDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val planetName = arguments?.getString("planetName") ?: return
        val chartJson = arguments?.getString("chartJson") ?: return
        
        val app = requireActivity().application as AstrologyApp
        val chart = app.repository.chartFromJson(chartJson)
        val planetEnum = Planet.valueOf(planetName)
        
        val position = chart.planets.find { it.planet == planetEnum } ?: return
        
        binding.toolbar.title = "${planetEnum.displayName} Detail"
        binding.tvPlanetName.text = planetEnum.displayName
        binding.tvPlanetSign.text = "in ${position.sign.displayName}"
        
        binding.tvDegree.text = String.format("%.1f°", position.degree)
        binding.tvHouse.text = position.house.toString()
        binding.tvNakshatra.text = position.nakshatra.displayName
        binding.tvRetrograde.text = if (position.isRetrograde) "Retrograde ℞" else "Direct"
        
        val interpretation = PlanetInterpretation.getInterpretation(planetEnum, position.sign)
        binding.tvInterpretation.text = interpretation
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
