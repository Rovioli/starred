package com.rovioli.starred.service.animator

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
interface Animator {
    fun animate(key: String)

    fun stop(key: String)
}