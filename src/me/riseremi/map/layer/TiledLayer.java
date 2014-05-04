package me.riseremi.map.layer;

import me.riseremi.core.Bresenham;
import me.riseremi.core.Core_v1;
import me.riseremi.core.Global;
import me.riseremi.map.world.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author LPzhelud use of this class approved by the author 09.11.2012 - 9:15
 * AM
 */
public final class TiledLayer extends Layer {

    private final int[][] map;//[x][y]
    private final int[][] visiblity;//[x][y] 
    private final BufferedImage[] tiles;
    private final int tileWidth, tileHeight;
    private final int horizontalTilesNumber, verticalTilesNumber;

    public TiledLayer(BufferedImage image, int tileWidth, int tileHeight, int width, int height) {
        super(width * tileWidth, height * tileHeight);
        if (image.getWidth() / tileWidth * tileWidth != image.getWidth()
                || image.getHeight() / tileHeight * tileHeight != image.getHeight()) {
            throw new IllegalArgumentException();
        }
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.horizontalTilesNumber = width;
        this.verticalTilesNumber = height;
        tiles = chopImage(image);
        map = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = 0;
            }
        }

        visiblity = new int[width][height];
    }

    public int[][] getMap() {
        return map;
    }

    public BufferedImage[] chopImage(BufferedImage image) {
        int x = 0, y = 0;
        ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
        try {
            while (true) {
                while (true) {
                    BufferedImage subImage = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    list.add(subImage);
                    x++;
                    if ((x + 1) * tileWidth > image.getWidth()) {
                        x = 0;
                        break;
                    }
                }
                y++;
                if ((y + 1) * tileHeight > image.getHeight()) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(x);
            System.out.println(y);
        }
        return (BufferedImage[]) list.toArray(new BufferedImage[list.size()]);
    }

    public void setTile(int x, int y, int tileId) {
        map[x][y] = tileId;
    }

    public int getTile(int x, int y) {
        int tileId = 0;
        try {
            tileId = map[x][y];
        } catch (Exception ex) {
            System.out.println("Out of bounds (getTile)");
        }
        return tileId;
    }

    public int[][] getVisiblity() {
        return visiblity;
    }

    public boolean isLighted(int x, int y) {
        if (visiblity[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setVisiblity(int x, int y, int state) {
        visiblity[x][y] = state;
    }

    public void fillRectTile(int x, int y, int w, int h, int tileId) {
        for (int i = y; i < y + h; i++) {
            for (int j = x; j < w + x; j++) {
                setTile(j, i, tileId);
            }
        }
    }

    //отрисовка слоя, при этом рисуются только помещающиеся на экран тайлы
    @Override
    public void paintLayer(Graphics g) {
//        for (int i = 0; i < map.length; i++) {
//            int[] is = map[i];
//            for (int j = 0; j < is.length; j++) {
//                int k = is[j];
//                k--;
//                if (k != -1) {
//                    if (visiblity[i][j] == 1) {
//                        paintTile(g, i * tileWidth + super.getX(), j * tileHeight + super.getY(), k);
//                    }
//                }
//                //обнуление всей видимости при каждой перерисовке
//                visiblity[i][j] = 0;
//            }
//        }

        final int xStart = Math.abs(Core_v1.getInstance().getCamera().getX() / Global.tileWidth);
        final int yStart = Math.abs(Core_v1.getInstance().getCamera().getY() / Global.tileHeight);
        //новая отрисовка
        for (int i = xStart; i < Global.paintWidth + xStart; i++) {
            for (int j = yStart; j < Global.paintHeight + yStart; j++) {
//            for (int j = 0; j < Global.paintHeight; j++) {
                //if (visiblity[i - (super.getBlocksX())][j - (super.getY())] == 1) {
                try {
                    paintTile(g, i * tileWidth, j * tileHeight, map[i][j]);
//                    paintTile(g, i * tileWidth, j * tileHeight, map[i - (super.getBlocksX())][j - (super.getBlocksY())]);
                } catch (Exception ex) {
                }//paintTile(g, i * tileWidth, j * tileHeight, map[i][j]);

                //}
                //visiblity[i - (super.getX() / SimpleCrawler.tw)][j - (super.getY() / SimpleCrawler.th)] = 0;
                //обнуление всей видимости при каждой перерисовке
                //visiblity[i][j] = 0;
            }
        }

        //обнуляем
        for (int i = 0; i < Global.horizontalTiles; i++) {
            for (int j = 0; j < Global.verticalTiles; j++) {
                visiblity[i][j] = 0;
            }
        }
    }

    public void drawVisiblity(Graphics g, World world, int x, int y) {
        //x = x - world.getLayer().getBlocksX();
        y = y + world.getWorldLayer().getBlocksY();

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                visiblity[x - 5 + i][y - 5 + j] = 1;
//            }
//        }
        Bresenham.drawBresenhamLine(x, y, x, y - 5, visiblity, g);

        //visiblity[x][y] = 1;
//        visiblity[x - 1][y - 1] = 1;
//        visiblity[x][y - 1] = 1;
//        visiblity[x + 1][y - 1] = 1;
//
//        visiblity[x - 1][y] = 1;
//        visiblity[x + 1][y] = 1;
//
//        visiblity[x - 1][y + 1] = 1;
//        visiblity[x][y + 1] = 1;
//        visiblity[x + 1][y + 1] = 1;
    }

    protected void paintTile(Graphics g, int x, int y, int id) {
        g.drawImage(tiles[id], x, y, null);
        g.setColor(Color.white);
        //g.drawString("" + visiblity[x / tileWidth - super.getBlocksX()][y / tileHeight - super.getBlocksY()], x + Global.tileWidth / 2, y + Global.tileHeight / 2);
    }

    public void moveDown() {
        super.setBlocksY(super.getBlocksY() - 1);
        //Test.core.getFriend().update();
        //Core_v1.getInstance().getPlayer().moveDown();
    }

    public void moveUp() {
        super.setBlocksY(super.getBlocksY() + 1);
        //Core_v1.getInstance().getPlayer().moveUp();
    }

    public void moveLeft() {
        super.setBlocksX(super.getBlocksX() + 1);
        //Core_v1.getInstance().getPlayer().moveLeft();
    }

    public void moveRight() {
        super.setBlocksX(super.getBlocksX() - 1);
        //Core_v1.getInstance().getPlayer().moveRight();
    }
}
