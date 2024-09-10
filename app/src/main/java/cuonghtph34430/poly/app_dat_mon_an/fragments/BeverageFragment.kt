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
import cuonghtph34430.poly.app_dat_mon_an.databinding.FragmentBeverageBinding

class BeverageFragment : Fragment() {

    private var _binding: FragmentBeverageBinding? = null
    private val binding get() = _binding!!

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeverageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = ViewModelProvider(requireActivity())[FoodViewModel::class.java]

        // Thiết lập trạng thái của các checkbox
        foodViewModel.checkboxStates.observe(viewLifecycleOwner) { checkboxStates ->
            binding.checkboxBeverage1.isChecked = checkboxStates["Beverage 1"] ?: false
            binding.checkboxBeverage2.isChecked = checkboxStates["Beverage 2"] ?: false
            binding.checkboxBeverage3.isChecked = checkboxStates["Beverage 3"] ?: false
        }

        // Xử lý sự kiện checkbox được nhấn
        binding.checkboxBeverage1.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Beverage 1", isChecked)
        }

        binding.checkboxBeverage2.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Beverage 2", isChecked)
        }

        binding.checkboxBeverage3.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Beverage 3", isChecked)
        }

        binding.nextBtnBeverage.setOnClickListener {
            findNavController().navigate(R.id.action_beverageFragment_to_selectedFoodsFragment)
        }
        binding.backBtnBeverage.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
