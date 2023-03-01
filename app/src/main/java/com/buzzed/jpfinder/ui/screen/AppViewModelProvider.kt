package com.buzzed.jpfinder.ui.screen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.buzzed.jpfinder.JPFinderApplication
import com.buzzed.jpfinder.data.JPDao

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            HomeViewModel(
                JPFinderApplication().container.jpRepository,
            )
        }

        initializer {
            ListScreenViewModel(
                JPFinderApplication().container.jpRepository,
            )
        }

        initializer {
            DetailsScreenViewModel(
                this.createSavedStateHandle(),
                JPFinderApplication().container.jpRepository,
            )
        }

    }
}

fun CreationExtras.JPFinderApplication(): JPFinderApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JPFinderApplication)