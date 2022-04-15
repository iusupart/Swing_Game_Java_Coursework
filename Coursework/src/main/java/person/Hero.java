package person;

import main.KeyInputChecker;
import main.ScreenPanel;
import main.Tool;
import object.ObjectDoor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hero extends Person {

    KeyInputChecker keyInputChecker;

    public final int screenX;
    public final int screenY;

    public int keys = 0;

    public Hero(ScreenPanel screenPanel, KeyInputChecker keyInputChecker) {

        super(screenPanel);

        this.keyInputChecker = keyInputChecker;

        screenX = screenPanel.W/2 - (screenPanel.elementSize/2);
        screenY = screenPanel.H/2 - (screenPanel.elementSize/2);

        collisionArea = new Rectangle();
        collisionArea.x = 8;
        collisionArea.y = 16;
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;
        collisionArea.width = 32;
        collisionArea.height = 32;

        setDefaultValues();
        getHeroImage();
    }

    public void setDefaultValues() {
        worldX = screenPanel.elementSize * 23;
        worldY = screenPanel.elementSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getHeroImage() {
        up = setup("/hero/pers7");
        up1 = setup("/hero/pers8");
        down = setup("/hero/pers1");
        down1 = setup("/hero/pers2");
        left = setup("/hero/pers4");
        left1 = setup("/hero/pers6");
        right = setup("/hero/pers3");
        right1 = setup("/hero/pers5");
    }


    public void update() {

        if(keyInputChecker.UP || keyInputChecker.DOWN || keyInputChecker.LEFT || keyInputChecker.RIGHT) {
            if(keyInputChecker.UP) {
                direction = "up";
            } else if(keyInputChecker.DOWN) {
                direction = "down";
            } else if(keyInputChecker.LEFT) {
                direction = "left";
            } else if(keyInputChecker.RIGHT) {
                direction = "right";
            }

            collisionOn = false;
            screenPanel.collisionChecker.check(this);

            int objectIndex = screenPanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

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

            spriteCounter++;
            if(spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if(index != 999) {

            String ObjectName = screenPanel.object[index].name;

            switch (ObjectName) {
                case "Key":
                    keys++;
                    screenPanel.object[index] = null;
                    screenPanel.anInterface.alertShow("NIIIICE ITS A KEEEY");
                    break;
                case "Door":
                    if (keys > 0) {
                        screenPanel.object[index] = null;
                        keys--;
                        screenPanel.anInterface.alertShow("Door was opened");
                    } else {
                        screenPanel.anInterface.alertShow("No keys!");
                    }
                    break;
                case "Boots":
                    speed += 3;
                    screenPanel.object[index] = null;
                    screenPanel.anInterface.alertShow("U collected da boots");
                    break;

                case "Chest":
                    screenPanel.anInterface.finishTheGame = true;
            }
        }
    }
}
