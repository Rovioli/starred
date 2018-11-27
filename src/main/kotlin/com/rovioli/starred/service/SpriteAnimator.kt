package com.rovioli.starred.service

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class SpriteAnimator<E> : Animator, SystemComponent {
    private val states = mutableMapOf<String, AnimationState<E>>()

    fun addSequence(state: AnimationState<E>) = {
        states[state.name] = state
        this
    }

    override fun animate(key: String) {

    }

    override fun getName() = "SpriteAnimator"

}