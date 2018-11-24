package me.riseremi.core

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
class ComponentManager : SystemComponent("ComponentManager") {
    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val components: MutableMap<String, SystemComponent> = mutableMapOf()

    fun register(component: SystemComponent) {
    }

    fun find(componentName: String) : SystemComponent? = components[componentName]
}