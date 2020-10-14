package java_robot.robot;

import java.awt.Graphics; 
import java.awt.Color;  //import to use Color
import java.util.Random;  //import to use random

public class Robot {
    private int row,column;
    private int degree;
    private Random rand = new Random();

    public Robot(){  //if no argument random position
        row = rand.nextInt(12);
        column = rand.nextInt(12);
        degree = 0;

    }

    public Robot(int row,int column){  //add position
        this.row = row ;
        this.column = column;
        degree = 0;

    }

    public void drawRobot(Graphics graphics){
        graphics.setColor(Color.DARK_GRAY);  //set Color is Dark Gray
        graphics.fillRect(720*row/12+720/72,720*column/12+720/72,40,40);  //draw att row and column
    }

    public void move(){
        if ( degree==90 ){
            column++;
        }
        else if (degree==270){
            column--;
        }
        else if (degree ==180 ){
            row--;
        }
        else {
            row++;
        }
    }

    public void turnLeft(){
        degree += 270;
        if(degree >= 360){ //if over 360 then reset
            degree -= 360;

        }
            System.out.println("now head is "+degree);
    }

    public void turnRight(){
        degree += 90;
        if(degree >= 360){  //if over 360 then reset
            degree -= 360;

        }
            System.out.println("now headis is "+degree);    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

}
