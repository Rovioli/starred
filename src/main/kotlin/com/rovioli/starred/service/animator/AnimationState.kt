package com.rovioli.starred.service.animator

import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class AnimationState<E>(val name: String) : LinkedList<E>() {
    var x: Int = 0
    var y: Int = 0
    private var isPlaying = AtomicBoolean()

    fun play() {
        isPlaying.set(true)
    }

    fun stop() {
        isPlaying.set(false)
    }

    fun isPlaying() = isPlaying.get()

    constructor(name: String, vararg elements: E) : this(name) {
        elements.forEach { this.push(it) }
    }
}