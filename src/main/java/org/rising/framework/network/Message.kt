package org.rising.framework.network

import java.io.Serializable

/**
 * @author riseremi <riseremi at icloud.com>
 */
abstract class Message(val type: Type) : Serializable {

    enum class Type {
        SET_PLAYER_NAME, SET_FRIEND_NAME, SET_NAME, CHAT_MESSAGE, PURE_ATTACK_TEST,
        TURN_END, ATTACK, SET_PLAYER_ID, SET_FRIEND_ID, SET_POSITION, CREATE_PLAYER,
        CREATE_NOT_PLAYER, CONNECT, PING_MESSAGE, GAMEOVER_MESSAGE,
        ADD_TO_THE_LOBBY, GO, SET_ICON_ID
    }

    abstract fun processServer(message: Message)

    abstract fun processClient(message: Message)

    companion object {
        private const val serialVersionUID = -7838861924374435661L
    }
}
