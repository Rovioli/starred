package com.rovioli.starred.service.audio

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
interface Playable<T> {

    fun play(source: T, repeat: Boolean = false)

    fun isPlaying(): Boolean

    fun stop()
}