package com.finalproject.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.finalproject.R
import com.finalproject.databinding.FragmentAddReservationBinding
import com.finalproject.model.Bar
import com.finalproject.viewmodels.BarViewModel

class AddReservationFragment : Fragment() {

    private var _binding: FragmentAddReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var barViewModel: BarViewModel
    private val args by navArgs<AddReservationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddReservationBinding.inflate(inflater, container, false)
        barViewModel = ViewModelProvider(this).get(BarViewModel::class.java)

        binding.etTotalTables.setText(args.bar.tableQty)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addReservation(bar: Bar) {
        val total = binding.etTotalTablesToChoose.text.toString()
        if (total > bar.tableQty.toString()) {
            Toast.makeText(requireContext(), getString(R.string.msg_request), Toast.LENGTH_SHORT)
                .show()
        } else {
            //findNavController().navigate(R.id.action_addLugar_to_nav_lugar)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}