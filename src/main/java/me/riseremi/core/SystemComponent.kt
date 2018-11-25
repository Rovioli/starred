package me.riseremi.core

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
interface SystemComponent {
    fun onStart() {}

    fun onStop() {}

    fun getName(): String
}