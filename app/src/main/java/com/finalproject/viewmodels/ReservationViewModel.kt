package com.finalproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.finalproject.data.TrackerBarDB
import com.finalproject.model.Reservation
import com.finalproject.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: LiveData<List<Reservation>>
    private val repository: ReservationRepository

    init {
        val reservationDAO = TrackerBarDB.getDatabase(application).reservationDAO()
        repository = ReservationRepository(reservationDAO)
        getAllData = repository.getAllData
    }

    fun addReservation(reservation: Reservation) {
        viewModelScope.launch(Dispatchers.IO) { repository.addReservation(reservation) }
    }

    fun updateReservation(reservation: Reservation) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateReservation(reservation) }
    }

    fun deleteReservation(reservation: Reservation) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteReservation(reservation) }
    }
}