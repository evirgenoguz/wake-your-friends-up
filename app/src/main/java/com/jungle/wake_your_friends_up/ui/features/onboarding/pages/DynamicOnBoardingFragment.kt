package com.jungle.wake_your_friends_up.ui.features.onboarding.pages

import android.os.Bundle
import android.view.LayoutInflater
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.databinding.FragmentDynamicOnboardingBinding
import com.jungle.wake_your_friends_up.ext.getParcelableModel
import com.jungle.wake_your_friends_up.ui.features.onboarding.OnBoardingViewModel
import com.jungle.wake_your_friends_up.ui.features.onboarding.adapter.OnBoardingAdapter.Companion.KEY_ONBOARDING_MODEL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DynamicOnBoardingFragment : BaseFragment<FragmentDynamicOnboardingBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentDynamicOnboardingBinding
        get() = FragmentDynamicOnboardingBinding::inflate

    override fun setupUi() {
        setupViewsWithArguments()
    }

    private fun setupViewsWithArguments() {
        // retrieve model for fragment layout configuration
        // with arguments bundle.
        requireArguments().getParcelableModel<OnBoardingViewModel.OnBoardingModel>(
            KEY_ONBOARDING_MODEL
        )?.let { model ->
            // sets title and image
            with(binding) {
                ivOnBoarding.setImageBitmap(model.contentImage)
                tvOnBoarding.text = model.title
            }
        }
    }


    companion object {
        fun newInstance(bundle: Bundle): DynamicOnBoardingFragment {
            val fragment = DynamicOnBoardingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}