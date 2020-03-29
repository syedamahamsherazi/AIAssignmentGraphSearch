/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Syeda Maham Sherazi
 */
public class Node {

    private int state;
    private int action;
    private int cost;
    private Node parent;

    public Node(int state, int action, int cost, Node node) {
        this.state = state;
        this.action = action;
        this.cost = cost;
        this.parent = node;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getParent() {
        return parent;
    }

    
}



