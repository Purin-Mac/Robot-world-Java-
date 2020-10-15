package java_robot.inputprocessor;

import java_robot.robot.Robot;      //import Robot
import java_robot.wall.Wall;    //import Wall

public class InputProcessor{
    private char moveKey, turnLeftKey, turnRightKey;
    private Robot robot;
    private Wall[] wall;
    private boolean save = false;

    public InputProcessor(Robot robot, Wall[] wall){ //if it doesn't have input key
        this.moveKey = 'w';
        this.turnLeftKey = 'a';
        this.turnRightKey = 'd';
        this.robot = robot;
        this.wall = wall;
    }

    public InputProcessor(char move, char turnLeft, char turnRight, Robot robot, Wall[] wall){ //when have input key
        this.moveKey = move;
        this.turnLeftKey = turnLeft;
        this.turnRightKey = turnRight;
    }

    public void checkMove(char inputKey, int worldRow, int worldColumn, int maxWall){ //check input for moving robot and save file
        if (inputKey == moveKey){ //if input key equal to move key
            for (int i = 0; i < maxWall; i++){ //check with all wall
                if (robot.getDegree() == 0 && robot.getRow() + 1 == wall[i].getRow() && robot.getColumn() == wall[i].getColumn()){ //when head point to east and hit wall
                    break;          
                } else if (robot.getDegree() == 0 && robot.getRow() + 1 == worldRow){ //when head point to east and hit edge
                    break;
                } else if (robot.getDegree() == 90 && robot.getRow() == wall[i].getRow() && robot.getColumn() + 1 == wall[i].getColumn()){ //when head point to south and hit wall
                    break;
                } else if (robot.getDegree() == 90 && robot.getColumn() + 1 == worldColumn){ //when head point to south and hit edge
                    break;
                } else if (robot.getDegree() == 180 && robot.getRow() - 1 == wall[i].getRow() && robot.getColumn() == wall[i].getColumn()){ //when head point to west and hit wall
                    break;
                } else if (robot.getDegree() == 180 && robot.getRow() - 1 < 0){ //when head point to west and hit edge
                    break;
                } else if (robot.getDegree() == 270 && robot.getRow() == wall[i].getRow() && robot.getColumn() - 1 == wall[i].getColumn()){ //when head point to north and hit wall
                    break;
                } else if (robot.getDegree() == 270 && robot.getColumn() - 1 < 0){ //when head point to north and hit edge
                    break;
                } else if (i == maxWall - 1) {
                    robot.move();
                }
            }
        } else if (inputKey == turnLeftKey){ //if input equal to turn left key
            robot.turnLeft();
        } else if (inputKey == turnRightKey){ //if input equal to turn right key
            robot.turnRight();
        } else if ((int)inputKey == 65535) { //if input equal to F1 (integer of input F1 key is 65535)
            save = true;
        }
    }

    public char getMoveKey(){
        return moveKey;
    }

    public char getLeftKey(){
        return turnLeftKey;
    }

    public char getRightKey(){
        return turnRightKey;
    }

    public boolean getSave(){
        return save;
    }

    public void setSave(boolean saveState){
        save = saveState;
    }
}
