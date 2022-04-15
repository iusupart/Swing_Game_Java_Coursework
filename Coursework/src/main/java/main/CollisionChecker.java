package main;

import person.Person;

public class CollisionChecker {
    ScreenPanel screenPanel;

    public CollisionChecker(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
    }

    public void check(Person person) {
        int personLeftWorldX = person.worldX + person.collisionArea.x;
        int personRightWorldX = person.worldX + person.collisionArea.x + person.collisionArea.width;
        int personTopWorldY = person.worldY + person.collisionArea.y;
        int personBottomWorldY = person.worldY + person.collisionArea.y + person.collisionArea.height;

        int personLeftCol = personLeftWorldX / screenPanel.elementSize;
        int personRightCol = personRightWorldX / screenPanel.elementSize;
        int personTopRow = personTopWorldY / screenPanel.elementSize;
        int personBottomRow = personBottomWorldY / screenPanel.elementSize;

        int block1, block2;

        switch (person.direction) {
            case "up":
                personTopRow = (personTopWorldY - person.speed)/screenPanel.elementSize;
                block1 = screenPanel.blockDisplay.mapScheme[personLeftCol][personTopRow];
                block2 = screenPanel.blockDisplay.mapScheme[personRightCol][personTopRow];
                if(screenPanel.blockDisplay.block[block1].collision || screenPanel.blockDisplay.block[block2].collision) {
                    person.collisionOn = true;
                }
                break;
            case "down":
                personBottomRow = (personBottomWorldY + person.speed)/screenPanel.elementSize;
                block1 = screenPanel.blockDisplay.mapScheme[personLeftCol][personBottomRow];
                block2 = screenPanel.blockDisplay.mapScheme[personRightCol][personBottomRow];
                if(screenPanel.blockDisplay.block[block1].collision || screenPanel.blockDisplay.block[block2].collision) {
                    person.collisionOn = true;
                }
                break;
            case "left":
                personLeftCol = (personLeftWorldX - person.speed)/screenPanel.elementSize;
                block1 = screenPanel.blockDisplay.mapScheme[personLeftCol][personTopRow];
                block2 = screenPanel.blockDisplay.mapScheme[personLeftCol][personBottomRow];
                if(screenPanel.blockDisplay.block[block1].collision || screenPanel.blockDisplay.block[block2].collision) {
                    person.collisionOn = true;
                }
                break;
            case "right":
                personRightCol = (personRightWorldX + person.speed)/screenPanel.elementSize;
                block1 = screenPanel.blockDisplay.mapScheme[personRightCol][personTopRow];
                block2 = screenPanel.blockDisplay.mapScheme[personRightCol][personBottomRow];
                if(screenPanel.blockDisplay.block[block1].collision || screenPanel.blockDisplay.block[block2].collision) {
                    person.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Person person, boolean hero) {

        int index = 999;

        for (int i = 0; i < screenPanel.object.length; i++) {
            if(screenPanel.object[i] != null) {
                person.collisionArea.x = person.worldX + person.collisionArea.x;
                person.collisionArea.y = person.worldY + person.collisionArea.y;

                screenPanel.object[i].collisionArea.x = screenPanel.object[i].worldX + screenPanel.object[i].collisionArea.x;
                screenPanel.object[i].collisionArea.y = screenPanel.object[i].worldY + screenPanel.object[i].collisionArea.y;

                switch (person.direction) {
                    case "up":
                        person.collisionArea.y -= person.speed;
                        if(person.collisionArea.intersects(screenPanel.object[i].collisionArea)){
                            if(screenPanel.object[i].collision == true) {
                                person.collisionOn = true;
                            }
                            if(hero == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        person.collisionArea.y += person.speed;
                        if(person.collisionArea.intersects(screenPanel.object[i].collisionArea)){
                            if(screenPanel.object[i].collision == true) {
                                person.collisionOn = true;
                            }
                            if(hero == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        person.collisionArea.x -= person.speed;
                        if(person.collisionArea.intersects(screenPanel.object[i].collisionArea)){
                            if(screenPanel.object[i].collision == true) {
                                person.collisionOn = true;
                            }
                            if(hero == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        person.collisionArea.y += person.speed;
                        if(person.collisionArea.intersects(screenPanel.object[i].collisionArea)){
                            if(screenPanel.object[i].collision == true) {
                                person.collisionOn = true;
                            }
                            if(hero == true) {
                                index = i;
                            }
                        }
                        break;
                }

                person.collisionArea.x = person.collisionAreaDefaultX;
                person.collisionArea.y = person.collisionAreaDefaultY;
                screenPanel.object[i].collisionArea.x = screenPanel.object[i].collisionAreaDefaultX;
                screenPanel.object[i].collisionArea.y = screenPanel.object[i].collisionAreaDefaultY;
            }
        }

        return index;
    }
}
