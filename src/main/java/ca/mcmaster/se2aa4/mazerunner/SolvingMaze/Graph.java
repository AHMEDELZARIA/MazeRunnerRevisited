package ca.mcmaster.se2aa4.mazerunner.SolvingMaze;

import java.util.List;

public interface Graph {
    List<Integer> getValue(Integer nodeIndex);
    Integer getStartNodeIndex();
    Integer getEndNodeIndex();
    void printGraph();
}
