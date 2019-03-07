package com.rovioli.starred.service.network

interface MessageListener<T> {
    fun onMessage(message: T)
}