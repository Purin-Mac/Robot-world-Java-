package java_robot.main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
            JFrame window = new JFrame("ROBOT WORLD"); //create window
            window.setSize(720, 740);  //setSize is 720*740
            window.setTitle("ROBOT WORLD"); //set Title name
            window.setResizable(false);    //can't resize window
            window.setVisible(true);   //set display
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //set exit when close
    }

}
