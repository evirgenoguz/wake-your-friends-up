package com.jungle.wake_your_friends_up.ui.features.onboarding

import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.databinding.FragmentOnboardingBinding
import com.jungle.wake_your_friends_up.ui.features.onboarding.adapter.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentOnboardingBinding
        get() = FragmentOnboardingBinding::inflate

    override fun setupUi() {
        binding.viewPager.adapter = OnBoardingAdapter(this)
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var progress = (position + 1) * 33
                progress = if (progress == 99) 100 else progress
                binding.indicator.updateProgress(progress)

            }
        })
    }


}