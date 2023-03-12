package com.jungle.wake_your_friends_up.ui.features.onboarding.adapter

import android.graphics.BitmapFactory
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.ui.features.onboarding.OnBoardingViewModel
import com.jungle.wake_your_friends_up.ui.features.onboarding.pages.DynamicOnBoardingFragment

/**
 *
 * It is an adapter for managing on boarding screen's
 * view pager. It has the fragments it will use inside.
 * It uses [DynamicOnBoardingFragment] for pages and uses
 * bundle objects for data transfer. The data transfer object
 * is [OnBoardingViewModel.OnBoardingModel].
 *
 * Created by Burak Taşcı on 3.03.2023.
 */
class OnBoardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val fragments by lazy {
        arrayOf(
            DynamicOnBoardingFragment.newInstance(
                bundle = bundleOf(
                    KEY_ONBOARDING_MODEL to OnBoardingViewModel.OnBoardingModel(
                        contentImage = BitmapFactory.decodeResource(
                            fragment.resources,
                            R.drawable.img_red_alarm_clock_in_hurry
                        ),
                        title = fragment.requireContext().getString(R.string.onboarding_first_page)
                    )
                )
            ),
            DynamicOnBoardingFragment.newInstance(
                bundle = bundleOf(
                    KEY_ONBOARDING_MODEL to OnBoardingViewModel.OnBoardingModel(
                        contentImage = BitmapFactory.decodeResource(
                            fragment.resources,
                            R.drawable.img_yellow_alarm_clock_with_books
                        ),
                        title = fragment.requireContext().getString(R.string.onboarding_second_page)
                    )
                )
            ),
            DynamicOnBoardingFragment.newInstance(
                bundle = bundleOf(
                    KEY_ONBOARDING_MODEL to OnBoardingViewModel.OnBoardingModel(
                        contentImage = BitmapFactory.decodeResource(
                            fragment.resources,
                            R.drawable.img_happy_kids_with_alarms
                        ),
                        title = fragment.requireContext().getString(R.string.onboarding_third_page)
                    )
                )
            )
        )

    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]


    companion object {
        const val KEY_ONBOARDING_MODEL = "model"
    }

}