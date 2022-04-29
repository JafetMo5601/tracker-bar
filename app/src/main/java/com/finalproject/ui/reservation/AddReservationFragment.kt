package com.finalproject.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.finalproject.R
import com.finalproject.databinding.FragmentAddReservationBinding
import com.finalproject.model.Bar
import com.finalproject.model.Reservation
import com.finalproject.viewmodels.BarViewModel
import com.finalproject.viewmodels.ReservationViewModel

class AddReservationFragment : Fragment() {

    private var _binding: FragmentAddReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var barViewModel: BarViewModel
    private lateinit var reservationModel: ReservationViewModel
    private val args by navArgs<AddReservationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddReservationBinding.inflate(inflater, container, false)
        reservationModel = ViewModelProvider(this).get(ReservationViewModel::class.java)

        binding.btRequest.setOnClickListener {
            addReservation()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addReservation(){
        val name = binding.etNombre.text.toString()
        if(name.isNotEmpty()){
            val userid = binding.etUserid.text.toString()
            val description = binding.etDescription.text.toString()
            val date = binding.etDate.text.toString()
            val reservation = Reservation("", name, userid, description, date)
            reservationModel.addReservation(reservation)
            Toast.makeText(requireContext(),getString(R.string.msg_reservation_added),Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_nofound_data),Toast.LENGTH_LONG).show()
        }
        findNavController().navigate(R.id.action_addReservationFragment_to_reservationFragment)
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}