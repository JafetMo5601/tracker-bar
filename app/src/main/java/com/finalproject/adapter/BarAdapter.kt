package com.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.databinding.BarListItemBinding
import com.finalproject.model.Bar

class BarAdapter: RecyclerView.Adapter<BarAdapter.BarViewHolder>() {

    private var listBares = emptyList<Bar>()

    inner class BarViewHolder(private val itemBinding: BarListItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(bar: Bar) {
            itemBinding.tvReservationBarName.text = bar.name
            itemBinding.tvAddress.text = bar.direction
            if (bar.status) {
                itemBinding.tvReservationDate.text = "Open"
            } else {
                itemBinding.tvReservationDate.text = "Closed"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        val itemBinding = BarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BarViewHolder, position: Int) {
        val currentBar = listBares[position]
        holder.bind(currentBar)
    }

    override fun getItemCount(): Int {
        return listBares.size
    }

    fun setData(bares: List<Bar>) {
        this.listBares = bares
        notifyDataSetChanged()
    }
}