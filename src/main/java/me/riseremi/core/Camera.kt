package me.riseremi.core

import com.rovioli.starred.system.SystemComponent
import java.awt.Graphics2D

/**
 *
 * @author riseremi <riseremi at icloud.com>
</riseremi> */
class Camera : SystemComponent() {

    override fun getName() = "Camera"

    override fun accessibleByUser() = true

    var x: Int = 0
    var y: Int = 0

    fun translate(g: Graphics2D) {
        g.translate(x, y)
    }

    fun untranslate(g: Graphics2D) {
        g.translate(-x, -y)
    }
}
