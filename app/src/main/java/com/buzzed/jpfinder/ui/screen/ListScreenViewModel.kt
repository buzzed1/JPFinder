package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPDao
import com.buzzed.jpfinder.data.JPRepository
import kotlinx.coroutines.flow.*

class ListScreenViewModel(
    jpRepository: JPRepository,
): ViewModel() {

    private var _uiState = MutableStateFlow(ListUiState())

    val uiState: StateFlow<ListUiState> = _uiState


    //consume the stateflow and change it to a list
    val listUiState: StateFlow<ListUiState> = jpRepository.getAllJPStream().map {ListUiState(it)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLI),
            initialValue = ListUiState()
        )


    fun filterJpInCommunity(communityName: String?): List<JP> {
        //To Do: filter community list based on parish //
        val list = mutableListOf<JP>()
        listUiState.value.jpList.map {jp ->
            if(jp.community?.lowercase() == communityName?.lowercase()) {
                list.add(jp)
            }
        }

        Log.d("filterJPInCommunity","list size: ${list.size}")
        Log.d("filterJPInCommunity","list: $list")
        return list
    }

    fun getListOfJPs(): List<JP> {
        return listUiState.value.jpList

    }


    companion object {
        private const val TIMEOUT_MILLI = 5_000L
    }
}

data class ListUiState(
    val jpList: List<JP> = listOf(),
)