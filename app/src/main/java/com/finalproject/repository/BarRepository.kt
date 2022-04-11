package com.finalproject.repository

import androidx.lifecycle.MutableLiveData
import com.finalproject.data.BarDAO
import com.finalproject.model.Bar

class BarRepository(private val barDAO: BarDAO) {
    val getAllData: MutableLiveData<List<Bar>> = barDAO.getAllData()

    fun addBar(bar: Bar) {
        barDAO.saveBar(bar)
    }
}