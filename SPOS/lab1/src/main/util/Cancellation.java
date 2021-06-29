package main.util;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cancellation extends JFrame implements KeyListener {

    public Cancellation() {
        setSize(0, 0);
        setUndecorated(true);
        setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_Q | e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            System.out.println("Cancelled");
            System.exit(0);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}