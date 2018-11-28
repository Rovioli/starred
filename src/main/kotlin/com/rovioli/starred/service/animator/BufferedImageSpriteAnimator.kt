package com.rovioli.starred.service.animator

import com.rovioli.starred.service.SystemComponent
import java.awt.image.BufferedImage

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class BufferedImageSpriteAnimator(private val renderThread: Thread) : Animator, SystemComponent {
    private val states = mutableMapOf<String, AnimationState<BufferedImage>>()

    fun addSequence(state: AnimationState<BufferedImage>) = {
        states[state.name] = state
        this
    }

    override fun animate(key: String) {
        renderThread.run {

        }
    }

    override fun getName() = "BufferedImageSpriteAnimator"
}