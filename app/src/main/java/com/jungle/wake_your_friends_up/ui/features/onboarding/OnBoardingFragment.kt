package com.jungle.wake_your_friends_up.ui.features.onboarding

import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.databinding.FragmentOnboardingBinding
import com.jungle.wake_your_friends_up.ui.features.onboarding.adapter.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentOnboardingBinding
        get() = FragmentOnboardingBinding::inflate


    // fixme this should be lateinit to avoid crash
    private val onBoardingAdapter by lazy {
        OnBoardingAdapter(this)
    }

    override fun setupUi() {
        setupViewPager()
    }


    private fun setupViewPager() {
        binding.viewPager.adapter = onBoardingAdapter
        // A performance enhancement for viewpager
        binding.viewPager.offscreenPageLimit = 2
        // Callback for indicator animation and
        // next or finish button arrangement
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // adjust progress size according to position
                // and update indicator's progress value
                var progress = (position + 1) * 33
                progress = if (progress == 99) 100 else progress
                binding.indicator.updateProgress(progress)

                // Determine is user in last page or not
                val isLastPageOrNot = position == onBoardingAdapter.itemCount - 1
                // determine drawable id according to isLastPageOrNot variable
                val nextOrFinishDrawableId = if (isLastPageOrNot)
                    R.drawable.ic_check_circle
                else
                    R.drawable.ic_arrow_circle_right
                // set next or finish drawable
                val nextOrFinishDrawable =
                    ContextCompat.getDrawable(requireContext(), nextOrFinishDrawableId)
                binding.vNextOrFinish.background = nextOrFinishDrawable

                // next or finish button listener
                binding.vNextOrFinish.setOnClickListener {
                    if (isLastPageOrNot)
                        onFinishButtonClick()
                    else
                        onNextButtonClick(position = position)
                }
            }
        })
    }

    private fun onNextButtonClick(position: Int) {
        // it changes current position manually
        val nextPosition = position + 1
        binding.viewPager.setCurrentItem(nextPosition, true)
    }

    private fun onFinishButtonClick() {
        binding.vNextOrFinish.setOnClickListener{
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToStartFragment())
        }
    }
}