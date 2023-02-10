package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch




class DetailsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    jpRepository: JPRepository
) : ViewModel() {



    private var _uiState = MutableStateFlow(DetailUiState())

    val uiState: StateFlow<DetailUiState> = _uiState

    val detailUiState: StateFlow<DetailUiState> = jpRepository.getAllJPStream().map {
        DetailUiState(it)}
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(DetailsScreenViewModel.TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )

     private var jpFromId: JP? = null


    init {
        viewModelScope.launch(Dispatchers.IO) {
            jpFromId = jpRepository.getJPStream(DetailsScreenDestination.jpId)
        }

    }

    fun getFilteredJP(): JP? {
       return jpFromId
    }

    fun filterJP(): JP {
        val filteredJP = JP(0,"","","","","","","")


        return filteredJP
    }

    fun updateJp(name: String) {


    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailUiState(
    val jpFiltered: List<JP?> = listOf(),

    )