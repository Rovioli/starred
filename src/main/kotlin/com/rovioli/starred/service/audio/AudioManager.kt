package com.rovioli.starred.service.audio

import com.rovioli.starred.service.resource.ResourceManager
import com.rovioli.starred.system.SystemComponent
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

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
        player.play(resourceManager.loadUrl("sounds/$name").toString(), repeat)
    }
}