package com.rovioli.starred.service.audio

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer

/*
 * TODO: have a look
 * https://docs.oracle.com/javase/tutorial/sound/index.html
 * This https://www.oracle.com/technetwork/java/javase/certconfig-2095354.html
 * says that I can't compile JavaFX on RHEL (including Fedora).
 * Write once, run everywhere, but fedora.
 */
class Player : Playable<String>, Runnable {

    private lateinit var mediaPlayer: MediaPlayer
    private var isRunning = false

    override fun run() {
        isRunning = true
    }

    override fun play(source: String, repeat: Boolean) {
        mediaPlayer = MediaPlayer(Media(source))
        mediaPlayer.play()
        Thread(this).start()
    }

    override fun isPlaying() =
            isRunning && mediaPlayer.status == MediaPlayer.Status.PLAYING

    override fun stop() {
        mediaPlayer.stop()
        isRunning = false
    }
}