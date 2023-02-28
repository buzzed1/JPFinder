package com.buzzed.jpfinder.ui.screen

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
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {


    private var _uiState = MutableStateFlow(DetailUiState())

    val uiState: StateFlow<DetailUiState> = _uiState

    private var _oneJP = MutableStateFlow(OneJp())
        private set

    private val oneJPstate: StateFlow<OneJp> = _oneJP.asStateFlow()

    val detailUiState: StateFlow<DetailUiState> = jpRepository.getAllJPStream().map {
        DetailUiState(it)}
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(DetailsScreenViewModel.TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )




      private var jpFromId: JP? = null //= jpRepository.getJPStream(DetailsScreenDestination.jpId)

      private val favoriteJPList = mutableListOf<JP>()


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

    fun updateJp(name: String) {

    }

    fun setFavoriteJP(isFavorited: Boolean, jp: JP) {
        val userPref = userPreferencesRepository
        viewModelScope.launch {
            userPref.saveFavorited(isFavorited)
        }
        favoriteJPList.add(jp)
        FavoriteJP(favoriteJPList)

    }

    fun getFavoriteJPs(): List<JP> {
        return favoriteJPList
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailUiState(
    val jpFiltered: List<JP?> = listOf(),
    )

data class UserPrefUiState (
    val isFavorited: Boolean
        )

data class FavoriteJP (
    val jpList: List<JP> = listOf()
        )

data class OneJp (
    val jp: JP? = null
        )