package com.example.kotlinbasic_bai2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinbasic_bai2.viewmodel.FoodViewModel
import cuonghtph34430.poly.app_dat_mon_an.R
import cuonghtph34430.poly.app_dat_mon_an.databinding.FragmentDessertBinding

class DessertFragment : Fragment() {

    private var _binding: FragmentDessertBinding? = null
    private val binding get() = _binding!!

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDessertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = ViewModelProvider(requireActivity())[FoodViewModel::class.java]

        // Thiết lập trạng thái của các checkbox
        foodViewModel.checkboxStates.observe(viewLifecycleOwner) { checkboxStates ->
            binding.checkboxDessert1.isChecked = checkboxStates["Dessert 1"] ?: false
            binding.checkboxDessert2.isChecked = checkboxStates["Dessert 2"] ?: false
            binding.checkboxDessert3.isChecked = checkboxStates["Dessert 3"] ?: false
        }

        // Xử lý sự kiện checkbox được nhấn
        binding.checkboxDessert1.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Dessert 1", isChecked)
        }

        binding.checkboxDessert2.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Dessert 2", isChecked)
        }

        binding.checkboxDessert3.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Dessert 3", isChecked)
        }

        binding.nextBtnDessert.setOnClickListener {
            findNavController().navigate(R.id.action_dessertFragment_to_beverageFragment)
        }
        binding.backBtnDessert.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
