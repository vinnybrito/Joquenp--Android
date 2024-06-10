package com.vinicius.joquenpo

import com.vinicius.joquenpo.commons.extensions.PAPER
import com.vinicius.joquenpo.commons.extensions.ROCK
import com.vinicius.joquenpo.commons.extensions.SCISSORS
import com.vinicius.joquenpo.ui.joquenpo.JoquenpoViewModel
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import org.junit.Test

class JoquenpoViewModelTest {

    private val viewModel by lazy { JoquenpoViewModel() }

    @Test
    fun givenCheckWinner_whenUserChoiceIsSuccess_thenShowSuccessMessage() {
        viewModel.checkWinner(0, 2)
        assertEquals("Você ganhou!", viewModel.matchResult.value)

        viewModel.checkWinner(1, 0)
        assertEquals("Você ganhou!", viewModel.matchResult.value)

        viewModel.checkWinner(2, 1)
        assertEquals("Você ganhou!", viewModel.matchResult.value)
    }

    @Test
    fun givenCheckWinner_whenUserChoiceIsFailure_thenShowFailureMessage() {
        viewModel.checkWinner(2, 0)
        assertEquals("Você perdeu!", viewModel.matchResult.value)

        viewModel.checkWinner(0, 1)
        assertEquals("Você perdeu!", viewModel.matchResult.value)

        viewModel.checkWinner(1, 2)
        assertEquals("Você perdeu!", viewModel.matchResult.value)
    }

}