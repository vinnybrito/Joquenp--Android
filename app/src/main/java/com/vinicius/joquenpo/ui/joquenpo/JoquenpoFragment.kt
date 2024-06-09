package com.vinicius.joquenpo.ui.joquenpo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vinicius.joquenpo.commons.extensions.PAPER
import com.vinicius.joquenpo.commons.extensions.ROCK
import com.vinicius.joquenpo.commons.extensions.SCISSORS
import com.vinicius.joquenpo.databinding.FragmentJoquenpoBinding
import kotlinx.coroutines.launch

class JoquenpoFragment : Fragment() {

    private lateinit var binding: FragmentJoquenpoBinding
    private val viewModel: JoquenpoViewModel by viewModels { JoquenpoViewModel.newFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoquenpoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                Int.PAPER -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.papel) }
                Int.ROCK -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.pedra) }
                Int.SCISSORS -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.tesoura) }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            imageViewPapel.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.PAPER) }
            imageViewPedra.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.ROCK) }
            imageViewTesoura.setOnClickListener { viewModel.setupChoicesOnUserClick(Int.SCISSORS) }
        }
    }

}