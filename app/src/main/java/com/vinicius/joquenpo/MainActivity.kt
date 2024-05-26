package com.vinicius.joquenpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinicius.joquenpo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            imageViewPapel.setOnClickListener { optionSelected(Int.ZERO) }
            imageViewPedra.setOnClickListener { optionSelected(Int.ONE) }
            imageViewTesoura.setOnClickListener { optionSelected(Int.TWO) }
        }
    }

    private fun optionSelected(userChoice: Int) {
        checkWinner(Random.nextInt(Int.THREE), userChoice)
    }

    private fun setupComputerChoiceImage(computerChoice: Int) {
        with(binding) {
            when (computerChoice) {
                Int.ZERO -> { imageViewPadrao.setImageResource(R.drawable.papel)}
                Int.ONE -> { imageViewPadrao.setImageResource(R.drawable.pedra) }
                Int.TWO -> { imageViewPadrao.setImageResource(R.drawable.tesoura) }
            }
        }
    }

    private fun checkWinner(computerChoice: Int, userChoice: Int) {
        setupComputerChoiceImage(computerChoice)
        with(binding) {
            if (computerChoice == 0 && userChoice == 1) {
                resultTextView.text = FAILURE_MESSAGE
            } else if (computerChoice == 0 && userChoice == 2) {
                resultTextView.text = SUCCESS_MESSAGE
            } else if (computerChoice == 1 && userChoice == 0) {
                resultTextView.text = SUCCESS_MESSAGE
            } else if (computerChoice == 1 && userChoice == 2) {
                resultTextView.text = FAILURE_MESSAGE
            } else if (computerChoice == 2 && userChoice == 0) {
                resultTextView.text = FAILURE_MESSAGE
            } else if (computerChoice == 2 && userChoice == 1) {
                resultTextView.text = SUCCESS_MESSAGE
            } else {
                resultTextView.text = TIE_MESSAGE
            }
        }
    }

    companion object {
        private const val TIE_MESSAGE = "Empate"
        private const val SUCCESS_MESSAGE = "Você Ganhou"
        private const val FAILURE_MESSAGE = "Você perdeu!"
    }

}