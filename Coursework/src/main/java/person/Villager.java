package person;

import main.ScreenPanel;

import java.util.Random;

public class Villager extends Person{



    public Villager(ScreenPanel screenPanel) {
        super(screenPanel);

        direction = "down";
        speed = 1;

        getVillagerImage();
    }

    public void getVillagerImage() {
        up = setup("/villager/npc");
        up1 = setup("/villager/npc");
        down = setup("/villager/npc");
        down1 = setup("/villager/npc");
        left = setup("/villager/npc");
        left1 = setup("/villager/npc");
        right = setup("/villager/npc");
        right1 = setup("/villager/npc");
    }


        public void mobRandom() {

        updatesCounter++;

        if(updatesCounter == 150) {
            Random random = new Random();
            int dirRandom = random.nextInt(1000) + 1;

            if(dirRandom <= 250) {
                direction = "up";
            } else if(dirRandom > 250 && dirRandom <= 500){
                direction = "down";
            } else  if(dirRandom > 500 && dirRandom <= 750){
                direction = "left";
            } else if(dirRandom > 750 && dirRandom <= 1000){
                direction = "right";
            }
            updatesCounter = 0;
        }

    }

}
