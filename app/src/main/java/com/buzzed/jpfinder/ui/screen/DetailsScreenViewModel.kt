package com.buzzed.jpfinder.ui.screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import com.buzzed.jpfinder.data.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch




class DetailsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    jpRepository: JPRepository,
    val userPreferencesRepository: UserPreferencesRepository
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

    val userPrefUiState: StateFlow<UserPrefUiState> = userPreferencesRepository.isFavorited.map { isFavorited ->
        UserPrefUiState(isFavorited)
    } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = UserPrefUiState(false)
    )


     private var jpFromId: JP? = null

     private val favoriteJPList = mutableListOf<JP>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            jpFromId = jpRepository.getJPStream(DetailsScreenDestination.jpId)
        }

    }

    fun getFilteredJP(): JP? {
       return jpFromId
    }

//    fun filterJP(): JP {
//        val filteredJP = JP(0,"","","","","","","")
//
//
//        return filteredJP
//    }

//    fun updateJp(name: String) {
//
//    }

    fun setFavoriteJP(isFavorited: Boolean, jp: JP) {
        val userPref = userPreferencesRepository
        viewModelScope.launch {
            userPref.saveFavorited(isFavorited)
        }
        favoriteJPList.add(jp)
        FavoriteJP(favoriteJPList)
        Log.d("Set Favorite", "$favoriteJPList")

    }

    fun getFavoriteJPs(): List<JP> {
        Log.d("Return Favorites", "$favoriteJPList")
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