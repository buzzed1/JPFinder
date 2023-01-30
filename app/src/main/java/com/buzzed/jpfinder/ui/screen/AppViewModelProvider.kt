package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.buzzed.jpfinder.JPFinderApplication

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            HomeViewModel()
        }

        initializer {
            ListScreenViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            DetailsScreenViewModel(this.createSavedStateHandle())
        }

    }
}

fun CreationExtras.JPFinderApplication(): JPFinderApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JPFinderApplication)