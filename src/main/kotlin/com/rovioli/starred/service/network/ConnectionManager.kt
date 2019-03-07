package com.rovioli.starred.service.network

import com.rovioli.starred.system.SystemComponent

class ConnectionManager : SystemComponent() {
    override fun getName() = "ConnectionManager"


    override fun accessibleByUser() = true

    fun getConnection(ip: String = "localhost", port: Int = 80): Connection<out Jsonable> {
        return GameConnection()
    }

    fun createConnection(ip: String = "localhost", port: Int = 80): Connection<out Jsonable> {
        return GameConnection()
    }

    fun closeConnection(connection: Connection<out Jsonable>) {

    }

    fun closeConnection(ip: String = "localhost", port: Int = 80) {

    }
}