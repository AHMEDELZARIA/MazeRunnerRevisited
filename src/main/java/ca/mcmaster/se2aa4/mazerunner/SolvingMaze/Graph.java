package ca.mcmaster.se2aa4.mazerunner.SolvingMaze;

import java.util.List;

public interface Graph {
    /**
     * Gets the value held by node
     *
     * @param nodeIndex Index of node
     * @return value of node
     */
    List<Integer> getValue(Integer nodeIndex);

    /**
     * @return Gets the node representing the start of the maze
     */
    Integer getStartNodeIndex();

    /**
     * @return Gets the node representing the end of the maze
     */
    Integer getEndNodeIndex();

    /**
     * Prints out the graph
     */
    void printGraph();
}
