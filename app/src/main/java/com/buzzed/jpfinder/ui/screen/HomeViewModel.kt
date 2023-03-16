package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val jpRepository: JPRepository,
    ) : ViewModel() {

init {
    getFavoriteJPs()
}

    private var _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState


    fun updateLists(pList: List<String>, cList: Set<String> ) {
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



    fun enableButton() {
        _uiState.update { currentState ->
            currentState.copy(
                enabledButton = true
            )
        }
    }

    fun getFavoriteJPs(): List<JP> {
        var list: List<JP> = listOf()

        viewModelScope.launch(Dispatchers.IO) {
            jpRepository.getAllJPStream()
                .map {
                   list = it.filter { jp ->
                        jp.isFavorited
                    }
                    Log.d("get Favorite", "list: $list")
                }
        }



        return list
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

    }
}

data class HomeUiState(
    val parishList: List<String> = listOf(),
    val communityList: Set<String> = setOf(),
    val selectedParish: String = "",
    val selectedCommunity: String = "",
    val enabledCommunity: Boolean = false,
    val enabledButton: Boolean = false,
    val favoriteList: List<JP> = listOf(),

    )