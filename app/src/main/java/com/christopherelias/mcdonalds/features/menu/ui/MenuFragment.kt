package com.christopherelias.mcdonalds.features.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.christopherelias.mcdonalds.R
import com.christopherelias.mcdonalds.databinding.FragmentMenuBinding
import com.christopherelias.mcdonalds.di.MenuFeatureDiGraph
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import com.christopherelias.mcdonalds.features.menu.ui.recyclerview.MenuAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding!!

    private val viewModel: MenuViewModel by viewModels {
        MenuViewModelFactory(
            repository = MenuFeatureDiGraph.provideRepository(),
            uiMapper = MenuFeatureDiGraph.provideMenuUiMapper()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.fetchMenu()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect { state ->
                    collectUiState(state)
                }
            }
        }
    }

    private fun collectUiState(state: MenuUiState) {
        with(state) {
            binding.progressBarMenu.isVisible = loading

            if (menuItems.isNotEmpty()) {
                binding.recyclerViewMenu.adapter = MenuAdapter(menuItems, ::onMealClicked)
            }

            if (error != null) {
                Snackbar.make(binding.root, "Something went wrong: $error", LENGTH_LONG).show()
            }
        }
    }

    private fun onMealClicked(meal: MealUi) {
        MealBottomSheetFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable("meal", meal)
                }
            }
            .show(childFragmentManager, "MealDetail")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}