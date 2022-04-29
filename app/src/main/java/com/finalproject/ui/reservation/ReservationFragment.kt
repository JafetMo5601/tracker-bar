package com.finalproject.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.R
import com.finalproject.adapter.ReservationAdapter
import com.finalproject.animation.SwipeGesture
import com.finalproject.databinding.FragmentReservationBinding
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

        binding.fbAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_reservationFragment_to_addReservationFragment)
        }

        val reservationAdapter = ReservationAdapter()
        val recycler = binding.recycler

        recycler.adapter = reservationAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

//        barViewModel = ViewModelProvider(this)[BarViewModel::class.java]

//        barViewModel.addReservation(Bar("", "Rooster", "San Antonio, Alajuela, Costa Rica", true, 50, 10))
//        barViewModel.addReservation(Bar("", "Xcape Bar & Lounge", "Calle de la amargura, Saprissa, San Pedro", true, 50, 10))
//        barViewModel.addReservation(Bar("", "Bar Terra U", "Calle 61 San Pedro, San Jose, Costa Rica", true, 50, 10))
//        barViewModel.addReservation(Bar("", "OPEN MIND", "Contiguo a Monster Pizza, Calle de la Amargura", true, 50, 10))
//        barViewModel.addReservation(Bar("", "Terra \"U\"", "Calle de la amargura, Saprissa, San Pedro", true, 50, 10))
//        barViewModel.addReservation(Bar("", "Bar Einstein", "Saprissa, San Jose Province, Costa Rica", true, 50, 10))

        reservationViewModel = ViewModelProvider(this)[ReservationViewModel::class.java]

        reservationViewModel.getAllData.observe(viewLifecycleOwner) {
            reservations -> reservationAdapter.setData(reservations)
        }

        val swipeGesture = object: SwipeGesture(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT -> {
//                        tempListOfReservations.removeAt(viewHolder.bindingAdapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(recycler)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}