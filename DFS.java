/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.pkg1.pkg8.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Arwa
 */
public class DFS {

    public List<Pattern> DFS_Search(Pattern initState) {

        Stack<Pattern> frontier = new Stack<>();
        List<Pattern> path = new ArrayList<>();
        List<Pattern> explored = new ArrayList<Pattern>();

        initState.setFron(true);
        initState.isRoot = true;
        initState.setLevel(0);
        frontier.push(initState);
        initState.SetSequence(initState.getElements(), initState);
        boolean goal = false;
        //boolean fron = true, exp = true;
        while (!frontier.isEmpty() && !goal) {

            Pattern state = frontier.pop();
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
                    frontier.push(state.children.get(i));
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
