package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val jpRepository: JPRepository
    ) : ViewModel() {


    private var _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState
    val jp = JP(0, "Jones","George","","","","Westgreen","")
init {
    viewModelScope.launch {
        jpRepository.updateJP(jp)
    }
}
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
            enableCommunity()
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
        Log.d("parish", "${_uiState.value.selectedParish}")
        return _uiState.value.selectedParish
    }

    fun getCommunity(): String {
        Log.d("community", "${_uiState.value.selectedCommunity}")
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
        Log.d("Enable Button", "${_uiState.value.enabledButton}")
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