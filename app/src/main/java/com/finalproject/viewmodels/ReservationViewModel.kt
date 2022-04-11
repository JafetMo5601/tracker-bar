package com.finalproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.finalproject.data.ReservationDAO
import com.finalproject.model.Reservation
import com.finalproject.repository.ReservationRepository

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: MutableLiveData<List<Reservation>>
    private val repository = ReservationRepository(ReservationDAO())

    init {
        getAllData = repository.getAllData
    }

    fun addReservation(reservation: Reservation) {
        repository.addReservation(reservation)
    }

    fun updateReservation(reservation: Reservation) {
        repository.updateReservation(reservation)
    }

    fun deleteReservation(reservation: Reservation) {
        repository.deleteReservation(reservation)
    }
}