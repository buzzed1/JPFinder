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



    fun getFilteredJP(id: Int): JP? {
        val rJp = JP(0,"","","","","","","")
       val returnedJp = detailUiState.value.jpFiltered.filter {jp ->
            jp?.id == (id ?: 0)

        }
        Log.d("jpId", "$returnedJp.first()")
        return if(returnedJp.isEmpty()){
            rJp
        } else {
            returnedJp.first()
        }
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
    val jp: JP? = null
)