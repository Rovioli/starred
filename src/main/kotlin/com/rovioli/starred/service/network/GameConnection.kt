package com.rovioli.starred.service.network

class GameConnection : Connection<Jsonable> {
    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(ip: String, port: Int): Connection<Jsonable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disconnect(ip: String, port: Int): Connection<Jsonable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isConnected(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun send(message: Jsonable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listen(listener: MessageListener<Jsonable>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}