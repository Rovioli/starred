package com.rovioli.starred.service

import java.util.*

/**
 * @author Vitalii Dmitriev
 * @since 27.11.2018
 */
open class AnimationState<E>(val name: String) : LinkedList<E>() {
    constructor(name: String, vararg elements: E) : this(name) {
        elements.forEach { this.push(it) }
    }
}