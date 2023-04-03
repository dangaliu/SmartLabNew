package com.example.smartlabnew.fragments.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartlabnew.databinding.FragmentOnboardingItemBinding
import com.example.smartlabnew.fragments.onboarding.viewmodel.OnboardingViewModel

class OnboardingItemFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingItemBinding
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val position = arguments?.getInt("position") ?: 0
            val item = viewModel.items[position]
            tvTitle.text = item.title
            tvDescription.text = item.description
            ivImage.setImageResource(item.imageRes)
        }
    }

    companion object {
        fun newInstance(position: Int): OnboardingItemFragment {
            return OnboardingItemFragment().apply {
                arguments = Bundle().apply { putInt("position", position) }
            }
        }
    }
}