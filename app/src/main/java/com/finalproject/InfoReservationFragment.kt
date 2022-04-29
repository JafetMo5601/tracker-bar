package com.finalproject

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.finalproject.databinding.FragmentInfoReservationBinding
import com.finalproject.model.Reservation
import com.finalproject.viewmodels.ReservationViewModel

class InfoReservationFragment : Fragment() {

    private val args by navArgs<InfoReservationFragmentArgs>()
    private var _binding: FragmentInfoReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var reservationViewModel: ReservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoReservationBinding.inflate(inflater, container, false)
        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)

        binding.etNombre.setText(args.reservation.barName)
        binding.etUserid.setText(args.reservation.userId)
        binding.etDescription.setText(args.reservation.detail)
        binding.etDate.setText(args.reservation.date)

        binding.btDelete.setOnClickListener{ deleteReservation() }

        binding.btUpdate.setOnClickListener { updateReservation() }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateReservation(){
        val name = binding.etNombre.text.toString()
        if(name.isNotEmpty()){
            val userid = binding.etUserid.text.toString()
            val description = binding.etDescription.text.toString()
            val date = binding.etDate.text.toString()
            val reservation = Reservation(args.reservation.id, userid, name, description, date)
            reservationViewModel.updateReservation(reservation)
            Toast.makeText(requireContext(),getString(R.string.msg_reservation_updated), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_updatereservation_failed), Toast.LENGTH_LONG).show()
        }
        findNavController().navigate(R.id.action_infoReservationFragment_to_reservationFragment)
    }

    private fun deleteReservation(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){ _, _ ->
            reservationViewModel.deleteReservation(args.reservation)
            findNavController().navigate(R.id.action_infoReservationFragment_to_reservationFragment)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_seguro_borrar))
        builder.create().show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}