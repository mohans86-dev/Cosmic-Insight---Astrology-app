package com.example.astrologyapp.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologyapp.data.local.ChartEntity
import com.example.astrologyapp.databinding.ItemSavedChartBinding

class SavedChartsAdapter(
    private val onItemClick: (ChartEntity) -> Unit,
    private val onDeleteClick: (ChartEntity) -> Unit
) : ListAdapter<ChartEntity, SavedChartsAdapter.ChartViewHolder>(ChartDiffCallback()) {

    class ChartViewHolder(val binding: ItemSavedChartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val binding = ItemSavedChartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvName.text = item.name
        holder.binding.tvBirthDate.text = item.dateOfBirth
        holder.binding.tvPlace.text = item.placeOfBirth
        
        holder.binding.root.setOnClickListener { onItemClick(item) }
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(item) }
    }

    class ChartDiffCallback : DiffUtil.ItemCallback<ChartEntity>() {
        override fun areItemsTheSame(oldItem: ChartEntity, newItem: ChartEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChartEntity, newItem: ChartEntity): Boolean {
            return oldItem == newItem
        }
    }
}
