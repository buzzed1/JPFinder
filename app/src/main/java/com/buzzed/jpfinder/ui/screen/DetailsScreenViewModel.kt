package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import com.buzzed.jpfinder.data.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class DetailsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val jpRepository: JPRepository,
) : ViewModel() {


    private var _uiState = MutableStateFlow(DetailUiState())

    val uiState: StateFlow<DetailUiState> = _uiState

    private var _oneJP = MutableStateFlow(OneJp())

    private val oneJPstate: StateFlow<OneJp> = _oneJP.asStateFlow()

    private var _favoriteJpState = MutableStateFlow(FavoriteJP())

    val favoriteJpState: StateFlow<FavoriteJP> = _favoriteJpState.asStateFlow()

    val detailUiState: StateFlow<DetailUiState> = jpRepository.getAllJPStream().map {
        DetailUiState(it)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )






    private var jpFromId: JP? = JP(null,"Loading","Loading","Loading","Loading","Loading","Loading","Loading") //= jpRepository.getJPStream(DetailsScreenDestination.jpId)

    private val favoriteJPList = mutableListOf<JP>()

    private var favorites = mutableMapOf<JP,Boolean>()


   fun getFilteredJP(): JP? {
      filterJP()

     return oneJPstate.value.jp
   }

     fun filterJP() {
         viewModelScope.launch(Dispatchers.IO) {
             jpFromId = jpRepository.getJPStream(DetailsScreenDestination.jpId)
         }

       _oneJP.update {currentState ->
           currentState.copy(
              jp = jpFromId
           )
       }


    }

    /*
    Update the database and set isfavorite as true
     */
    suspend fun updateJp(jp: JP) {

    }

    fun setFavoriteJP(isFavorited: Boolean, jp: JP) {

        if (isFavorited) {
                val newJP =  jp.copy(isFavorited = isFavorited)
                viewModelScope.launch(Dispatchers.IO) {
                    jpRepository.updateJP(newJP)
                }
            filterJP()
            favorites[newJP] = isFavorited
            } else {
            val newJP =  jp.copy(isFavorited = isFavorited)
            viewModelScope.launch(Dispatchers.IO) {
                jpRepository.updateJP(newJP)
            }
            filterJP()
            favorites[newJP] = isFavorited
        }


    }




    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailUiState(
    val jpFiltered: List<JP?> = listOf(),
    )


data class FavoriteJP (
    val jpList: List<JP> = listOf(),
    //val checkedState: Boolean = false
        )

data class OneJp (
    val jp: JP? = null
        )