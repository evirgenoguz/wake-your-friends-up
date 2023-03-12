package com.jungle.wake_your_friends_up.ui.components

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import com.jungle.wake_your_friends_up.databinding.ViewAnimatedLinearProgressIndicatorBinding


/**
 *
 * A view to have animation for [com.google.android.material.progressindicator.LinearProgressIndicator].
 * It animates between progress changes.
 *
 * Created by Burak Taşcı on 3.03.2023.
 */
class AnimatedLinearProgressIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = ViewAnimatedLinearProgressIndicatorBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private var progress: Int = 0

    init {
        // sets initial progress value
        binding.linearProgressIndicator.progress = progress
    }

    fun updateProgress(target: Int) {
        // Animation declaration
        val animation = ObjectAnimator.ofInt(binding.linearProgressIndicator, "progress", progress, target)
        animation.duration = 1000 // 3.5 second
        animation.interpolator = DecelerateInterpolator()
        animation.start()
        // Sets the new progress end of the animation
        animation.doOnEnd {
            progress = target
        }
    }

}