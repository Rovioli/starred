package me.riseremi.core

import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
class ResourceManager : SystemComponent {
    override fun getName() = "ResourceManager"

    @Throws(IOException::class)
    fun loadImage(resName: String): BufferedImage {
        return ImageIO.read(ClassLoader.getSystemResourceAsStream(resName))
    }

    fun loadUrl(resName: String): URL = ClassLoader.getSystemResource(resName)

    fun loadStream(resName: String): InputStream = ClassLoader.getSystemResourceAsStream(resName)
}