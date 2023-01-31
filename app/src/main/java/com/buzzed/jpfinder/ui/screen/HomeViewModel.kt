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
    private val JPRepository: JPRepository
    ) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val list =
           JPRepository.getAllJPStream()
        }
    }

    fun updateLists(pList: List<Int>, cList: List<Int> ) {
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
                 enabledCommunity = true
                 )
             }
        }


    }

    fun selectCommunity(community: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCommunity = community,
                enabledButton = true
            )
        }
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
        Log.d("Enable Button", "${_uiState.value.enabledButton}")
        return _uiState.value.enabledButton
    }

    fun filterCommunity(communityName: String) {
        //To Do: filter community list based on parish //
    }

    fun getListOfJP(community: String) {


    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(
    val jpList: List<JP> = listOf(),
    val parishList: List<Int> = listOf(),
    val communityList: List<Int> = listOf(),
    val selectedParish: String = "",
    val selectedCommunity: String = "",
    val enabledCommunity: Boolean = false,
    val enabledButton: Boolean = false,


    )