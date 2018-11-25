package me.riseremi.core.service

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
interface Playable {

    fun play(repeat: Boolean = false)

    fun isPlaying(): Boolean

    fun stop()
}