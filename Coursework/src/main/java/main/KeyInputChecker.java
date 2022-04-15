package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputChecker implements KeyListener {
    ScreenPanel screenPanel;

    public boolean UP, DOWN, LEFT, RIGHT;
    public KeyInputChecker (ScreenPanel screenPanel){
        this.screenPanel = screenPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W) {
            UP = true;
        }
        if(code == KeyEvent.VK_S) {
            DOWN = true;
        }
        if(code == KeyEvent.VK_A) {
            LEFT = true;
        }
        if(code == KeyEvent.VK_D) {
            RIGHT = true;
        }
        if(code == KeyEvent.VK_SPACE) {
            if(screenPanel.state == screenPanel.play){
                screenPanel.state = screenPanel.pause;
            } else if(screenPanel.state == screenPanel.pause){
                screenPanel.state = screenPanel.play;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            UP = false;
        }
        if(code == KeyEvent.VK_S) {
            DOWN = false;
        }
        if(code == KeyEvent.VK_A) {
            LEFT = false;
        }
        if(code == KeyEvent.VK_D) {
            RIGHT = false;
        }
    }
}
