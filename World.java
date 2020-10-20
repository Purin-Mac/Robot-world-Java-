package java_robot.world;

import java_robot.robot.Robot;      //import Robot
import java_robot.objective.Objective;    //import Objective
import java_robot.wall.Wall;    //import Wall
import java_robot.inputprocessor.InputProcessor;    //import InputProcessor

import java.awt.Color;  //import to use Color
import java.awt.Graphics;   //import to draw geometric
import java.awt.event.ActionEvent;  //import to use active panel
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.util.Random;  //import to use random


import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;  // Import the IOException class to handle errors

/* World Class use create Robot World widget */

public class World extends JPanel implements KeyListener, ActionListener {

    private int row,column; //set row,column as attribute to collect world size
    private Robot myRobot;   //set myRobot as object of Robot
    private Objective myObjective; //set myObjective as object of Robot
    private int totalWall;  //set totalWall as attribute to collect wall quantitity
    private Wall[] myWall;  //set myWall as array object of Wall
    private InputProcessor myInputProcessor; //set myInput as object of InputProcessor
    private Random rand = new Random();  //instance Random to use random
    private char move, turnLeft, turnRight; //set move, turnLeft, turnRight as attribute to collect key input

    public World(int row,int column) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: insert row,column and create wall,object,robot that no overlap
        //
        /////////////////////////////////////////////////////

        totalWall = (int)row*column/3; //calculate quantitity of wall
        myWall = new Wall[totalWall];  //set array size
        this.row=row;       //collect row
        this.column=column; //collect column
        this.myRobot = new Robot();     //instance Robot
        this.myObjective = new Objective(); //instance Objective
        int created = 0;
        while(created < totalWall){       //instance each Wall with no overlap with objective and robot
            int rand1 = rand.nextInt(12);
            int rand2 = rand.nextInt(12);
            if(rand1 != myRobot.getRow() && rand2 != myRobot.getColumn() && rand1 != myObjective.getRow() && rand2 != myObjective.getColumn()){
                myWall[created] = new Wall(rand1,rand2);
                created++;
            }
        }
        this.myInputProcessor = new InputProcessor(myRobot, myWall);
        addKeyListener(this);  //add class know keyPressed
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    public World(String filename) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: Read save that from savewWorld.txt file
        //
        /////////////////////////////////////////////////////
        
      try {
        int line = 1;
        int createdWall = 0;
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if (line == 1 ){  //read world row and column  then collect <-- world=x,y
              String[] splited = data.split("=");
              String[] dataline = splited[1].split(",");
              row = Integer.valueOf(dataline[0]);
              column = Integer.valueOf(dataline[1]);
          }

          else if (line == 2 ){ //read Robot attribute  <-- robot=x,y
              String[] splited = data.split("=");
              String[] dataline = splited[1].split(",");
              myRobot = new Robot(Integer.valueOf(dataline[0]),Integer.valueOf(dataline[1]));
          }

          else if (line == 3 ){   //read Objective attribute <-- objective=x,y
              String[] splited = data.split("=");
              String[] dataline = splited[1].split(",");
              myObjective = new Objective(Integer.valueOf(dataline[0]),Integer.valueOf(dataline[1]));
          }

          else if (line == 4 ){  //read wall quantitity or array size <-- wall=x
              String[] splited = data.split("=");
              myWall = new Wall[Integer.valueOf(splited[1])];
              totalWall = Integer.valueOf(splited[1]);
          }

          else if (line == 4 + totalWall + 1){ //read Input moveKey <-- move
              String[] splited = data.split("=");
              this.move = splited[1].charAt(0);
          }

          else if (line == 4 + totalWall + 2){ //read Input leftKey <-- turnLeft
            String[] splited = data.split("=");
            this.turnLeft = splited[1].charAt(0);
          }

          else if (line == 4 + totalWall + 3){ //read Input rightKey <-- turnRight
            String[] splited = data.split("=");
            this.turnRight = splited[1].charAt(0);
            this.myInputProcessor = new InputProcessor(move, turnLeft, turnRight, myRobot, myWall);
          }

          else {  //read each Wall attribute  <-- x,y
              String[] dataline = data.split(",");
              myWall[createdWall] = new Wall(Integer.valueOf(dataline[0]),Integer.valueOf(dataline[1]));
              createdWall++;
          }

          line++;
        }
        myReader.close();
      }
      catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

      addKeyListener(this);         //add Class know keyPressed
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paint(Graphics graphics) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: draw each component
        //
        /////////////////////////////////////////////////////

        //background
        graphics.setColor(Color.white);  //draw white background
        graphics.fillRect(0, 0, 720, 720);
        myObjective.drawObjective(graphics); //draw objective
        myRobot.drawRobot(graphics);  //draw myRobot
        for(int i = 0; i<totalWall;i++){  //draw each wall
            myWall[i].drawWall(graphics);
        }
        this.drawLine(graphics);        //draw world line

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        myInputProcessor.checkMove(ke.getKeyChar(), row, column, totalWall); //check input key
        if (myInputProcessor.getSave() == true){
            this.saveWorld("worldsave.txt");    //save world data
            myInputProcessor.setSave(false);
        }
        repaint();   //draw again
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

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: draw grid line
        //
        /////////////////////////////////////////////////////

        int widthPerBlock = (int)(720/row);  //calculate space of block
        int heightPerBlock =(int)(720/column);
        graphics.setColor(Color.DARK_GRAY);
        for(int i=0;i<=row;i++){
            graphics.fillRect(widthPerBlock*i,0,1,720); //draw vertical line
        }
        for(int i=0;i<=column;i++){
            graphics.fillRect(0,heightPerBlock*i,720,1); //draw horizontal line
        }
    }

    public void saveWorld(String filename){

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: write  all attribute to "filename"
        //
        /////////////////////////////////////////////////////
        try {
            File save = new File(filename);  //create filename.txt
            save.createNewFile();       //create File
            FileWriter mySave = new FileWriter(filename);       //create Writer Filename.txt
            mySave.write("world="+this.row+","+this.column+"\n");       //writer world data
            mySave.write("ROBOT="+myRobot.getRow()+","+myRobot.getColumn()+"\n"); //writer robot data
            mySave.write("Objective="+myObjective.getRow()+","+myObjective.getColumn()+"\n"); //wrte objective data
            mySave.write("wall="+totalWall+"\n");  //write quantitity of wall
            for (int i=0;i<totalWall;i++){
                mySave.write(myWall[i].getRow()+","+myWall[i].getColumn()+"\n"); //wirte each wall
            }
            mySave.write("MoveKey="+myInputProcessor.getMoveKey()+"\n"); //write move key
            mySave.write("LeftKey="+myInputProcessor.getLeftKey()+"\n"); //write turn left key
            mySave.write("RightKey="+myInputProcessor.getRightKey()+"\n"); //write turn right key
            mySave.close();     //close file
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}
