package com.example.astrologyapp.ui.saved

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrologyapp.AstrologyApp
import com.example.astrologyapp.R
import com.example.astrologyapp.databinding.FragmentSavedChartsBinding
import kotlinx.coroutines.launch

class SavedChartsFragment : Fragment() {

    private var _binding: FragmentSavedChartsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedChartsViewModel by viewModels {
        val app = requireActivity().application as AstrologyApp
        SavedChartsViewModel.Factory(app.repository)
    }

    private lateinit var adapter: SavedChartsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedChartsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.saved_title)
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.savedCharts.collect { charts ->
                adapter.submitList(charts)
                if (charts.isEmpty()) {
                    binding.rvSavedCharts.visibility = View.GONE
                    binding.layoutEmpty.visibility = View.VISIBLE
                } else {
                    binding.rvSavedCharts.visibility = View.VISIBLE
                    binding.layoutEmpty.visibility = View.GONE
                }
            }
        }

        binding.fabAddChart.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(R.id.action_home_to_birthInput)
        }
    }

    private fun setupRecyclerView() {
        adapter = SavedChartsAdapter(
            onItemClick = { chartEntity ->
                val bundle = Bundle().apply {
                    putString("chartJson", chartEntity.chartJson)
                }
                findNavController().navigate(R.id.action_savedCharts_to_chartResult, bundle)
            },
            onDeleteClick = { chartEntity ->
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.saved_delete_confirm)
                    .setMessage(R.string.saved_delete_message)
                    .setPositiveButton(R.string.action_delete) { _, _ ->
                        viewModel.deleteChart(chartEntity.id)
                    }
                    .setNegativeButton(R.string.action_cancel, null)
                    .show()
            }
        )
        binding.rvSavedCharts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSavedCharts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
