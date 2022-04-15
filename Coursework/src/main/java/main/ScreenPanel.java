package main;

import block.BlockDisplay;
import object.MainObject;
import person.Hero;
import person.Person;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int startElementSize = 16; //16 16 tile
    final int scale = 3;
    public final int elementSize = startElementSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int W = elementSize * maxScreenCol; //768 p
    public final int H = elementSize * maxScreenRow; //576 p

    //WORLD
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = elementSize * maxWorldCol;
    public final int worldHeight = elementSize * maxWorldRow;

    int FPS = 60;

    public boolean state;
    public final boolean play = true;
    public final boolean pause = false;


    BlockDisplay blockDisplay = new BlockDisplay(this);

    KeyInputChecker keyInputChecker = new KeyInputChecker(this);
    Thread gameThread;

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public MainObjectsSetter mainObjectsSetter = new MainObjectsSetter(this);

    public Interface anInterface = new Interface(this);

    public Hero hero = new Hero(this,keyInputChecker);

    public MainObject object[] = new MainObject[10];

    public Person mob[] = new Person[10];



    public ScreenPanel() {
        this.setPreferredSize(new Dimension(W, H));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInputChecker);
        this.setFocusable(true);
    }

    public void startGame() {
        mainObjectsSetter.setObject();
        mainObjectsSetter.setCivilian();
        state = play;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Sleep method of interval
        // (UPD moreover exists delta method)
        double sleepTime = 1000000000 / FPS;
        double nextRepaint = System.nanoTime() + sleepTime;

        while (gameThread != null) {
            //1) UPDATE update information for example personal positon
            update();

            //2) DRAW draw the our screen with the updated screen
            repaint();

            try {
                double timeRemain = nextRepaint - System.nanoTime();
                timeRemain = timeRemain/1000000;

                if (timeRemain < 0) {
                    timeRemain = 0;
                }

                Thread.sleep((long) timeRemain);
                nextRepaint += sleepTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update() {
        if(state == play) {
            hero.update();
            for(int i = 0; i < mob.length; i++) {
                if(mob[i] != null) {
                    mob[i].update();
                }
            }
        } else if(state == pause){

        }
    }
    
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        blockDisplay.draw(graphics2D);

        for(int i = 0; i < object.length; i++) {
            if(object[i] != null) {
                object[i].draw(graphics2D, this);
            }
        }

        for(int i = 0; i < mob.length; i++) {
            if(mob[i] != null) {
                mob[i].draw(graphics2D);
            }
        }

        hero.draw(graphics2D);

        anInterface.draw(graphics2D);

        graphics2D.dispose();
    }
}
