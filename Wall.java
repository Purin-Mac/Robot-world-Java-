package java_robot.wall;

import java.awt.Graphics;  //import to draw geometric
import java.awt.Color; //import to use Color

public class Wall {
    private int row,column; //set attribute

    public Wall(int row,int column){  //constructor to set row and column

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: insert row and column
        //
        /////////////////////////////////////////////////////

        this.row = row;
        this.column = column;

    }

    public void drawWall(Graphics graphics){

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: draw Wall from input Class graphics
        //
        /////////////////////////////////////////////////////

        graphics.setColor(new Color(150,200,200));  //green color
        graphics.fillRect(720*row/12,720*column/12,59,59); //fill the block
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }
}
