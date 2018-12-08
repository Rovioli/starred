package com.rovioli.starred.service.animator

import com.rovioli.starred.system.SystemComponent
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
            val state = states[key] ?: throw NoSuchElementException("There's no such state $key")
            state.play()
            while (run.get() && state.isPlaying()) {
                val image = state.pop()
                canvas.drawImage(image, null, state.x, state.y)
                state.offerLast(image)
            }
            state.stop()
        }
    }

    override fun stop() {
        run.set(false)
    }

    override fun stop(key: String) {
        states[key]?.stop()
    }

    override fun getName() = "BufferedImageSpriteAnimator"

    override fun accessibleByUser() = true
}