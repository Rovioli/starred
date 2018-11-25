package com.rovioli.starred

import com.rovioli.starred.exception.NoSuchComponentException
import com.rovioli.starred.service.AudioManager
import com.rovioli.starred.service.ComponentManager
import com.rovioli.starred.service.ResourceManager
import me.riseremi.core.Camera
import me.riseremi.main.Main
import me.riseremi.network.ClientSeverProtocol

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */


private lateinit var componentManager: ComponentManager

fun main(args: Array<String>) {
    val resourceManager = ResourceManager()
    componentManager = ComponentManager()
    componentManager.register(
            resourceManager,
            AudioManager(resourceManager),
            Camera(),
            ClientSeverProtocol()
    )

    // Launch an old main method to start a game
    Main.main(args)
}

fun getComponent(name: String) {
    componentManager.find(name) ?: throw NoSuchComponentException("Component $name is not registered.")
}