package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface Graph {
    List<Integer> getValue(Integer nodeIndex);
    Integer getStartNodeIndex();
    Integer getEndNodeIndex();
}
