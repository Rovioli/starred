package com.rovioli.starred.service.network

class ClientConnection : Connection<Jsonable> {
    override fun close() { }

    override fun connect(ip: String, port: Int): Connection<Jsonable> { return this }

    override fun disconnect(ip: String, port: Int): Connection<Jsonable> { return this }

    override fun isConnected(): Boolean { return false }

    override fun send(message: Jsonable) { }

    override fun listen(listener: MessageListener<Jsonable>) { }
}