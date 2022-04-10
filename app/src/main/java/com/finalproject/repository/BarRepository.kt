package com.finalproject.repository

import androidx.lifecycle.MutableLiveData
import com.finalproject.data.ReservationDAO
import com.finalproject.model.Reservation

class ReservationRepository(private val reservationDAO: ReservationDAO) {
    val getAllData: MutableLiveData<List<Reservation>> = reservationDAO.getAllData()

    fun addReservation(reservation: Reservation) {
        reservationDAO.saveReservation(reservation)
    }

    fun updateReservation(reservation: Reservation) {
        reservationDAO.saveReservation(reservation)
    }

    fun deleteReservation(reservation: Reservation) {
        reservationDAO.deleteReservation(reservation)
    }
}