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
import cuonghtph34430.poly.app_dat_mon_an.databinding.FragmentMainDishBinding

class MainDishFragment : Fragment() {

    private var _binding: FragmentMainDishBinding? = null
    private val binding get() = _binding!!

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = ViewModelProvider(requireActivity())[FoodViewModel::class.java]

        // Thiết lập trạng thái của các checkbox
        foodViewModel.checkboxStates.observe(viewLifecycleOwner) { checkboxStates ->
            binding.checkboxMainDish1.isChecked = checkboxStates["Main Dish 1"] ?: false
            binding.checkboxMainDish2.isChecked = checkboxStates["Main Dish 2"] ?: false
            binding.checkboxMainDish3.isChecked = checkboxStates["Main Dish 3"] ?: false
        }

        // Xử lý sự kiện checkbox được nhấn
        binding.checkboxMainDish1.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Main Dish 1", isChecked)
        }

        binding.checkboxMainDish2.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Main Dish 2", isChecked)
        }

        binding.checkboxMainDish3.setOnCheckedChangeListener { _, isChecked ->
            foodViewModel.updateCheckboxState("Main Dish 3", isChecked)
        }

        binding.nextBtnMainDish.setOnClickListener {
            findNavController().navigate(R.id.action_mainDishFragment_to_sideDishFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
