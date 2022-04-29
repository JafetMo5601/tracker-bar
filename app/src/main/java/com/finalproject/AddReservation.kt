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
    private lateinit var reservationViewModel: ReservationViewModel
    private var binding: AddReservation? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reservation)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)

        reservationViewModel.addReservation()
        return super.onCreateView(name, context, attrs)
    }
}