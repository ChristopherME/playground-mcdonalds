package com.christopherelias.mcdonalds.features.menu.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christopherelias.mcdonalds.domain.repository.MenuRepository
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel(
    private val menuRepository: MenuRepository,
    private val menuUiMapper: MenuUiMapper
) : ViewModel() {

    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable -> handleException(throwable) }

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchMenu() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = uiState.value.copy(loading = true)
            val domainMenuList = menuRepository.getMenu()
            val uiMenuList = menuUiMapper.mapDomainMenuListToUi(domainMenuList)
            _uiState.value = uiState.value.copy(loading = false, menuItems = uiMenuList)
        }
    }

    private fun handleException(exception: Throwable) {
        _uiState.value = uiState.value.copy(loading = false, error = exception)
    }
}