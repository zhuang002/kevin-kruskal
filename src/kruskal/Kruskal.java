/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class Kruskal {

    static private ArrayList<Path> graph=new ArrayList();
    static private ArrayList<Integer[]> subtrees= new ArrayList();
    static private ArrayList<Path> spanTree=new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        readInput();
        processSpanTree();
        printPathLength();
    }

    /**
     * read input into graph.
     */
    private static void readInput() {
        
    }

    /**
     * Build the spanning tree.
     */
    private static void processSpanTree() {
        while (true) {
            ArrayList<Path> shortestPaths=getShortPaths();
            if (shortestPaths.isEmpty()) break;
            for (Path path:shortestPaths) {
                if (shouldAddToSpanTree(path)) {
                    addToSpanTree(path);
                }
            }
        }
    }

    /**
     * Print the generated spanning tree from spanTree.
     */
    private static void printPathLength() {
    }

    /**
     * Get the left shortest paths from graph.
     * @return return a list of paths that has the shortest distance.
     */
    private static ArrayList<Path> getShortPaths() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    /**
     * Add a path to the spanTree and subTrees
     * @param path 
     */
    private static void addToSpanTree(Path path) {
        
    }

    /**
     * Check if any of the subtrees contains the node.
     * @param node The node to check
     * @return true for there is one subtree contains the node, false for there is no subtree contains the node.
     */
    private static boolean subtreesContains(int node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get the root id of the subtree that contains the node.
     * @param node the node to find its subtree.
     * @return the root id of the subtree if node is in a subtree, otherwise return -1.
     */
    private static int getSubtree(int node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Check if a new path should be added to the span tree. 
     * @param path the path to be checked.
     * @return true if should be added, otherwise false.
     */
    private static boolean shouldAddToSpanTree(Path path) {
        if (!subtreesContains(path.node1)|| !subtreesContains(path.node2)) {
            return true;
        } 
        if (getSubtree(path.node1)!= getSubtree(path.node2)) {
            return true;
        }
        return false;
    }
    
}

/**
 * The class that contains a path info.
 * @author zhuan
 */
class Path implements Comparable {
    public int node1; // one end of a path.
    public int node2; // the other end of a path.
    public int length; // the length of the path.

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}