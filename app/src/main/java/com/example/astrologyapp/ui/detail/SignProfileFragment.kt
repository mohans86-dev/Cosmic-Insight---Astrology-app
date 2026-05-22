package com.example.astrologyapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.astrologyapp.data.model.ZodiacSign
import com.example.astrologyapp.data.model.ZodiacSignInfo
import com.example.astrologyapp.databinding.FragmentSignProfileBinding

class SignProfileFragment : Fragment() {

    private var _binding: FragmentSignProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val signName = arguments?.getString("signName") ?: return
        val sign = ZodiacSign.valueOf(signName)
        
        binding.toolbar.title = sign.displayName
        binding.tvSignName.text = sign.displayName
        
        binding.tvElement.text = sign.element.displayName
        binding.tvQuality.text = sign.quality.displayName
        binding.tvRuler.text = sign.rulingPlanet.displayName
        binding.tvLuckyColor.text = ZodiacSignInfo.getLuckyColor(sign)
        binding.tvLuckyNumbers.text = ZodiacSignInfo.getLuckyNumbers(sign).joinToString(", ")
        
        binding.tvPersonality.text = ZodiacSignInfo.getPersonality(sign)
        binding.tvStrengths.text = ZodiacSignInfo.getStrengths(sign).joinToString("\n• ", prefix = "• ")
        binding.tvWeaknesses.text = ZodiacSignInfo.getWeaknesses(sign).joinToString("\n• ", prefix = "• ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
