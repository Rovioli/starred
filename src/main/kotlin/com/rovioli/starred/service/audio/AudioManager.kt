package com.rovioli.starred.service.audio

import com.rovioli.starred.service.resource.ResourceManager
import com.rovioli.starred.system.SystemComponent

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
class AudioManager(private val resourceManager: ResourceManager) : SystemComponent() {

    private val player: Player = Player()

    override fun getName() = "AudioManager"

    override fun accessibleByUser() = true

    init {
        // TODO: preload sounds here
    }

    fun play(name: String, repeat: Boolean = false) {
        player.play(resourceManager.loadStream("sounds/lobby"))
    }
}