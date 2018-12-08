package com.rovioli.starred.system

/**
 * @author Vitalii Dmitriev
 * @since 08.12.2018
 */
class ThreadManager : SystemComponent() {
    enum class ThreadType {
        RENDER, ANIMATION, RESOURCE, MAIN, WORKER
    }

    override fun getName() = "ThreadManager"

    override fun accessibleByUser() = false

    override fun onStart() {

    }
}