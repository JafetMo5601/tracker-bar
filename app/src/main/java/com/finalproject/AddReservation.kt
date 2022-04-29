package com.finalproject

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.finalproject.databinding.FragmentBarBinding
import com.finalproject.databinding.ReservationLayoutBinding
import com.finalproject.ui.reservation.AddReservationFragment
import com.finalproject.viewmodels.ReservationViewModel

class AddReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reservation)
    }
}