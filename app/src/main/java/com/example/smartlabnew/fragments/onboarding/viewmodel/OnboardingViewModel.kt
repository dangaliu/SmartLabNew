package com.example.smartlabnew.fragments.onboarding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartlabnew.R
import com.example.smartlabnew.model.dto.OnboardingItem
import java.util.*

class OnboardingViewModel : ViewModel() {

    val items = LinkedList(
        listOf(
            OnboardingItem(
                "Анализы",
                "Экспресс сбор и получение проб",
                R.drawable.onboarding_01
            ),
            OnboardingItem(
                "Уведомления",
                "Вы быстро узнаете о результатах",
                R.drawable.onboarding_02
            ),
            OnboardingItem(
                "Мониторинг",
                "Наши врачи всегда наблюдают за вашими показателями здоровья",
                R.drawable.onboarding_03
            )
        )
    )

    var isLastPage = MutableLiveData(false)

    var currentText = "Пропустить"

    fun nextPage(): OnboardingItem {
        val removedItem = items.removeFirst()
        if (items.size == 0) {
            isLastPage.value = true
            currentText = "Завершить"
        }
        return removedItem
    }
}