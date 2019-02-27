package com.rovioli.starred.service.resource

import com.rovioli.starred.system.SystemComponent
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
class ResourceManager : SystemComponent() {

    enum class ResourceType {
        AUDIO, IMAGE, STRING
    }

    override fun getName() = "ResourceManager"

    override fun accessibleByUser() = true

    @Throws(IOException::class)
    fun loadImage(resName: String): BufferedImage {
        return ImageIO.read(ClassLoader.getSystemResourceAsStream(resName))
    }

    fun loadUrl(resName: String): URL = ClassLoader.getSystemResource(resName)

    fun loadStream(resName: String): InputStream = ClassLoader.getSystemResourceAsStream(resName)
}