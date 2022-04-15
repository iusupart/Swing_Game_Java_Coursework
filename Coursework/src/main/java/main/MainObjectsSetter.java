package main;

import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;
import person.Villager;

public class MainObjectsSetter {
    ScreenPanel screenPanel;

    public MainObjectsSetter(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
    }

    public void setObject() {

        screenPanel.object[0] = new ObjectKey(screenPanel);
        screenPanel.object[0].worldX = 23 * screenPanel.elementSize;
        screenPanel.object[0].worldY = 7 * screenPanel.elementSize;

        screenPanel.object[1] = new ObjectKey(screenPanel);
        screenPanel.object[1].worldX = 22 * screenPanel.elementSize;
        screenPanel.object[1].worldY = 38 * screenPanel.elementSize;

        screenPanel.object[2] = new ObjectKey(screenPanel);
        screenPanel.object[2].worldX = 37 * screenPanel.elementSize;
        screenPanel.object[2].worldY = 7 * screenPanel.elementSize;

        screenPanel.object[3] = new ObjectDoor(screenPanel);
        screenPanel.object[3].worldX = 10 * screenPanel.elementSize;
        screenPanel.object[3].worldY = 11 * screenPanel.elementSize;

        screenPanel.object[4] = new ObjectDoor(screenPanel);
        screenPanel.object[4].worldX = 8 * screenPanel.elementSize;
        screenPanel.object[4].worldY = 28 * screenPanel.elementSize;

        screenPanel.object[5] = new ObjectDoor(screenPanel);
        screenPanel.object[5].worldX = 12 * screenPanel.elementSize;
        screenPanel.object[5].worldY = 22 * screenPanel.elementSize;

        screenPanel.object[6] = new ObjectChest(screenPanel);
        screenPanel.object[6].worldX = 10 * screenPanel.elementSize;
        screenPanel.object[6].worldY = 7 * screenPanel.elementSize;

        screenPanel.object[7] = new ObjectBoots(screenPanel);
        screenPanel.object[7].worldX = 37 * screenPanel.elementSize;
        screenPanel.object[7].worldY = 42 * screenPanel.elementSize;
    }

    public void setEnemy() {

    }

    public void setCivilian() {
        screenPanel.mob[0] = new Villager(screenPanel);
        screenPanel.mob[0].worldX = screenPanel.elementSize * 21;
        screenPanel.mob[0].worldY = screenPanel.elementSize * 19;
    }
}
