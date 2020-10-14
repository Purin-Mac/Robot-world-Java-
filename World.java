package java_robot.world;


import java.awt.Color;      //import to use Color
import java.awt.Graphics;       //import to draw geometric
import java.awt.event.ActionEvent;  //import to use active panel
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.util.Random;  //import to use random



/* World Class use create Robot World widget */

public class World extends JPanel implements KeyListener, ActionListener {

    private int row,column; //set row,column as attribute to collect world size
    private int totalWall;  //set totalWall as attribute to collect wall quantitity
    private Random rand = new Random();  //instance Random to use random

    public World(int row,int column) {
        totalWall = (int)row*column/3; //calculate quantitity of wall
        this.row=row;       //collect row
        this.column=column; //collect column

        addKeyListener(this);  //add class know keyPressed
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }



    @Override
    public void paint(Graphics graphics) {
        //background
        graphics.setColor(Color.white);  //draw white background
        graphics.fillRect(0, 0, 720, 720);

        this.drawLine(graphics);        //draw world line
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();  //draw everytime
    }


    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public  void drawLine(Graphics graphics){
        int widthPerBlock = (int)(720/row);  //calculate space of block
        int heightPerBlock =(int)(720/column);
        for(int i=0;i<=row;i++){
            graphics.setColor(Color.black);
            graphics.fillRect(widthPerBlock*i,0,1,720); //draw vertical line
        }
        for(int i=0;i<=column;i++){
            graphics.setColor(Color.black);
            graphics.fillRect(0,widthPerBlock*i,720,1); //draw horizontal line
        }
    }



}
