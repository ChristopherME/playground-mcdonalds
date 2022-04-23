package com.christopherelias.mcdonalds.features.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.christopherelias.mcdonalds.databinding.BottomSheetFragmentMealBinding
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MealBottomSheetFragment : BottomSheetDialogFragment() {

    private val meal by lazy { requireArguments().getParcelable<MealUi>("meal")!! }

    private var _binding: BottomSheetFragmentMealBinding? = null
    private val binding: BottomSheetFragmentMealBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetFragmentMealBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
    }

    private fun renderView() {
        with(meal) {
            binding.mealName.text = name
            binding.mealDescription.text = description
            binding.mealPrice.text = price
            binding.mealImage.load(imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}