package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ListScreenViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {


    companion object {
        private val TIMEOUT_MILLI = 5_000L
    }
}