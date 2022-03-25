package com.finalproject.repository

import androidx.lifecycle.LiveData
import com.finalproject.data.ReservationDAO
import com.finalproject.model.Reservation

class ReservationRepository(private val reservationDAO: ReservationDAO) {
    val getAllData: LiveData<List<Reservation>> = reservationDAO.getAllData()

    suspend fun addReservation(reservation: Reservation) {
        reservationDAO.addReservation(reservation)
    }

    suspend fun updateReservation(reservation: Reservation) {
        reservationDAO.updateReservation(reservation)
    }

    suspend fun deleteReservation(reservation: Reservation) {
        reservationDAO.deleteReservation(reservation)
    }
}