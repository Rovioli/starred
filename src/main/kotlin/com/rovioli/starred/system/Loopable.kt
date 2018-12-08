package com.rovioli.starred.system

/**
 * @author Vitalii Dmitriev
 * @since 08.12.2018
 */
interface Loopable : Runnable {
    fun loop()

    fun interrupt()

    fun interrupted(): Boolean
}