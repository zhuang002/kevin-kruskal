/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author zhuan
 */
public class Kruskal {

    static private ArrayList<Path> graph = new ArrayList();
    static private ArrayList<Set> subTrees = new ArrayList();
    static private ArrayList<Path> spanTree = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        readInput();
        processSpanTree();
        printPathLength();
        
        /* Example for set.
        HashSet<Integer> set=new HashSet();
        set.add(node1);
        set.add(node2);
        subTrees.add(set);*/
    }

    /**
     * read input into graph.
     */
    private static void readInput() {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int path = sc.nextInt();
        for (int i = 0; i < path; i++) {
            Path p = new Path();
            p.node1 = sc.nextInt();
            p.node2 = sc.nextInt();
            p.length = sc.nextInt();
            graph.add(p);
        }
        Collections.sort(graph);
        subTrees = new int[nodes];
        for (int i = 0; i < subTrees.length; i++) {
            subTrees[i] = -1;
        }
    }

    /**
     * Build the spanning tree.
     */
    private static void processSpanTree() {
        while (true) {
            ArrayList<Path> shortestPaths = getShortPaths();
            if (shortestPaths.isEmpty()) {
                break;
            }
            for (Path path : shortestPaths) {
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
        int sum = 0;
        for (int i = 0; i < spanTree.size(); i++) {
            sum += spanTree.get(i).length;
        }
        System.out.println(sum);
    }

    /**
     * Get the left shortest paths from graph.
     *
     * @return return a list of paths that has the shortest distance.
     */
    private static ArrayList<Path> getShortPaths() {
        ArrayList<Path> sPath = new ArrayList();
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(0).length == graph.get(i).length) {
                sPath.add(graph.get(i));
            } else {
                break;
            }
        }
        graph.removeAll(sPath);
        return sPath;
    }

    /**
     * Add a path to the spanTree and subTrees
     *
     * @param path
     */
    private static void addToSpanTree(Path path) {
        spanTree.add(path);
        if (subTrees[path.node1] == -1) {
            if (subTrees[path.node2] == -1) {
                subTrees[path.node1] = path.node1;
                subTrees[path.node2] = path.node1;
            } else {
                subTrees[path.node1] = subTrees[path.node2];
            }
        } else {
            if (subTrees[path.node2] == -1) {
                subTrees[path.node2] = subTrees[path.node1];
            } else {
                int r1 = subTrees[path.node1];
                int r2 = subTrees[path.node2];
                for (int i = 0; i < subTrees.length; i++) {
                    if (subTrees[i] == r2) {
                        subTrees[i] = r1;
                    }
                }
            }
        }
    }

    /**
     * Check if any of the subtrees contains the node.
     *
     * @param node The node to check
     * @return true for there is one subtree contains the node, false for there
     * is no subtree contains the node.
     */
    private static boolean subtreesContains(int node) {
        return subTrees[node] != -1;
    }

    /**
     * Get the root id of the subtree that contains the node.
     *
     * @param node the node to find its subtree.
     * @return the root id of the subtree if node is in a subtree, otherwise
     * return -1.
     */
    private static int getSubtree(int node) {
        return subTrees[node];
    }

    /**
     * Check if a new path should be added to the span tree.
     *
     * @param path the path to be checked.
     * @return true if should be added, otherwise false.
     */
    private static boolean shouldAddToSpanTree(Path path) {
        if (!subtreesContains(path.node1) || !subtreesContains(path.node2)) {
            return true;
        }
        if (getSubtree(path.node1) != getSubtree(path.node2)) {
            return true;
        }
        return false;
    }

}

/**
 * The class that contains a path info.
 *
 * @author zhuan
 */
class Path implements Comparable {

    public int node1; // one end of a path.
    public int node2; // the other end of a path.
    public int length; // the length of the path.

    @Override
    public int compareTo(Object o) {
        return this.length - ((Path) o).length;
    }
}
