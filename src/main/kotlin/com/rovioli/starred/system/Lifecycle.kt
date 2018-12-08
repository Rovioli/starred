package com.rovioli.starred.system

/**
 * @author Vitalii Dmitriev
 * @since 08.12.2018
 */
interface Lifecycle {

    fun onStart()

    fun onUpdate(delta: Int = 0, vararg args: String)

    fun onStop()
}