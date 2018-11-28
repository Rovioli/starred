package com.rovioli.starred

import com.rovioli.starred.exception.NoSuchComponentException
import com.rovioli.starred.service.*
import com.rovioli.starred.service.animator.BufferedImageSpriteAnimator
import com.rovioli.starred.service.audio.AudioManager
import com.rovioli.starred.service.resource.ResourceManager
import me.riseremi.core.Camera
import me.riseremi.main.Main
import me.riseremi.network.ClientSeverProtocol

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */


private lateinit var componentManager: ComponentManager
// TODO: introduce a ThreadHandler with something like looper.
private val renderThread: Thread = Thread()
fun main(args: Array<String>) {
    val resourceManager = ResourceManager()
    componentManager = ComponentManager()
    componentManager.register(
            resourceManager,
            AudioManager(resourceManager),
            Camera(),
            ClientSeverProtocol(),
            BufferedImageSpriteAnimator(renderThread)
    )

    // Launch an old main method to start a game
    Main.main(args)
}

fun getComponent(name: String) {
    componentManager.find(name) ?: throw NoSuchComponentException("Component $name is not registered.")
}