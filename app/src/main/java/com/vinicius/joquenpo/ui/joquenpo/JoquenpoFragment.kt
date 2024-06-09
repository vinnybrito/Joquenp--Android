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
import com.vinicius.joquenpo.commons.extensions.PAPEL
import com.vinicius.joquenpo.commons.extensions.PEDRA
import com.vinicius.joquenpo.commons.extensions.TESOURA
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
                Int.PAPEL -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.papel) }
                Int.PEDRA -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.pedra) }
                Int.TESOURA -> { imageViewPadrao.setImageResource(com.vinicius.joquenpo.R.drawable.tesoura) }
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