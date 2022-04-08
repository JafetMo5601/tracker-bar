package com.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.databinding.ReservationListItemBinding
import com.finalproject.model.Reservation

class ReservationAdapter: RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    private var listReservations = emptyList<Reservation>()

    inner class ReservationViewHolder(private val itemBinding: ReservationListItemBinding):
            RecyclerView.ViewHolder(itemBinding.root) {
                fun bind(reservation: Reservation) {
                    itemBinding.tvReservationBarName.text = reservation.barName
                    itemBinding.tvReservationDetail.text = reservation.detail
                    itemBinding.tvReservationDate.text = reservation.date.toString()

//                    itemBinding.reservationItemView.setOnCliickListener {}
                }
            }

    fun deleteItem(i: Int) {
        listReservations.drop(i)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val itemBinding = ReservationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val currentReservation = listReservations[position]
        holder.bind(currentReservation)
    }

    override fun getItemCount(): Int {
        return listReservations.size
    }

    fun setData(reservations: List<Reservation>) {
        this.listReservations = reservations
        notifyDataSetChanged()
    }
}