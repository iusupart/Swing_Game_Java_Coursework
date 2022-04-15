package person;

import main.ScreenPanel;
import main.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Person {

    ScreenPanel screenPanel;
    public BufferedImage image;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up, up1, down, down1, left, left1, right, right1;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle collisionArea = new Rectangle(0,0,48,48);

    public int collisionAreaDefaultX, collisionAreaDefaultY;
    public boolean collisionOn = false;

    public int updatesCounter = 0;


    public Person(ScreenPanel screenPanel) {
         this.screenPanel = screenPanel;
    }

    public void mobRandom() {

    }

    public void update() {
        mobRandom();
        collisionOn = false;
        screenPanel.collisionChecker.check(this);
        if(collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {

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

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up;
                    }
                    if(spriteNum == 2){
                        image = up1;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down;
                    }
                    if(spriteNum == 2){
                        image = down1;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left;
                    }
                    if(spriteNum == 2){
                        image = left1;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = right;
                    }
                    if(spriteNum == 2){
                        image = right1;
                    }
                    break;
            }

            int x = screenX;
            int y = screenY;

            if(screenX > worldX) {
                x = worldX;
            }
            if(screenY > worldY) {
                y = worldY;
            }

            if((screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX){
                x = (screenPanel.maxScreenCol * screenPanel.elementSize) - (screenPanel.worldWidth - worldX);
            }

            if((screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY) {
                y = (screenPanel.maxScreenRow * screenPanel.elementSize) - (screenPanel.worldHeight - worldY);
            }


            graphics2D.drawImage(image, screenX, screenY, screenPanel.elementSize, screenPanel.elementSize, null);
        } else if(screenPanel.hero.screenX > screenPanel.hero.worldX || screenPanel.hero.screenY > screenPanel.hero.worldY || (screenPanel.maxScreenCol * screenPanel.elementSize) - screenPanel.hero.screenX > screenPanel.worldWidth - screenPanel.hero.worldX || (screenPanel.maxScreenRow * screenPanel.elementSize) - screenPanel.hero.screenY > screenPanel.worldHeight - screenPanel.hero.worldY){
            graphics2D.drawImage(image, screenX, screenY, screenPanel.elementSize, screenPanel.elementSize, null);
        }
    }

    public BufferedImage setup(String imagePath){
        Tool tool = new Tool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            scaledImage = tool.scaleImage(scaledImage, screenPanel.elementSize, screenPanel.elementSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }
}
