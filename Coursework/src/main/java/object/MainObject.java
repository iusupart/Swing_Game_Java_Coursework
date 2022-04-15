package object;

import main.ScreenPanel;
import main.Tool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle collisionArea = new Rectangle(0,0,48,48);
    public int collisionAreaDefaultX = 0;
    public int collisionAreaDefaultY = 0;
    Tool tool = new Tool();

    public void draw(Graphics2D graphics2D, ScreenPanel screenPanel) {

        int screenX = worldX - screenPanel.hero.worldX + screenPanel.hero.screenX;
        int screenY= worldY - screenPanel.hero.worldY + screenPanel.hero.screenY;

        if(screenPanel.hero.screenX > screenPanel.hero.worldX) {
            screenX = worldX;
        }

        if(screenPanel.hero.screenY > screenPanel.hero.worldY) {
            screenY = worldY;
        }

        if((screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX){
            screenX = (screenPanel.maxScreenCol * screenPanel.elementSize) - (screenPanel.worldWidth - worldX);
        }

        if((screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY) {
            screenY = (screenPanel.maxScreenRow * screenPanel.elementSize) - (screenPanel.worldHeight - worldY);
        }


        if(worldX + screenPanel.elementSize> screenPanel.hero.worldX - screenPanel.hero.screenX &&
                worldX - screenPanel.elementSize< screenPanel.hero.worldX + screenPanel.hero.screenX &&
                worldY + screenPanel.elementSize > screenPanel.hero.worldY - screenPanel.hero.screenY &&
                worldY - screenPanel.elementSize < screenPanel.hero.worldY + screenPanel.hero.screenY){

            graphics2D.drawImage(image, screenX, screenY, screenPanel.elementSize, screenPanel.elementSize, null);
        } else if(screenPanel.hero.screenX > screenPanel.hero.worldX || screenPanel.hero.screenY > screenPanel.hero.worldY || (screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX || (screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY){
            graphics2D.drawImage(image, screenX, screenY, screenPanel.elementSize, screenPanel.elementSize, null);
        }
    }
}
