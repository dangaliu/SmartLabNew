package com.example.smartlabnew.fragments.onboarding.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.smartlabnew.R
import com.example.smartlabnew.model.dto.OnboardingItem
import java.util.*

class OnboardingViewModel(private val app: Application) : AndroidViewModel(app) {

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

    fun nextPage() {
        items.removeFirst()
        if (items.size == 0) {
            isLastPage.value = true
        }
    }
}