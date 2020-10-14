package java_robot.main;

import java_robot.world.World;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("ROBOT WORLD"); //create window
        World myWorld = new World(12,12);   //create world 12*12
        window.setSize(720, 740);  //setSize is 720*740
        window.setTitle("ROBOT WORLD"); //set Title name
        window.setResizable(false);    //can't resize window
        window.add(myWorld);       //add myWorld widget
        window.setVisible(true);   //set display
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //set exit when close
    }

}
