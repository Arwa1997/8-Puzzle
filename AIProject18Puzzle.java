/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.pkg1.pkg8.puzzle;

import java.util.List;

/**
 *
 * @author Arwa
 */
public class AIProject18Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //goal set as 0,1,2,3,4,5,6,7,8
        //case1
        int[] pattern = new int[]{2,3,4,1,5,0,7,6,8};
        //case2
        //int[] pattern = new int[]{1,2,3,0,4,6,7,5,8};
        //case3
        //int[] pattern = new int[]{6,4,7,8,5,0,3,2,1};
        Pattern initState = new Pattern(pattern);
        DFS dfs = new DFS();
        BFS bfs = new BFS();
        Astar a = new Astar();

        List<Pattern> solved;
//               =  bfs.BFS_Search(initState);
        long startTime = System.nanoTime();
        //uncomment to run  the needed algorithm.
         solved = a.Astar_Search(initState);
        //  solved = dfs.DFS_Search(initState);
        //solved = bfs.BFS_Search(initState);
        long endTime = System.nanoTime();
        for (int i = solved.size() - 1; i > -1; i--) {
            System.out.println("");
            solved.get(i).printPattern(solved.get(i));
        }
        System.out.println("cost of the path    " + (solved.size() - 1));
        System.out.println("number of nodes    " + initState.numOfNodes());

        long totalTime = endTime - startTime;
        System.out.println("the running time = " + totalTime + "  nano sec");
    }

}
