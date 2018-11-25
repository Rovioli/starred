package me.riseremi.utils

import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

// All these methods aren't really null-safe

@Throws(IOException::class)
@Deprecated(message = "Use ResourceManager instead")
fun loadImage(resName: String): BufferedImage {
    return ImageIO.read(ClassLoader.getSystemResourceAsStream(resName))
}

@Deprecated(message = "Use ResourceManager instead")
fun loadUrl(resName: String): URL = ClassLoader.getSystemResource(resName)

@Deprecated(message = "Use ResourceManager instead")
fun loadStream(resName: String): InputStream = ClassLoader.getSystemResourceAsStream(resName)