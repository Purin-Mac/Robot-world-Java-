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
        int widthPerBlock = (int)(720/12); // calculate space of block
        int heightPerBlock =(int)(720/12);
        if ( degree==90 ){ //head point to south
            graphics.drawPolygon(new int[] {widthPerBlock*row + 10, widthPerBlock*row + widthPerBlock/2, widthPerBlock*row + widthPerBlock - 10}, 
            new int[] {heightPerBlock*column + 10, heightPerBlock*column + heightPerBlock - 10, heightPerBlock*column + 10}, 3); 
            //[left side of base, head, right side of base]
        }
        else if (degree==270){ //head point to north 
            graphics.drawPolygon(new int[] {widthPerBlock*row + 10, widthPerBlock*row + widthPerBlock/2, widthPerBlock*row + widthPerBlock - 10}, 
            new int[] {heightPerBlock*column + heightPerBlock - 10, heightPerBlock*column + 10, heightPerBlock*column + heightPerBlock - 10}, 3);
            //[left side of base, head, right side of base]
        }
        else if (degree ==180 ){ //head point to west
            graphics.drawPolygon(new int[] {widthPerBlock*row + 10, widthPerBlock*row + widthPerBlock - 10, widthPerBlock*row + widthPerBlock - 10}, 
            new int[] {heightPerBlock*column + heightPerBlock/2, heightPerBlock*column + 10, heightPerBlock*column + heightPerBlock - 10}, 3);
            //[head, right side of base, left side of base]
        }
        else { //head point to east
            graphics.drawPolygon(new int[] {widthPerBlock*row + 10, widthPerBlock*row + widthPerBlock - 10, widthPerBlock*row + 10}, 
            new int[] {heightPerBlock*column + 10, heightPerBlock*column + heightPerBlock/2, heightPerBlock*column + heightPerBlock - 10}, 3);
            //[left side of base, head, right base]
        }
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
