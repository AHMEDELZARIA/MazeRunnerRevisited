package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.AbstractMap.SimpleEntry;

public class dfs implements MazeSolver {
    private Maze maze;
    private Path finalPath;

    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        this.finalPath = new Path();
        Graph graph = new AdjacencyList(this.maze);
        List<Integer> path = search(graph, graph.getStartNodeIndex(), graph.getEndNodeIndex());
        return finalPath.convert(path);
    }

    private List<Integer> search(Graph graph, Integer start, Integer end) {
        List<Integer> startPath = new ArrayList<>();
        startPath.add(start);
        SimpleEntry<Integer, List<Integer>> entry = new SimpleEntry<>(start, startPath);
        Stack<SimpleEntry<Integer, List<Integer>>> stack = new Stack<>();
        stack.push(entry);

        Set<Integer> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            SimpleEntry<Integer, List<Integer>> stack_entry = stack.pop();
            Integer current = stack_entry.getKey();
            List<Integer> path = stack_entry.getValue();

            if (current.equals(end)) {
                return path;
            }

            visited.add(current);

            for (Integer neighbour : graph.getValue(current)) {
                if (!visited.contains(neighbour)) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbour);
                    SimpleEntry<Integer, List<Integer>> new_entry = new SimpleEntry<>(neighbour, newPath);
                    stack.push(new_entry);
                }
            }
        }

        return new ArrayList<>();
    }

}