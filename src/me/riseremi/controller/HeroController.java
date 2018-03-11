package me.riseremi.controller;

import me.riseremi.core.Core_v1;
import me.riseremi.entities.Entity;
import me.riseremi.entities.Player;
import me.riseremi.map.world.CheckObstacles;
import me.riseremi.map.world.World;
import me.riseremi.network.messages.MessageSetPosition;
import org.rising.framework.network.Client;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author riseremi <riseremi at icloud.com>
 */
public class HeroController {

    private static Random rnd = new Random();

    public static void heroController(Player player, World world, KeyEvent ke) throws CloneNotSupportedException, IOException {
        int hero_xx = player.getX();
        final int y1 = player.getY();
        final int x1 = player.getX();
        int hero_yy = y1;

        //экземпляр клиента или сервера
        Core_v1 core = Core_v1.getInstance();
        Entity player1 = core.getPlayer();
        int x = player1.getX();
        int y = player1.getY();

        if (player.isCanMove()) {
            if (ke.getKeyCode() == KeyEvent.VK_DOWN
                    && !(CheckObstacles.checkObstacle(world, hero_xx, hero_yy + 1))
                    && player.canDoIt(Entity.MOVE_COST)) {
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1, y1 + 1));
                player.subtractActionPoints(Entity.MOVE_COST);
            }

            if (ke.getKeyCode() == KeyEvent.VK_UP
                    && !(CheckObstacles.checkObstacle(world, hero_xx, hero_yy - 1))
                    && player.canDoIt(Entity.MOVE_COST)) {
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1, y1 - 1));
                player.subtractActionPoints(Entity.MOVE_COST);
            }
            //
            if (ke.getKeyCode() == KeyEvent.VK_LEFT
                    && !(CheckObstacles.checkObstacle(world, hero_xx - 1, hero_yy))
                    && player.canDoIt(Entity.MOVE_COST)) {
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1 - 1, y1));
                player.subtractActionPoints(Entity.MOVE_COST);
            }
            //
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT
                    && !(CheckObstacles.checkObstacle(world, hero_xx + 1, hero_yy))
                    && player.canDoIt(Entity.MOVE_COST)) {
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1 + 1, y1));
                player.subtractActionPoints(Entity.MOVE_COST);
            }
            //end turn
            if (ke.getKeyCode() == KeyEvent.VK_F11) {
                try {
                    core.endTurn();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
