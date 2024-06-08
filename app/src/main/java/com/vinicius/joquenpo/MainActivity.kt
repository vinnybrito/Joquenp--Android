package com.vinicius.joquenpo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vinicius.joquenpo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModel.newFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCollectors()
        setupListeners()
    }

    private fun setupCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.matchResult.collect { matchResult ->
                    binding.resultTextView.text = matchResult
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.computerChoice.collect { computerChoice ->
                    setupComputerChoiceImage(computerChoice)
                }
            }
        }
    }

    private fun setupComputerChoiceImage(computerChoice: Int?) {
        with(binding) {
            when (computerChoice) {
                Int.PAPEL -> { imageViewPadrao.setImageResource(R.drawable.papel)}
                Int.PEDRA -> { imageViewPadrao.setImageResource(R.drawable.pedra) }
                Int.TESOURA -> { imageViewPadrao.setImageResource(R.drawable.tesoura) }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            imageViewPapel.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.PAPEL) }
            imageViewPedra.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.PEDRA) }
            imageViewTesoura.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.TESOURA) }
        }
    }

}