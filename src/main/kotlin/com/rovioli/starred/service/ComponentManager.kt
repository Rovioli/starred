package com.rovioli.starred.service

import com.rovioli.starred.system.SystemComponent

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
class ComponentManager : SystemComponent() {

    private val components: MutableMap<String, SystemComponent> = mutableMapOf()

    override fun getName() = "ComponentManager"

    override fun accessibleByUser() = false

    fun register(component: SystemComponent) {
        components[component.getName()] = component
    }

    fun register(vararg newComponents: SystemComponent) {
        newComponents.forEach { components[it.getName()] = it }
    }

    fun find(componentName: String): SystemComponent? = components[componentName]

    init {
        register(this)
    }
}