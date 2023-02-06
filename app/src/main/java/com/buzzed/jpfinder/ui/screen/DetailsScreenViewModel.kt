package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import kotlinx.coroutines.flow.*

class DetailsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    jpRepository: JPRepository
) : ViewModel() {


   private val jpId: Int = checkNotNull(savedStateHandle[DetailsScreenDestination.jpId.toString()])

    val DetailUiState: StateFlow<DetailUiState> = jpRepository.getJPStream(jpId).map {
        DetailUiState(jp = it)}
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(DetailsScreenViewModel.TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )



    fun getFilteredJP() {
        Log.d("jpId", "$DetailUiState.value.jp")
        DetailUiState.value.jp
    }

    fun filterJP(): JP {
        val filteredJP = JP(0,"","","","","","","")


        return filteredJP
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailUiState(
    val jp: JP? = null,
)