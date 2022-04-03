package com.finalproject.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
//        val swipeGesture = object: SwipeGesture() {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                when(direction){
//                    ItemTouchHelper.LEFT -> {
//                        reservationAdapter.deleteItem(viewHolder.adapterPosition)
//                    }
//                }
//                super.onSwiped(viewHolder, direction)
//            }
//        }
        recycler.adapter = reservationAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        reservationViewModel = ViewModelProvider(this)[ReservationViewModel::class.java]

        var tempListOfReservations: MutableList<Reservation> = mutableListOf()
        tempListOfReservations.add(Reservation(1, 1, "test1", "test detail", "12/12/12"))
        tempListOfReservations.add(Reservation(2, 1, "test2", "test detail", "12/12/12"))
        tempListOfReservations.add(Reservation(3, 1, "test3", "test detail", "12/12/12"))
        tempListOfReservations.add(Reservation(4, 1, "test4", "test detail", "12/12/12"))
        tempListOfReservations.add(Reservation(5, 1, "test5", "test detail", "12/12/12"))

        reservationViewModel.getAllData.observe(viewLifecycleOwner) {
            reservations -> reservationAdapter.setData(tempListOfReservations)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}