package com.rovioli.starred.service.animator

import com.rovioli.starred.service.SystemComponent

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class SpriteAnimator<E>(private val renderThread: Thread) : Animator, SystemComponent {
    private val states = mutableMapOf<String, AnimationState<E>>()

    fun addSequence(state: AnimationState<E>) = {
        states[state.name] = state
        this
    }

    override fun animate(key: String) {

    }

    override fun getName() = "SpriteAnimator"
}