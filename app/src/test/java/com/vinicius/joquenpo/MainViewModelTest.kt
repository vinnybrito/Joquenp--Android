package com.vinicius.joquenpo

class MainViewModelTest {

    private val viewModel by lazy { MainViewModel() }

    fun checarSeGanhou() {
        viewModel.checkWinner(1, 0)
    }

}