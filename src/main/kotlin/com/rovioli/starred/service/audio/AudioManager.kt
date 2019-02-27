package com.rovioli.starred.service.audio

import com.rovioli.starred.service.resource.ResourceManager
import com.rovioli.starred.system.SystemComponent

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
class AudioManager(private val resourceManager: ResourceManager) : Playable<String>, SystemComponent() {

    private val player: Player = Player()

    override fun getName() = "AudioManager"

    override fun accessibleByUser() = true

    init {
        // TODO: preload sounds here
    }

    override fun play(source: String, repeat: Boolean) {
        player.play(resourceManager.loadStream("sounds/$source"), repeat)
    }

    override fun isPlaying() = player.isPlaying()

    override fun stop() {
        player.stop()
    }
}