/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.pkg1.pkg8.puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arwa
 */
public class Pattern implements Comparable<Pattern> {

    private String positionToParent;
    private int[] elements = new int[9];
    private boolean isDone;
    public Pattern parent;
    boolean isRoot;
    public List<Pattern> children = new ArrayList<>();
    private int position;
    private int sequence;
    private static List<Integer> sequences = new ArrayList<>();
    private boolean explored = false;
    private boolean fron = false;
    private int manhattanDistance;
    private float euclideanDistance;
    private int level;
    private int totalCost;

    public Pattern(int[] elements) {

        AddPattern(elements);

    }

    public void AddPattern(int[] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            this.elements[i] = pattern[i];
        }
    }

    public void makeTree() {
        Pattern p = this;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == 0) {
                position = i;
            }
        }

        moveRight(elements, position, p);
        moveLeft(elements, position, p);
        moveUp(elements, position, p);
        moveDown(elements, position, p);

    }

    public int SetSequence(int[] elements, Pattern p) {
        int i = 0;
        int x = elements[i];
        for (i = 1; i < elements.length; i++) {
            x *= 10;
            x += elements[i];
        }
        p.sequence = x;
        return x;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExplored() {
        return explored;
    }

    public int[] getElements() {
        return elements;
    }

    public int numOfNodes() {
        return sequences.size();
    }

    public void setExplored() {
        this.explored = true;
    }

    public boolean isFron() {
        return fron;
    }

    public void setFron(boolean fron) {
        this.fron = fron;
    }

    public boolean isGoal() {

        if (this.sequence == 12345678) {
            isDone = true;
            return true;

        } else {
            isDone = false;
        }
        return false;
    }

    public boolean isUnique(Pattern child) {
        for (int i = 0; i < sequences.size(); i++) {
            if (child.sequence == sequences.get(i)) {
                return false;
            }
        }
        return true;

    }

    public void moveRight(int[] pattern, int i, Pattern p) {

        //to make sure it can move right
        if (i % 3 < 2) {
            int[] child = new int[9];
            //make a copy of parent
            makeChild(pattern, child);

            //switch to move right
            int temp = child[i + 1];
            child[i + 1] = child[i];
            child[i] = temp;

            Pattern node = new Pattern(child);
            node.parent = p;
            node.setLevel(p.level + 1);
            node.SetSequence(child, node);
            calcManhattanDistance(node);
            calcEuclideanDistance(node);
            heuristics(node);
            if (isUnique(node)) {
                children.add(node);
                sequences.add(this.SetSequence(child, node));
                SetSequence(child, node);
                node.positionToParent = "right";
                node.parent = this;
            }
        }

    }

    public void moveLeft(int[] pattern, int i, Pattern p) {

        //to make sure it can move left
        if (i % 3 > 0) {
            int[] child = new int[9];
            //make a copy of parent
            makeChild(pattern, child);

            //switch to move left
            int temp = child[i - 1];
            child[i - 1] = child[i];
            child[i] = temp;

            //adding to children of parent node
            Pattern node = new Pattern(child);
            node.parent = p;
            SetSequence(child, node);
            node.setLevel(p.level + 1);
            calcManhattanDistance(node);
            calcEuclideanDistance(node);
            heuristics(node);
            //System.out.println("in left");
            if (isUnique(node)) {
                children.add(node);
                sequences.add(this.SetSequence(child, node));
                SetSequence(child, node);
                //    System.out.println("in left done");

                node.positionToParent = "left";
                node.parent = this;
            }
        }

    }

    public void moveUp(int[] pattern, int i, Pattern p) {

        //to make sure it can move up
        if (i - 3 >= 0) {
            int[] child = new int[9];
            //make a copy of parent

            makeChild(pattern, child);

            //switch to move up
            int temp = child[i - 3];
            child[i - 3] = child[i];

            child[i] = temp;

            Pattern node = new Pattern(child);
            node.SetSequence(child, node);
            node.parent = p;
            node.setLevel(p.level + 1);
            calcManhattanDistance(node);
            calcEuclideanDistance(node);
            heuristics(node);

            if (isUnique(node)) {
                children.add(node);
                sequences.add(this.SetSequence(child, node));
                SetSequence(child, node);
                node.positionToParent = "up";
                node.parent = this;

            }
        }

    }

    public void moveDown(int[] pattern, int i, Pattern p) {

        if (i + 3 < 9) {

            int[] child = new int[9];
            //make a copy of parent
            makeChild(pattern, child);

            //switch to move down
            int temp = child[i + 3];
            child[i + 3] = child[i];
            child[i] = temp;

            Pattern node = new Pattern(child);
            node.parent = p;
            node.setLevel(p.level + 1);
            SetSequence(child, node);
            calcManhattanDistance(node);
            calcEuclideanDistance(node);
            heuristics(node);
            if (isUnique(node)) {
                children.add(node);
                sequences.add(this.SetSequence(child, node));
                SetSequence(child, node);

                node.positionToParent = "down";
                node.parent = this;
            }

        }

    }

    void makeChild(int[] parent, int[] child) {

        for (int i = 0; i < parent.length; i++) {
            child[i] = parent[i];

        }

    }

    public void calcManhattanDistance(Pattern p) {
        int i, xI, yI, xX, yX, temp, manhattanDistance = 0;
        //XI yI the actual indexes
        //xX yX the manhatten distance

        for (i = 0; i < 9; i++) {
            if (p.elements[i] == 0) {
                continue;
            }

            temp = p.elements[i] ;
            xX = ((temp) % 3);
            yX = temp / 3;
            xI = ((i) % 3);
            yI = i / 3;

            manhattanDistance += Math.abs(xI - xX) + Math.abs(yI - yX);
        }
        p.manhattanDistance = manhattanDistance;
    }

    public int heuristics(Pattern p) {
        int x = (int) p.euclideanDistance;
        return p.totalCost = x + p.level;
    }

    public void calcEuclideanDistance(Pattern p) {
        int i, xI, yI, xX, yX, temp;
        float euclideanDistance = 0;
        //XI yI the actual indexes
        //xX yX the manhatten distance

        for (i = 0; i < 9; i++) {
            if (p.elements[i] == 0) {
                continue;
            }

            temp = p.elements[i];
            xX = ((temp) % 3);
            yX = temp / 3;
            xI = ((i) % 3);
            yI = i / 3;

            euclideanDistance += Math.sqrt(Math.pow(Math.abs(xI - xX), 2) + Math.pow(Math.abs(yI - yX), 2));
        }
        p.euclideanDistance = euclideanDistance;
    }

    public void printPattern(Pattern p) {

        int m = 0;
        System.out.println("Level: " + p.level);
        //uncomment either to get the distance required.
        System.out.println("Euclidean dis = " + p.euclideanDistance);
         //System.out.println("Manhattan dis = " + p.manhattanDistance);

        if (!p.isRoot) {

            System.out.println("Direction moved: " + p.positionToParent);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print(p.elements[m] + " ");
                m++;
            }
            System.out.println("");

        }

        System.out.println("");

    }

    @Override
    public int compareTo(Pattern p) {

        if (this.heuristics(this) > p.heuristics(p)) {
            return 1;
        } else if (this.heuristics(this) < p.heuristics(p)) {
            return -1;
        } else {
            return 0;
        }
    }

}
