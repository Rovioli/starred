package com.rovioli.starred.service.network

import java.io.Closeable

interface Connection<T> : Closeable {
    fun connect(ip: String = "localhost", port: Int = 80): Connection<T>

    fun disconnect(ip: String = "", port: Int = 80): Connection<T>

    fun isConnected(): Boolean

    fun send(message: T)

    fun listen(listener: MessageListener<T>)
}