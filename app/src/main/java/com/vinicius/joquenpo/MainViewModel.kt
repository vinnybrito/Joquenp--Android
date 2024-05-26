package com.vinicius.joquenpo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MainViewModel: ViewModel() {

    companion object {
        fun newFactory() {
            viewModelFactory { initializer { MainViewModel() } }
        }
    }

}