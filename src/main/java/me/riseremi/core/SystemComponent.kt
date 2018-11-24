package me.riseremi.core

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
abstract class SystemComponent(
        val name: String/*
        val resources: Resources
        */
) {
    abstract fun onStart()

    abstract fun onStop()
}