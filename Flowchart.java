package java_robot.flowchart;

import java_robot.node.Node;

public class Flowchart {

    public Node data;      //Set data as attribute to collect Node or Binary tree
    public Node lastIF;    //Collect last if node that was create

    public static void main(String[] args) {
         Flowchart mySub = new Flowchart();
         Flowchart myFlowchart = new Flowchart();
         myFlowchart.addIFcommand("isBlocked()", "turnLeft()", "move()");

         mySub.addIFcommand("isBlocked()", "turnLeft()", "move()");
         myFlowchart.addFalseCommand(mySub);
         myFlowchart.addFalseCommand("turnRight()");
         myFlowchart.getFlow();
    }

    public Flowchart() {
        data = new Node();
    }

    public void addCommand(String args) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add command args to binary tree
      //
      /////////////////////////////////////////////////////

      if (data.command == null) {  //if first node is empty then
        data.command = args;   //add command
      } else {
        data.addRight(data, args);  //insert command to most right node
      }
    }

    public void addCommand(Flowchart subFlow) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add subFlowchart or sub Binary Tree to Main Tree
      //
      /////////////////////////////////////////////////////

      if ( data.command == null) {  //if first node is empty then
        data = subFlow.data;      //put sub Binary tree instead old data
      } else {
        data.addRight(data, subFlow.data);    //put sub Binary tree to most right data
      }
      lastIF = subFlow.lastIF;    //collect last time if that create from sub Binary Tree
    }

    public void addFalseCommand(String args) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add False command to last if that was create
      //
      /////////////////////////////////////////////////////

      lastIF.addLeft(lastIF, args);   //add command to most left from lastIF node
    }

    public void addFalseCommand(Flowchart subFlow) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add False command to alst if that was create
      //
      /////////////////////////////////////////////////////

      lastIF.addLeft(lastIF, subFlow.data);  //add sub Binary Tree to most left from lastIF node
    }

    public void addTrueCommand(String args) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add True command to a last if node that was  create
      //
      /////////////////////////////////////////////////////

      Node temp = lastIF.addRight(lastIF, args);  //add command to most right from lastIF node and collect to temp
      lastIF.endTrueNode = temp;      //add new end node that was created
    }

    public void addTrueCommand(Flowchart subFlow) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add True command to a last if node that was create
      //
      /////////////////////////////////////////////////////

      lastIF.addRight(lastIF, subFlow.data); //add subBinart Tree to most right from lastIF node
    }


    public void addIFcommand(String condition, String trueCommand, String falseCommand) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add if command to most right node
      //
      /////////////////////////////////////////////////////

      Node temp = new Node(condition, trueCommand, falseCommand); //add if command
      lastIF = temp;
      if (data.command != null ) {
        data.addRight(data, temp);    //add to most right node
      } else {
        data = temp;
      }
    }

    public void addIFcommand(String condition, Flowchart trueCommand, Flowchart falseCommand) {

      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: add if command from subTree
      //
      /////////////////////////////////////////////////////

      Flowchart temp = new Flowchart();  //create temp flowchart
      temp.addCommand(condition);    //add command condition
      temp.data.right = trueCommand.data;    //put right as true sub Binary Tree
      temp.data.left = falseCommand.data;    //put left as false sub Binary Tree
      data.addRight(data, temp.data);  //add to the most right node
    }

    public void getFlow() {
      /////////////////////////////////////////////////////
      //
      // Programmer: ThatphumCpre
      //
      // Description: view all command node that not null
      //
      /////////////////////////////////////////////////////

      getFlow(this.data);
    }

    public void getFlow(Node args) {  //view all command
      if (args != null ) {
        System.out.println(args.command);
        getFlow(args.left); //go to most left
        getFlow(args.right);    //go to most right
      }
    }

}
