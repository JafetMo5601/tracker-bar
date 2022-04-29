package com.finalproject.ui.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalproject.adapter.BarAdapter
import com.finalproject.databinding.FragmentBarBinding
import com.finalproject.viewmodels.BarViewModel

class BarFragment : Fragment() {
    private lateinit var barViewModel: BarViewModel
    private var _binding: FragmentBarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        barViewModel = ViewModelProvider(this)[BarViewModel::class.java]

        _binding = FragmentBarBinding.inflate(inflater, container, false)

        val barAdapter = BarAdapter()
        val recycler = binding.recycler

        recycler.adapter = barAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        barViewModel.getAllData.observe(viewLifecycleOwner) {
                bares -> barAdapter.setData(bares)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}