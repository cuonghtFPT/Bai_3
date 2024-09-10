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
import cuonghtph34430.poly.app_dat_mon_an.databinding.FragmentSideDishBinding

class SideDishFragment : Fragment() {

    private var _binding: FragmentSideDishBinding? = null
    private val binding get() = _binding!!

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = ViewModelProvider(requireActivity())[FoodViewModel::class.java]

        // Thiết lập trạng thái của các checkbox
        foodViewModel.checkboxStates.observe(viewLifecycleOwner) { checkboxStates ->
            binding.checkboxSideDish1.isChecked = checkboxStates["Side Dish 1"] ?: false
            binding.checkboxSideDish2.isChecked = checkboxStates["Side Dish 2"] ?: false
            binding.checkboxSideDish3.isChecked = checkboxStates["Side Dish 3"] ?: false
        }

        // Xử lý sự kiện checkbox được nhấn
        binding.checkboxSideDish1.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Side Dish 1", isChecked)
        }

        binding.checkboxSideDish2.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Side Dish 2", isChecked)
        }

        binding.checkboxSideDish3.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Side Dish 3", isChecked)
        }

        binding.nextBtnSideDish.setOnClickListener {
            findNavController().navigate(R.id.action_sideDishFragment_to_dessertFragment)
        }
        binding.backBtnSideDish.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
