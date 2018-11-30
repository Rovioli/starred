package me.riseremi.entities

import me.riseremi.cards.Card
import me.riseremi.core.Core_v1
import me.riseremi.core.Global

/**
 * @author riseremi <riseremi at icloud.com>
 */
class Player(name: String, imgId: Int, id: Int, type: Entity.Type) : Entity(name, imgId, id, 0, 0, Global.WINDOW_WIDTH, type) {
    // Card picked from the hand.
    // Range selection will be based on the range of this card.
    private var raisedCard: Card? = null

    fun setRaisedCard(raisedCard: Card) {
        if (this.actionPoints >= raisedCard.apcost) {
            this.raisedCard = raisedCard
            Core_v1.getInstance().isTileSelectionMode = true
        }
    }

    fun getRaisedCard(): Card? {
        return raisedCard
    }

    fun resetRaisedCard() {
        this.raisedCard = null
    }
}
