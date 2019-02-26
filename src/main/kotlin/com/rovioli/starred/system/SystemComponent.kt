package com.rovioli.starred.system

import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
abstract class SystemComponent : Loopable, Lifecycle {
    private val interrupted: AtomicBoolean = AtomicBoolean(true)

    abstract fun getName(): String

    abstract fun accessibleByUser(): Boolean

    final override fun run() {
        interrupted.set(false)
        onStart()
    }

    final override fun loop() {
        onUpdate()
    }

    final override fun interrupt() {
        interrupted.set(true)
        onStop()
    }

    final override fun interrupted(): Boolean = interrupted.get()

    override fun onStart() {}

    override fun onUpdate(delta: Int, vararg args: String) {}

    override fun onStop() {}
}