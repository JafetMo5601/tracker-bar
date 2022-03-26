package com.finalproject.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalproject.R
import com.finalproject.adapter.ReservationAdapter
import com.finalproject.databinding.FragmentReservationBinding
import com.finalproject.model.Reservation
import com.finalproject.viewmodels.ReservationViewModel

class ReservationFragment : Fragment() {
    private lateinit var reservationViewModel: ReservationViewModel
    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        reservationViewModel =
            ViewModelProvider(this)[ReservationViewModel::class.java]
        _binding = FragmentReservationBinding.inflate(inflater, container, false)

        val reservationAdapter = ReservationAdapter()
        val recycler = binding.recycler
        recycler.adapter = reservationAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        reservationViewModel = ViewModelProvider(this)[ReservationViewModel::class.java]
        reservationViewModel.getAllData.observe(viewLifecycleOwner) {
            reservations -> reservationAdapter.setData(reservations)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}