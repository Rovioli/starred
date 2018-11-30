package me.riseremi.entities

import me.riseremi.core.Global
import me.riseremi.ui.HPBar


/**
 *
 * @author riseremi <riseremi at icloud.com>
 */
class Friend//@Getter @Setter private boolean isServer;
(name: String, imgId: Int, id: Int, type: Entity.Type)//HARDCODE
    : Entity(name, imgId, id, 0, 572 - HPBar.INDENT - HPBar.BAR_HEIGHT, Global.WINDOW_WIDTH, type)
