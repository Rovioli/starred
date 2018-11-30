package me.riseremi.entities

import me.riseremi.cards.Card
import me.riseremi.cards.CardsArchive
import me.riseremi.cards.Hand
import me.riseremi.core.Camera
import me.riseremi.core.Core_v1
import me.riseremi.core.Global
import me.riseremi.main.Main
import me.riseremi.map.world.World
import me.riseremi.ui.HPBar

import java.awt.*
import java.awt.image.BufferedImage
import java.io.IOException

import me.riseremi.utils.loadImage

/**
 * @author riseremi <riseremi at icloud.com>
</riseremi> */
open class Entity(name: String, /*String pathToSprite,*/ imgId: Int, var id: Int, barX: Int, barY: Int, maxWidth: Int, var type: Type?) {

    var hp: Int = 0
        private set
    var maxHp: Int = 0
        private set
    private val pDef: Int
    var name: String
    //координаты измеряются в тайлах
    var x: Int = 0
    var y: Int = 0 //straight from the top-left corner, 0:0, no offset
    private var sprite: BufferedImage? = null
    private var isPaint = false
    var actionPoints: Int = 0
        private set
    val hand: Hand
    var isCanMove = true
    val hpBar: HPBar
    var imgId: Int = 0
        private set
    private var classId: Int = 0
    private val CLASS_NAMES = arrayOf("Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter", "Mage", "Blood Mage", "Head Hunter")

    val isDead: Boolean
        get() = hp <= 0

    private fun setSprite(sprite: BufferedImage) {
        this.sprite = sprite
    }

    private fun setPaint(isPaint: Boolean) {
        this.isPaint = isPaint
    }

    fun setClassId(classId: Int) {
        this.classId = classId
    }

    enum class Type {

        PLAYER, NOT_PLAYER, CREEP, NONE
    }

    init {
        hpBar = HPBar(barX, barY, HPBar.INDENT, maxWidth, HPBar.BAR_HEIGHT)
        this.imgId = imgId
        this.classId = imgId
        this.hand = Hand()
        this.actionPoints = 10
        this.maxHp = 30
        this.hp = this.maxHp
        this.pDef = 0
        this.setPaint(true)
        //this.setName(name + " (id: " + id + ")");
        this.name = name
        try {
            setSprite(loadImage("sprites/hero$imgId.png"))
        } catch (ex: IOException) {
            println("cannot load sprite")
        }

    }

    //отрисовка героя и его друга, isPaint - рисовать ли (не рисуем, пока друг не подключился к игре)
    //координаты друга транслируем в соответствии с положением карты (абсолютная коорд друга + абсолютная коорд мира)
    //герой, за которого мы играем, всегда в центре экрана
    fun paint(g: Graphics, world: World, isFriend: Boolean) {
        val instance = Core_v1.getInstance()
        val xo: Int
        val yo: Int
        xo = x * Global.tileWidth
        yo = y * Global.tileHeight

        if (isPaint) {
            g.drawImage(sprite!!.getScaledInstance(Global.tileWidth, Global.tileHeight, 0), xo, yo, null)
            g.color = Color.WHITE
            //int nameLen2 = g.getFontMetrics().stringWidth(name + " (id: " + id + ")") / 2;

            val name1 = name
            val name2 = name + " (id: " + id + ") + x/y: " + x + "/" + y + " : " + instance.player.id + " " + CLASS_NAMES[classId]
            g.drawString(if (Main.ENABLE_DEBUG_TOOLS) name2 else name1,
                    xo + 16 - g.fontMetrics.stringWidth(if (Main.ENABLE_DEBUG_TOOLS) name2 else name1) / 2, yo - 8)
        }
    }

    fun setImage(imgId: Int) {
        try {
            this.setSprite(loadImage("sprites/hero$imgId.png"))
            this.imgId = imgId
            this.classId = imgId
        } catch (ignored: IOException) {
        }

    }

    @JvmOverloads
    fun setPosition(x: Int, y: Int, check: Boolean = true) {
        this.x = x
        this.y = y
        val core = Core_v1.getInstance()

        if (check && id == core.player.id) {
            val camera = core.camera
            camera.x = -(x * Global.tileWidth - Global.CENTER_X * Global.tileWidth)
            camera.y = -(y * Global.tileHeight - Global.CENTER_Y * Global.tileHeight)
        }
    }

    fun canDoIt(cost: Float): Boolean {
        return actionPoints - cost >= 0
    }

    fun subtractActionPoints(cost: Float) {
        actionPoints -= cost.toInt()
    }

    private fun decreaseBloodCostHP(cost: Int) {
        hp = if (hp - cost < 0) 0 else hp - cost
    }

    fun resetActionPoints() {
        actionPoints = if (actionPoints > 0) 10 + 1 else 10
    }

    private fun dealPhysicalDamage(amount: Int) {
        Main.addToChat("Got " + (amount - pDef) + " damage\r\n")
        hp = if (hp - (amount - pDef) < 0) 0 else hp - (amount - pDef)
    }

    private fun heal(power: Int) {
        hp = if (hp + power <= maxHp) hp + power else maxHp
    }

    private fun addAPInNextTurn(value: Int) {
        actionPoints += value
    }

    private fun drawCards(value: Int) {
        for (i in 0 until value) {
            val card = CardsArchive.instance.getRandomCard()
            hand.addCard(card!!.toDrawableCard())
            Core_v1.getInstance().incrementCardsDrawn()
        }
    }

    private fun undrawCards(value: Int) {
        for (i in 0 until value) {
            hand.removeLastCard()
        }
    }

    fun applyEffects(card: Card, target: Entity) {
        card.effects.forEach { effect ->
            val type = effect.getEffectType()
            val value = effect.getValue()

            when (type) {
                Card.Companion.EffectType.DAMAGE -> target.dealPhysicalDamage(value)
                Card.Companion.EffectType.DRAW_CARD -> drawCards(value)
                Card.Companion.EffectType.UNDRAW_CARD -> target.undrawCards(value)
                Card.Companion.EffectType.HEAL -> target.heal(value)
                Card.Companion.EffectType.ADD_AP -> target.addAPInNextTurn(value)
                Card.Companion.EffectType.BLOODCOST -> decreaseBloodCostHP(value)
                else -> Main.addToChat("BUT NOTHING HAPPENED\r\n")
            }
        }
    }

    companion object {
        //action costs
        val MOVE_COST = 1f
    }


}
