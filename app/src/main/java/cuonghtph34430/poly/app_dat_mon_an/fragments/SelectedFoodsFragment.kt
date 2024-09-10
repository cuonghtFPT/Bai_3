package com.example.kotlinbasic_bai2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinbasic_bai2.viewmodel.FoodViewModel
import cuonghtph34430.poly.app_dat_mon_an.databinding.FragmentSelectedFoodsBinding

class SelectedFoodsFragment : Fragment() {

    private lateinit var viewModel: FoodViewModel
    private var _binding: FragmentSelectedFoodsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedFoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FoodViewModel::class.java]

        viewModel.selectedFoods.observe(viewLifecycleOwner) { selectedFoods ->
            // Xây dựng nội dung để hiển thị các món ăn đã chọn
            val stringBuilder = StringBuilder()
            for (food in selectedFoods) {
                stringBuilder.append(food).append("\n") // Thêm mỗi món ăn vào dòng mới
            }
            binding.textViewSelectedFoods.text = stringBuilder.toString()
        }
        binding.backBtnSelect.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
