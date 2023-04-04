package com.jungle.wake_your_friends_up.ext

/**
 * Created by Oguz Evirgen on 4.04.2023.
 */


/**
 * Executes the scope internally if the object is not null.
 */
inline fun <T : Any, R : Any> withNotNull(receiver: T?, block: T.() -> R): R? {
    return receiver?.block()
}
