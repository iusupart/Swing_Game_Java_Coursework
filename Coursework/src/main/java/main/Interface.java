package main;

import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Interface {

    ScreenPanel screenPanel;

        Font arial_40, win;
        Graphics2D graphics2D;
        BufferedImage image;
        public boolean alertOn = false;
        public String alert = "";
        int count = 0;
        public boolean finishTheGame = false;


    public Interface(ScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        win = new Font("Arial", Font.BOLD, 80);
        ObjectKey key = new ObjectKey(screenPanel);
        image = key.image;
    }

    public void alertShow(String textOfAlert) {
        alert = textOfAlert;
        alertOn = true;
    }

    public void drawHP() {

    }

    public void draw(Graphics2D graphics2D) {

        this.graphics2D = graphics2D;

        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);

        if(screenPanel.state == screenPanel.play){
            drawPlay();
        } else if(screenPanel.state == screenPanel.pause){
            drawPause();
        }
    }

    public void drawPlay() {
        if (finishTheGame) {

            graphics2D.setFont(win);
            graphics2D.setColor(Color.yellow);

            String text;
            int textLength;
            int centerX;
            int centerY;


            text = "You won!";
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            centerX = screenPanel.W/2 - textLength/2;
            centerY = screenPanel.H/2 + screenPanel.elementSize/2;
            graphics2D.drawString(text, centerX, centerY);

            screenPanel.gameThread = null;

        } else {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(image, screenPanel.elementSize/2, screenPanel.elementSize/2, screenPanel.elementSize, screenPanel.elementSize, null);
            graphics2D.drawString("x = "+ screenPanel.hero.keys, 74, 50);

            if (alertOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(alert, screenPanel.W/2 - 50, screenPanel.H - 100);

                count++;

                if(count > 160) {
                    count = 0;
                    alertOn = false;
                }

            }
        }
    }

    public void drawPause() {
        String text = "PAUSE";
        String textCont = "Continue";
        String textLeave = "Save and exit";
        int x;
        int y;
        int textLength;

        int xCont;
        int yCont;
        int textLenghtCont;

        int xLeave;
        int yLeave;
        int textLenghtLeave;

        textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        x = screenPanel.W/2 - textLength/2;
        y = screenPanel.H/2 + screenPanel.elementSize/2 - 100;

        textLenghtCont = (int)graphics2D.getFontMetrics().getStringBounds(textCont, graphics2D).getWidth();
        xCont = screenPanel.W/2 - textLenghtCont/2;
        yCont = screenPanel.H/2 + screenPanel.elementSize/2;

        textLenghtLeave = (int)graphics2D.getFontMetrics().getStringBounds(textLeave, graphics2D).getWidth();
        xLeave = screenPanel.W/2 - textLenghtLeave/2;
        yLeave = screenPanel.H/2 + screenPanel.elementSize/2 + 100;

        graphics2D.drawString(text, x, y);
        graphics2D.drawString(textCont,xCont, yCont);
        graphics2D.drawString(textLeave, xLeave, yLeave);
    }
}
