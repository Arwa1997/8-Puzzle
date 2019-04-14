/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.pkg1.pkg8.puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Arwa
 */
public class Astar {

    public List<Pattern> Astar_Search(Pattern initState) {

        PriorityQueue<Pattern> frontier = new PriorityQueue<Pattern>();
        List<Pattern> path = new ArrayList<>();
        List<Pattern> explored = new ArrayList<Pattern>();

        initState.setFron(true);
        initState.isRoot = true;
        initState.setLevel(0);
        initState.calcManhattanDistance(initState);
        initState.calcEuclideanDistance(initState);
        initState.SetSequence(initState.getElements(), initState);
        frontier.add(initState);

        boolean goal = false;

        while (!frontier.isEmpty() && !goal) {

            Pattern state = (Pattern) frontier.remove();

            state.setFron(false);
            state.setExplored();
            explored.add(state);

            state.makeTree();

            for (int i = 0; i < state.children.size(); i++) {

                if (state.children.get(i).isGoal()) {
                    System.out.println("Goal Reached!");
                    goal = true;
                    Path(path, state.children.get(i));
                }

                if (!state.children.get(i).isExplored() && !state.children.get(i).isFron()) {
                    frontier.add(state.children.get(i));
                    state.children.get(i).setFron(true);
                }

            }

        }

        return path;

    }

    public void Path(List<Pattern> path, Pattern node) {
        path.add(node);
        while (node.parent != null) {
            node = node.parent;
            path.add(node);
        }

    }

}
