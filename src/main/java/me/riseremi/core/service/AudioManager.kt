package me.riseremi.core.service

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
class AudioManager(private val resourceManager: ResourceManager) : SystemComponent {
    override fun getName() = "AudioManager"

    init {
        // TODO: preload sounds here
    }

    fun findAudio(name: String): Playable {
        return object : Playable {
            override fun play(repeat: Boolean) {}

            override fun isPlaying(): Boolean = false

            override fun stop() {}
        }
    }

    fun play(name: String, repeat: Boolean = false) {}
}