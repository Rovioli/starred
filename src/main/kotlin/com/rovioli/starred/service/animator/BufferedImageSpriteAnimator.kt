package com.rovioli.starred.service.animator

import com.rovioli.starred.service.SystemComponent
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.NoSuchElementException
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class BufferedImageSpriteAnimator(private val renderThread: Thread) : Animator, SystemComponent {
    private val states = mutableMapOf<String, AnimationState<BufferedImage>>()
    private lateinit var canvas: Graphics2D
    private val run: AtomicBoolean = AtomicBoolean(false)

    fun addSequence(state: AnimationState<BufferedImage>) = {
        states[state.name] = state
        this
    }

    fun setCanvas(canvas: Graphics2D) = {
        this.canvas = canvas
        this
    }

    override fun animate(key: String) {
        // TODO: rewrite with coroutines
        run.set(true)
        renderThread.run {
            while (run.get()) {
                val state = states[key] ?: throw NoSuchElementException("There's no such state $key")
                val image = state.pop()
                canvas.drawImage(image, null, 0, 0)
                state.offerLast(image)
            }
        }
    }

    override fun stop(key: String) {
        // TODO: make it stop a particular state
        run.set(false)
    }

    override fun getName() = "BufferedImageSpriteAnimator"
}