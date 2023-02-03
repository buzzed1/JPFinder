package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import kotlinx.coroutines.flow.*

class ListScreenViewModel(
    jpRepository: JPRepository,
): ViewModel() {

    private var _uiState = MutableStateFlow(ListUiState())

    val uiState: StateFlow<ListUiState> = _uiState

    val jpList2 = listOf<JP>()


    //consume the stateflow and change it to a list
    private val listUiState: StateFlow<ListUiState> = jpRepository.getAllJPStream().map {ListUiState(it)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLI),
            initialValue = ListUiState()
        )


    fun filterJpInCommunity(communityName: String?): List<JP> {
        //To Do: filter community list based on parish //
        val list = mutableListOf<JP>()
        listUiState.value.jpList.map {jp ->
            if(jp.community == communityName) {
                list.add(jp)
            }
        }
        return list
    }

    fun getListOfJPs(): List<JP> {
        return listUiState.value.jpList

    }

    fun setCommunity(community: String) {
        _uiState.update {
            it.copy(
                communityName = community
            )
        }
    }

    companion object {
        private const val TIMEOUT_MILLI = 5_000L
    }
}

data class ListUiState(
    val jpList: List<JP> = listOf(),
    val communityName: String = ""
)