# 8-Puzzle
8 Puzzle game using uninformed search algorithms 

To implement the search algorithms on the puzzle, we first made a "Pattern" class to define each node of the puzzle depending on the possible movements of the "0" or the empty square.  The constructor will take an array of the initial elements add it to the "elements" array of the class.
The next method in this class "makeTree" will expand the current Pattern node into the possible children that are unique from the parent by calling the movement methods.

This is a sample of one of the movement methods. This methods will move the position of the "0" to the right, after performing a simple check to make sure it is not on the far right edge of the puzzle (where it is not possible to be moved any further right). As we are dealing with this 2D puzzle in a 1D array, we move the elements right or left by adding or subtracting 1, and down or up by adding or subtracting 3 (the width of each row), respectively.

As we need to preserve the new children of each parent, we add them to an ArrayList of <Patterns> to use later in each search algorithm. While also identifying each child by its parent, by setting the parent as soon as the child is  made and added to the arraylist. 
We also made a "SetSequence" method that takes an int[] array and its Pattern object and turns it into an int of the same sequence, to avoid looping while comparing the sequence in the future using other methods, and therefore reduce the running time.
Some of the comparing methods we used are isGoal() and isUnique(), the first one is to detect if the current state has reached the goal state (which we have set to be 0,1,2,3,4,5,6,7,8) and the second is to check if the sequence is unique from its parent (to avoid infinite loops while using a search algorithm). Here we are using the integer technique that was implemented using the previous method.
The method "makeChild" is pretty simple, it creates a copy of the parent array so we can re-position the "0" and create a new node.


The next methods are mainly setters and getters to be used in in aiding the search algorithms
The "fron" and "explored" flags are to be used in the frontier and explored lists of each algorithm, which will be shown in details.
Last but not least, we have added a printing method to print the sequence of the current state in the 2D style of the puzzle to give a simple visualization of the moves and the path to goal. It also prints the level, and the direction moved from parent of each node.
This pretty much summarizes our "Pattern" class. Next we made a separate class for each search algorithm, that we call individually depending on which one is to be used.

1. Breadth-First Search
This class has only two methods, one is the main search method, the other simply adds the path to nodes to an array that can then be returned as the solution to the puzzle.
In the searching method, we implemented the queue data structure using an arraylist by adding to it normally and then removing the element at position [0] of the array each time. This was our frontier array, the other "explored" array we used was a normal one to preserve the fully explored nodes. We would loop over the frontier queue until it is empty while adding the children states to it each time and checking if any of them equals the goal state. We used the setters and getters of the fron & explored flags for each node to be able to tell if each state is or is not present in either arrays without looping over them (hence a better runtime).
The path method is called when goal is found to trace the current (goal) state back to the initial parent (root).
When called, this method will return the path array of the solved puzzle.

2. Depth-First Search
The algorithm is exactly the same as that we used in the breadth-first search, except this time the frontier array is implemented with a stack data structure.

3. A* Search
To implement this search algorithm, we had to make some changes to our "Pattern" class.
First we added some methods to calculate the Manhattan, Euclidean, and Heuristic distances.

As we used 1d array in pattern class we had to get the x y indexes to calculate the manhattan distance (the sum of the vertical and horizontal distance between the current location and the location at the goal pattern)and the euclidean distance( the square root of the square of the difference between the current and the goal indexes)

We added an integer parameter “level” to pattern which increments each time we create a child in the heuristic method we add both the level number and the used distance result (which we added a setter for that was shown previously). (To use the manhattan distance we edit the code.)

As we had to use a PriorityQueue for our frontier array in this search technique, its comparing method needed to be re-set as to use the heuristic provided. We did that by inheriting the "Comparable" java built-in class to our "Pattern" class and overriding its "CompareTo" method.

Finally our searching method for A* was also similar to that of the BFS and DFS, apart from the use of a PriorityQueue for our frontier array.

