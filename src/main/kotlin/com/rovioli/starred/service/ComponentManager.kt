package com.rovioli.starred.service

import com.rovioli.starred.system.SystemComponent
import com.rovioli.starred.system.ThreadManager
import com.rovioli.starred.system.ThreadManager.ThreadType

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
class ComponentManager(private val threadManager: ThreadManager) : SystemComponent() {

    private val components: MutableMap<String, SystemComponent> = mutableMapOf()

    override fun getName() = "ComponentManager"

    override fun accessibleByUser() = false

    fun register(component: SystemComponent, threadType: ThreadType = ThreadType.MAIN) {
        components[component.getName()] = component
    }

    fun register(vararg newComponents: SystemComponent, threadType: ThreadType = ThreadType.MAIN) {
        newComponents.forEach { components[it.getName()] = it }
    }

    fun find(componentName: String): SystemComponent? = components[componentName]

    init {
        register(this, ThreadType.MAIN)
    }
}