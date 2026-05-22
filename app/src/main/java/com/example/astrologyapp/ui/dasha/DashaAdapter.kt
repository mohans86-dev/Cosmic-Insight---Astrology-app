package com.example.astrologyapp.ui.dasha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologyapp.data.model.DashaPeriod
import com.example.astrologyapp.databinding.ItemDashaPeriodBinding
import com.example.astrologyapp.util.DateTimeUtils
import java.time.LocalDate

class DashaAdapter(private val periods: List<DashaPeriod>) : 
    RecyclerView.Adapter<DashaAdapter.DashaViewHolder>() {

    class DashaViewHolder(val binding: ItemDashaPeriodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashaViewHolder {
        val binding = ItemDashaPeriodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashaViewHolder, position: Int) {
        val period = periods[position]
        val binding = holder.binding
        
        binding.tvPlanetName.text = "${period.planet.displayName} Mahadasha"
        binding.tvDateRange.text = "${DateTimeUtils.formatDate(period.startDate)} - ${DateTimeUtils.formatDate(period.endDate)}"
        
        val now = LocalDate.now()
        val isCurrent = !now.isBefore(period.startDate) && !now.isAfter(period.endDate)
        
        if (isCurrent) {
            binding.progressBar.visibility = View.VISIBLE
            val totalDays = java.time.temporal.ChronoUnit.DAYS.between(period.startDate, period.endDate)
            val daysPassed = java.time.temporal.ChronoUnit.DAYS.between(period.startDate, now)
            val progress = ((daysPassed.toFloat() / totalDays.toFloat()) * 100).toInt()
            binding.progressBar.progress = progress
        } else {
            binding.progressBar.visibility = View.GONE
        }
        
        // Hide connector line for the last item
        binding.viewConnector.visibility = if (position == periods.size - 1) View.INVISIBLE else View.VISIBLE
    }

    override fun getItemCount() = periods.size
}
