package com.rovioli.starred.service.audio

import javazoom.jl.player.FactoryRegistry
import javazoom.jl.player.advanced.AdvancedPlayer
import java.io.InputStream

/*
 * Unlike the JavaFX solution, it compiles.
 * Just silently doesn't work.
 * https://coub.com/view/1045mt
 */
class Player : Playable<InputStream>, Runnable {

    lateinit var player: AdvancedPlayer
    private var isRunning = false

    override fun run() {
        println("current thread: ${Thread.currentThread().name}")
        isRunning = true
        player.play()
    }

    /**
     * @param repeat is actually ignored. TODO: use the repeat, luke.
     */
    override fun play(source: InputStream, repeat: Boolean) {
        player = AdvancedPlayer(source, FactoryRegistry.systemRegistry().createAudioDevice())
        Thread(this, "SoundThread").start()
    }

    override fun isPlaying() = isRunning

    override fun stop() {
        player.stop()
        isRunning = false
    }
}