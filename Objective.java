package java_robot.objective;

import java.awt.Graphics; //import to draw geometric
import java.awt.Color; //import to use Color
import java.util.Random; //import to use random

public class Objective {
    private int row,column;  //set row,column as attribute
    private Random rand = new Random(); //instance Random to use random

    public Objective(){  //if no argument random
        row = rand.nextInt(12);
        column = rand.nextInt(12);

    }

    public Objective(int row, int column){
        this.row = row;
        this.column = column;

    }

    public void drawObjective(Graphics graphics){
        graphics.setColor(Color.pink); //fill with pink color
        graphics.fillRect(720*row/12+720/72,720*column/12+720/72,40,40); //draw object
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }
}
