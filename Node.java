package java_robot.node;

public class Node {
    public String command = null;  //Set command as attribute to collect command
    public boolean ifType;          //Set ifType to collect is it "IF" in flowchart
    public Node endTrueNode = null;    //Set endTrueNode as attribute to collect endTrueNode
    public Node left = null;    //Set left as attribute to link to left node
    public Node right = null; //Set right as attribute to link to right node

    public Node(String args) {    //create node that have command from argument
        this.command = args;
    }

    public Node() {    //create node that have command from argumen
    }

    public Node(String condition, String trueCommand, String falseCommand) { //Add if Node

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: Instance with if condition to Binary Tree
        //
        /////////////////////////////////////////////////////

        command = "IF "+condition;      //give command condition to attribute
        ifType = true;                  //give this node as "if" node
        left = new Node(falseCommand);  //create left node
        right = new Node(trueCommand);  //create right node
        endTrueNode = right;           //create endTrueNode that "IF" Node done will end with this node
    }
    public Node addRight(Node currentNode, String cmd) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: add String command to most right node
        //
        /////////////////////////////////////////////////////

        Node temp;
        if ( currentNode.right == null ) {      //if last node then create node
            currentNode.right = new Node(cmd);
            return currentNode.right;      //return and collect to temp
        } else {
            temp = currentNode.addRight(currentNode.right, cmd);  //recursive if node is not last one
        }
        System.out.println(temp);
        return temp;
    }

    public void addRight(Node currentNode, Node subTree) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: add SubTree to most right node
        //
        /////////////////////////////////////////////////////

        if (currentNode.right == null) {
            currentNode.right = subTree;    //if right note is not empty put subTree to empty node
        } else {
            currentNode.addRight(currentNode.right, subTree); //recursive if node is not last one
        }
    }

    public void addLeft(Node currentNode, String cmd) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: add command to most left node
        //
        /////////////////////////////////////////////////////

        if (currentNode.left == null) {
            currentNode.left = new Node(cmd);  //if right node is empty create a new node
        } else {
            currentNode.addLeft(currentNode.left, cmd);    //if not recursive with left node
        }
    }

    public void addLeft(Node currentNode, Node subTree) {

        /////////////////////////////////////////////////////
        //
        // Programmer: ThatphumCpre
        //
        // Description: add node to most left Node
        //
        /////////////////////////////////////////////////////

        if (currentNode.left == null) {
            currentNode.left = subTree;   //if right node is not empty put subTree to empty node
        } else {
            currentNode.addLeft(currentNode.left, subTree);  //recursive if node is not last one
        }
    }


}
