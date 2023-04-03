package com.example.smartlabnew.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smartlabnew.fragments.onboarding.view.OnboardingItemFragment
import com.example.smartlabnew.model.dto.OnboardingItem

class OnboardingAdapter(
    private val fragmentActivity: FragmentActivity,
    private val items: List<OnboardingItem>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingItemFragment.newInstance(position)
    }

}