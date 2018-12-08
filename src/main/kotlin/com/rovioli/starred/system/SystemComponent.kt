package com.rovioli.starred.system

/**
 * @author Vitalii Dmitriev
 * @since 24.11.2018
 */
interface SystemComponent {
    fun getName(): String

    fun accessibleByUser(): Boolean
}