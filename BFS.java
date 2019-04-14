/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.pkg1.pkg8.puzzle;

import java.util.*;

/**
 *
 * @author Arwa
 */
public class BFS {

    public List<Pattern> BFS_Search(Pattern initState) {

        List<Pattern> frontier = new ArrayList<>();
        List<Pattern> path = new ArrayList<>();
        List<Pattern> explored = new ArrayList<Pattern>();

        initState.setFron(true);
        initState.isRoot = true;
        initState.setLevel(0);
        initState.SetSequence(initState.getElements(), initState);
        frontier.add(initState);

        boolean goal = false;
        //boolean fron = true, exp = true;
        while (!frontier.isEmpty() && !goal) {

            Pattern state = frontier.get(0);
            frontier.remove(0);
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
