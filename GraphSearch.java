
import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Syeda Maham Sherazi
 */

public class GraphSearch {

    public static int noOfStates = 0;
    public static int noOfActions = 0;
    public static int noOfTestCases = 0;
    public static String[] stateDescriptions;
    public static String[] ruleDescriptions;
    public static int[][] transitionTable;
    public static String[] testCases;

    public static Queue<Node> frontier= new LinkedList<Node>();
    public static Set<Integer> exploredSet = new HashSet<Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Here in Correct Format:");
        noOfStates = sc.nextInt();
        noOfActions = sc.nextInt();
        noOfTestCases = sc.nextInt();
        stateDescriptions = new String[noOfStates];
        ruleDescriptions = new String[noOfActions];
        transitionTable = new int[noOfStates][noOfActions];
        testCases = new String[noOfTestCases];
        sc.nextLine();
        sc.nextLine();
        for (int i = 0; i < noOfStates; i++) {
            stateDescriptions[i] = sc.nextLine();
        }
        sc.nextLine();
        for (int i = 0; i < noOfActions; i++) {
            ruleDescriptions[i] = sc.nextLine();
        }
        sc.nextLine();

        for (int i = 0; i < noOfStates; i++) {
            for (int j = 0; j < noOfActions; j++) {
                transitionTable[i][j] = sc.nextInt();
            }
        }
        sc.nextLine();
        sc.nextLine();
        for (int i = 0; i < noOfTestCases; i++) {
            testCases[i] = sc.nextLine();
        }

        String[] tCase;
        Node goal;
        for(int i=0;i<noOfTestCases;i++){
            tCase = testCases[i].split("\t");
            goal=graphSearch(tCase[0], tCase[1]);
            System.out.println("");
            print(goal);            
        }
        System.out.println("");
    }
    public static Node graphSearch(String startState, String goalState){
        int startStateNum=getStateNumber(startState);
        int goalStateNum=getStateNumber(goalState);
        Node startNode=new Node(startStateNum, -1, 0, null);
        frontier.add(startNode);
        exploredSet.clear();
        while(true){
            if(frontier.isEmpty())
                return new Node(-1, -1, -1, null);
            
            Node leafNode=frontier.remove();
            if(leafNode.getState()==goalStateNum)
                return leafNode;
            
            exploredSet.add(leafNode.getState());
            Node childNodes[] = getChildNodes(leafNode);
            int i=0;
            while(i<childNodes.length){
                if(!isNodeVisited(childNodes[i]))
                    frontier.add(childNodes[i]);
                i++;
            }
        }
    }
    public static int getStateNumber(String state){
        int i=0;
        while(i<noOfStates && stateDescriptions[i].compareTo(state)!=0){
            i++;
        }
        return i;
    }
    public static Node[] getChildNodes(Node node){
        Node[] childNodes=new Node[noOfActions];
        for(int i=0;i<noOfActions;i++){
            childNodes[i]=new Node(transitionTable[node.getState()][i], i, node.getCost()+1, node);
        }
        return childNodes;
    }
    public static boolean isNodeVisited(Node node){
        if(isNodeExistInExploredSet(node) || isNodeExistInFrontier(node))
            return true;
        else
            return false;
    }
    public static boolean isNodeExistInFrontier(Node node){
        int i=0;
        Node temp;
        boolean flag=false;
        while(i<frontier.size()){
            temp=frontier.remove();
            if(node.getState()==temp.getState())
                flag = true;
            frontier.add(temp);
            i++;
        }
        return flag;    
    }
    public static boolean isNodeExistInExploredSet(Node node){
        int i=0;
        if(exploredSet.contains(node.getState()))
            return true;
        else
            return false;
    }        
    public static void print(Node node){
        if(node.getState()==-1)
            System.out.println("No Solution");
        else{
            Stack<String> s=new Stack<String>();
            while(node.getParent()!=null){
                s.push(ruleDescriptions[node.getAction()]);
                node=node.getParent();
            }
            
            for(int i=s.size()-1;i>=0;i--){
                System.out.print(s.pop());
            }
        }
    }
    
}
