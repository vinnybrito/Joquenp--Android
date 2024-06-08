package com.vinicius.joquenpo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _computerChoice = MutableStateFlow<Int?>(null)
    val computerChoice = _computerChoice.asStateFlow()

    private val _matchResult = MutableStateFlow<String?>(null)
    val matchResult = _matchResult.asStateFlow()

    fun setupChoicesOnUserClick(choice: Int) {
        _computerChoice.value = Random.nextInt(Int.THREE)
        checkWinner(_computerChoice.value ?: 0, choice)
    }

    fun checkWinner(computerChoice: Int, userChoice: Int) {
        val answer = if(computerChoice == userChoice) {
            TIE_MESSAGE
        } else if ((computerChoice == Int.PAPEL && userChoice == Int.TESOURA) ||
                   (computerChoice == Int.PEDRA && userChoice == Int.PAPEL) ||
                   (computerChoice == Int.TESOURA && userChoice == Int.PEDRA)) {
            SUCCESS_MESSAGE
        } else {
            FAILURE_MESSAGE
        }
        _matchResult.value = answer
    }

    companion object {
        private const val TIE_MESSAGE = "Empate"
        private const val SUCCESS_MESSAGE = "Você ganhou!"
        private const val FAILURE_MESSAGE = "Você perdeu!"

        fun newFactory(): ViewModelProvider.Factory {
            return ViewModelProvider.NewInstanceFactory()
        }
    }

}