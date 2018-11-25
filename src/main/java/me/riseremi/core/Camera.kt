package me.riseremi.core

import me.riseremi.core.service.SystemComponent
import java.awt.Graphics2D

/**
 *
 * @author riseremi <riseremi at icloud.com>
</riseremi> */
class Camera : SystemComponent {

    override fun getName() = "Camera"

    var x: Int = 0
    var y: Int = 0

    fun translate(g: Graphics2D) {
        g.translate(x, y)
    }

    fun untranslate(g: Graphics2D) {
        g.translate(-x, -y)
    }
}
