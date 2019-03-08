package com.rovioli.starred.service.network

import com.rovioli.starred.system.SystemComponent

class ConnectionManager : SystemComponent() {
    override fun getName() = "ConnectionManager"



    override fun accessibleByUser() = true

    fun getConnection(ip: String = "localhost", port: Int = 80): Connection<out Jsonable> {
        return ServerConnection()
    }

    fun createServerConnection(ip: String = "localhost", port: Int = 80): Connection<out Jsonable> {
        return ServerConnection().connect(ip, port)
    }

    fun createPeerConnection(ip: String = "localhost", port: Int = 80): Connection<out Jsonable> {
        return ClientConnection().connect(ip, port)
    }

    fun findConnection(): Connection<out Jsonable> {

    }

    fun closeConnection(connection: Connection<out Jsonable>) {

    }

    fun closeConnection(ip: String = "localhost", port: Int = 80) {

    }
}