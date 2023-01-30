package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.ViewModel
import com.buzzed.jpfinder.data.JP

class HomeViewModel : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val jpList: List<JP> = listOf())