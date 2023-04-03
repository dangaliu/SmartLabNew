package com.example.smartlabnew.fragments.onboarding.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.smartlabnew.R
import com.example.smartlabnew.adapters.OnboardingAdapter
import com.example.smartlabnew.databinding.FragmentOnboardingBinding
import com.example.smartlabnew.fragments.onboarding.viewmodel.OnboardingViewModel
import com.example.smartlabnew.utils.SwipeControlTouchListener
import com.example.smartlabnew.utils.SwipeDirection

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var onboardingAdapter: OnboardingAdapter

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setIndicator(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        setObservers()
    }

    private fun initViewPager() {
        binding.viewPager.apply {
            onboardingAdapter = OnboardingAdapter(requireActivity(), viewModel.items)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = onboardingAdapter
        }
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
        (binding.viewPager[0] as RecyclerView).apply {
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            addOnItemTouchListener(SwipeControlTouchListener().apply {
                setSwipeDirection(SwipeDirection.RIGHT)
            })
        }
    }

    private fun setObservers() {
        viewModel.isLastPage.observe(viewLifecycleOwner) {
            if (it) {
                binding.textView.text = "Завершить"
            }
        }
    }

    private fun setIndicator(position: Int) {
        with(binding) {
            when (position) {
                0 -> {
                    iv01.setImageResource(R.drawable.filled_indicator)
                    iv02.setImageResource(R.drawable.unfilled_indicator)
                    iv03.setImageResource(R.drawable.unfilled_indicator)
                    viewModel.nextPage()
                }
                1 -> {
                    iv01.setImageResource(R.drawable.unfilled_indicator)
                    iv02.setImageResource(R.drawable.filled_indicator)
                    iv03.setImageResource(R.drawable.unfilled_indicator)
                    viewModel.nextPage()
                }
                2 -> {
                    iv01.setImageResource(R.drawable.unfilled_indicator)
                    iv02.setImageResource(R.drawable.unfilled_indicator)
                    iv03.setImageResource(R.drawable.filled_indicator)
                    viewModel.nextPage()
                    binding.viewPager.isUserInputEnabled = false
                }
                else -> {}
            }
        }
        Log.d("queue", "setIndicator: ${viewModel.items.size}")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}