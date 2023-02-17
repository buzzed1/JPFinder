package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import com.buzzed.jpfinder.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val jpRepository: JPRepository,
    private val userPreferencesRepository: UserPreferencesRepository
    ) : ViewModel() {


    private var _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState


    fun updateLists(pList: List<Int>, cList: Set<Int> ) {
        _uiState.update {currentState ->
        currentState.copy(
            parishList = pList,
            communityList = cList
        )

        }
    }

    fun selectParish(parish: String) {
        if (parish != "") {
             _uiState.update { currentState ->
                 currentState.copy(
                 selectedParish = parish,
                 )
             }
            //enableCommunity()
        }


    }

    fun selectCommunity(community: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCommunity = community,

            )
        }
        enableButton()
    }

    fun getParish(): String {
        return _uiState.value.selectedParish
    }

    fun getCommunity(): String {
        return _uiState.value.selectedCommunity
    }

    fun enableCommunity() {
        _uiState.update {currentState ->
        currentState.copy(
            enabledCommunity = true
        )
        }
    }

    fun enableButton() {
        _uiState.update { currentState ->
            currentState.copy(
                enabledButton = true
            )
        }
    }

    fun getEnabledCommunity(): Boolean {
        return _uiState.value.enabledCommunity
    }

    fun getEnabledButton(): Boolean {
        return _uiState.value.enabledButton
    }



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

    }
}

data class HomeUiState(
    val parishList: List<Int> = listOf(),
    val communityList: Set<Int> = setOf(),
    val selectedParish: String = "",
    val selectedCommunity: String = "",
    val enabledCommunity: Boolean = false,
    val enabledButton: Boolean = false,


    )