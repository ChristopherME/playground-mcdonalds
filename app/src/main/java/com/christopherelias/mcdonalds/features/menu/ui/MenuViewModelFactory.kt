package com.christopherelias.mcdonalds.features.menu.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.christopherelias.mcdonalds.domain.repository.MenuRepository
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapper

class MenuViewModelFactory(
    private val repository: MenuRepository,
    private val uiMapper: MenuUiMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(
                menuRepository = repository,
                menuUiMapper = uiMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}