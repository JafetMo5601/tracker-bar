package com.finalproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.finalproject.data.BarDAO
import com.finalproject.model.Bar
import com.finalproject.repository.BarRepository

class BarViewModel(application: Application) : AndroidViewModel(application) {
    val getAllData: MutableLiveData<List<Bar>>
    private val repository = BarRepository(BarDAO())

    init {
        getAllData = repository.getAllData
    }

    fun addReservation(bar: Bar) {
        repository.addBar(bar)
    }
}