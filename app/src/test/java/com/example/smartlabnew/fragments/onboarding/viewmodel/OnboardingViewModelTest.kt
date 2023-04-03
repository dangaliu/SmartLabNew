package com.example.smartlabnew.fragments.onboarding.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnboardingViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: OnboardingViewModel

    @Before
    fun init() {
        viewModel = OnboardingViewModel()
    }

    @Test
    fun `item in queue removes correctly`() {
        val firstItem = viewModel.items[0]
        val secondItem = viewModel.items[1]
        val thirdItem = viewModel.items[2]

        assertEquals(firstItem, viewModel.nextPage())
        assertEquals(secondItem, viewModel.nextPage())
        assertEquals(thirdItem, viewModel.nextPage())
    }

    @Test
    fun `item count decrease by one`() {
        viewModel.nextPage()
        assertEquals(2, viewModel.items.size)
        viewModel.nextPage()
        assertEquals(1, viewModel.items.size)
        viewModel.nextPage()
        assertEquals(0, viewModel.items.size)
    }

    @Test
    fun `correct text in button`() {
        assertEquals("Пропустить", viewModel.currentText)
        viewModel.nextPage()
        viewModel.nextPage()
        viewModel.nextPage()
        assertEquals("Завершить", viewModel.currentText)
    }
}