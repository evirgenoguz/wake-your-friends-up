package com.jungle.wake_your_friends_up.ext

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.jungle.wake_your_friends_up.R

/**
 * Created by Burak Taşcı on 2.03.2023.
 */

/**
 * It finds the NavController of FragmentContainerView object which has passed id.
 */
fun AppCompatActivity.findNavController(
    @IdRes id: Int = R.id.nav_host_fragment_content
): NavController {
    val hostFragment = supportFragmentManager.findFragmentById(id)
            as NavHostFragment
    return hostFragment.navController
}