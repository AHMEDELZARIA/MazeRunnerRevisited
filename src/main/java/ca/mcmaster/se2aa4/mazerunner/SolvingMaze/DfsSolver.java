package ca.mcmaster.se2aa4.mazerunner.SolvingMaze;

import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Path;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.AbstractMap.SimpleEntry;

public class DfsSolver implements MazeSolver {

    @Override
    public Path solve(Maze maze) {
        Path finalPath = new Path();
        Graph graph = new AdjacencyList(maze);
        List<Integer> path = search(graph, graph.getStartNodeIndex(), graph.getEndNodeIndex());
        return finalPath.convert(path);
    }

    /**
     * Performs a depth-first search finding the shortest path in a graph from a
     * specified start to end node
     *
     * @param graph Graph to search
     * @param start Start node
     * @param end End node
     * @return Shortest path in graph from start to end
     */
    private List<Integer> search(Graph graph, Integer start, Integer end) {
        // Initial a stack with entries [node, [path]]
        List<Integer> startPath = new ArrayList<>();
        startPath.add(start);
        SimpleEntry<Integer, List<Integer>> entry = new SimpleEntry<>(start, startPath);
        Stack<SimpleEntry<Integer, List<Integer>>> stack = new Stack<>();
        stack.push(entry);

        // Keep a track of visited nodes to avoid revisiting them
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