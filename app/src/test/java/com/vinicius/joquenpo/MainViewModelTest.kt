package com.vinicius.joquenpo

import com.vinicius.joquenpo.ui.main.MainViewModel

class MainViewModelTest {

    private val viewModel by lazy { MainViewModel() }

    fun checarSeGanhou() {
        viewModel.checkWinner(1, 0)
    }

}