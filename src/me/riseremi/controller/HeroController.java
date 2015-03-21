package me.riseremi.controller;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import me.riseremi.core.Core_v1;
import me.riseremi.entities.Entity;
import me.riseremi.entities.Player;
import me.riseremi.map.world.CheckObstacles;
import me.riseremi.map.world.World;
import me.riseremi.network.messages.MessageSetPosition;
import org.rising.framework.network.Client;

/**
 *
 * @author remi
 */
public class HeroController {

    private static Random rnd = new Random();

    public static void heroController(Player player, World world, KeyEvent ke) throws CloneNotSupportedException, IOException {
        //System.out.println("in controller");
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
//                core.getWorld().getWorldLayer().moveDown();
//                network.sendData(new NetworkMessage(NetworkMessage.PLAYER_MOVEMENT, "d"));

                //network.sendData(new NetworkMessage(NetworkMessage.SET_POSITION, id, x, y + 1));
                //TODO
                /*
                 next string: rewrite Client.getId() to work with connection
                 instead of player Entity
                 */
//                Client.getInstance().send(new MessageSetPosition(Client.getInstance().getId(), x1, y1 + 1));
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1, y1 + 1));
                //Core_v1.getInstance().getCamera().addY(-Global.tileHeight);
                player.decreaseActionPoint(Entity.MOVE_COST);
            }

            //
            if (ke.getKeyCode() == KeyEvent.VK_UP
                    && !(CheckObstacles.checkObstacle(world, hero_xx, hero_yy - 1))
                    && player.canDoIt(Entity.MOVE_COST)) {
                //core.getWorld().getWorldLayer().moveUp();
//                network.sendData(new NetworkMessage(NetworkMessage.PLAYER_MOVEMENT, "u"));

//                network.sendData(new NetworkMessage(NetworkMessage.SET_POSITION, id, x, y - 1));
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1, y1 - 1));
                player.decreaseActionPoint(Entity.MOVE_COST);
            }
            //
            if (ke.getKeyCode() == KeyEvent.VK_LEFT
                    && !(CheckObstacles.checkObstacle(world, hero_xx - 1, hero_yy))
                    && player.canDoIt(Entity.MOVE_COST)) {
                //core.getWorld().getWorldLayer().moveLeft();
//                network.sendData(new NetworkMessage(NetworkMessage.PLAYER_MOVEMENT, "l"));

//                network.sendData(new NetworkMessage(NetworkMessage.SET_POSITION, id, x - 1, y));
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1 - 1, y1));
                player.decreaseActionPoint(Entity.MOVE_COST);
            }
            //
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT
                    && !(CheckObstacles.checkObstacle(world, hero_xx + 1, hero_yy))
                    && player.canDoIt(Entity.MOVE_COST)) {
                //core.getWorld().getWorldLayer().moveRight();
//                network.sendData(new NetworkMessage(NetworkMessage.PLAYER_MOVEMENT, "r"));

//                network.sendData(new NetworkMessage(NetworkMessage.SET_POSITION, id, x + 1, y));
                //network.sendData(new SetPositionMessage(playerId, x + 1, y));
                Client.getInstance().send(new MessageSetPosition(player1.getId(), x1 + 1, y1));
                player.decreaseActionPoint(Entity.MOVE_COST);
            }
            //end turn
            if (ke.getKeyCode() == KeyEvent.VK_F11) {
                try {
                    core.endTurn();
                } catch (IOException ex) {
                }
            }
        }

//        //попробуем создать вещь по нажатию C и передать её на другой конец
//        if (ke.getKeyCode() == KeyEvent.VK_C) {
//            if (Core_v1.getInstance().getWorld().getItems().size() < 15) {
//                int id = rnd.nextInt(6);
//                int x = rnd.nextInt(30);
//                int y = rnd.nextInt(30);
//
//                Item itm = new Item(TempNameSwitch.switchName(id) + " (id: " + String.valueOf(id) + ")", id, x, y);
//
//                if (!CheckObstacles.checkObstacle(Core_v1.getInstance().getWorld(), x, y)) {
//                    Core_v1.getInstance().getWorld().getItems().add(itm);
//                    //готовим вещь к пересылке
//                    synchronized (Core_v1.getInstance().getWorld().getItems()) {
//                        Core_v1.getInstance().getNetwork().sendData(new NetworkMessage(NetworkMessage.SEND_ITEM, String.valueOf(itm.getId()) + ":" + String.valueOf(itm.getX()) + ":" + String.valueOf(itm.getY())));
//                        System.out.println("Item sent.");
//                    }
//                } else {
//                    System.out.println("Cannot spawn more than 15 items.");
//                }
//            }
//        }
//        //get item
//        if (ke.getKeyCode() == KeyEvent.VK_G) {
//            ArrayList<Item> inv = Core_v1.getInstance().getPlayer().getInventory();
//            ArrayList<Item> items = Core_v1.getInstance().getWorld().getItems();
//            Player p = Core_v1.getInstance().getPlayer();
//            int x = p.getX(), y = p.getY();
//
//            synchronized (Core_v1.getInstance().getWorld().getItems()) {
//                //поиск вещи на карте мира, добавление в инвентарь, пересылка, удаление
//                for (Item item : items) {
//                    if ((item.getX() == x) && (item.getY() == y)) {
//                        if (inv.size() < p.getInvSize()) {
//                            inv.add(item);
//                            network.sendData(new NetworkMessage(NetworkMessage.REMOVE_ITEM, String.valueOf(items.indexOf(item))));
//                            items.remove(item);
//                        }
//                        break;
//                    }
//                }
//            }
//        }
    }
}
