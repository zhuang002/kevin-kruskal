/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
        Set<Integer> s1 = getSubtree(path.node1);
        Set<Integer> s2 = getSubtree(path.node2);
        if (s1 == null) {
            if (s2 == null) {
                Set<Integer> s = new TreeSet();
                s.add(path.node1);
                s.add(path.node2);
                subTrees.add(s);
            } else {
                s2.add(path.node1);
            }
        } else {
            if (s2 == null) {
                s1.add(path.node2);
            } else {
                s1.addAll(s2);
                subTrees.remove(s2);
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
        for (int i = 0; i < subTrees.size(); i++) {
            if (subTrees.get(i).contains(node)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the root id of the subtree that contains the node.
     *
     * @param node the node to find its subtree.
     * @return the root id of the subtree if node is in a subtree, otherwise
     * return -1.
     */
    private static Set getSubtree(int node) {
        for (int i = 0; i < subTrees.size(); i++) {
            if (subTrees.get(i).contains(node)) {
                return subTrees.get(i);
            }
        }
        return null;
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
        if (getSubtree(path.node1) == null && getSubtree(path.node2) == null) {
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
