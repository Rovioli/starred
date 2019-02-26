package com.rovioli.starred.service.audio

import java.io.InputStream

// TODO: have a look
// https://docs.oracle.com/javase/tutorial/sound/index.html
class Player : Playable<InputStream>, Runnable {

    override fun run() {

    }

    override fun play(source: InputStream, repeat: Boolean) {
        run()
    }

    override fun isPlaying(): Boolean {
        return false
    }

    override fun stop() {
        // interrupt
    }
}