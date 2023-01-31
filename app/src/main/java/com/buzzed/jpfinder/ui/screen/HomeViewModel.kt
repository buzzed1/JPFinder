package com.buzzed.jpfinder.ui.screen

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val JPRepository: JPRepository
    ) : ViewModel() {

    val parishList = ParishList()
    val communityList = Towns()
   /* private var jpList*/

    var selectedParish = ""
    var selectedCommunity = ""

    var enabledCommunity = false
    var enabledButton = false



    fun selectParish(parish: String) {
        selectedParish = parish

        if (selectedParish == "") {
            enabledCommunity = false
        } else {
            enabledCommunity = true
        }
    }

    fun selectCommunity(community: String) {
        selectedCommunity = community

        if(selectedCommunity == "") {
            enabledButton = false
        }else {
            enabledButton = true
        }
    }

    fun filterCommunity(communityName: String) {
        //To Do: filter community list based on parish //
    }

    fun getListOfJP(community: String) : Flow<() -> Flow<List<JP>>> = flowOf {
        JPRepository.getJpInCommunity(community)

    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val jpList: List<JP> = listOf())