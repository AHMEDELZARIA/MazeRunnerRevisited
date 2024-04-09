package ca.mcmaster.se2aa4.mazerunner.SolvingMaze;

import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList implements Graph {
    private final Maze maze;
    private final Map<Integer, List<Integer>> adjacencyList;

    public AdjacencyList(Maze maze) {
        this.maze = maze;
        this.adjacencyList = buildGraph();
    }

    @Override
    public List<Integer> getValue(Integer nodeIndex) {
        return this.adjacencyList.get(nodeIndex);
    }

    @Override
    public Integer getStartNodeIndex() {
        return this.maze.getStart().y() * maze.getSizeX();
    }

    @Override
    public Integer getEndNodeIndex() {
        return (this.maze.getEnd().y() + 1) * this.maze.getSizeX() - 1;
    }

    private Map<Integer, List<Integer>> buildGraph() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Integer cell_index = -1;


        for (int row = 0; row < this.maze.getSizeY(); row++) {
            for (int col = 0; col < this.maze.getSizeX(); col++) {
                cell_index++;
                if (!this.maze.isWall(new Position(col, row))) {
                    graph.put(cell_index, getNeighbours(row, col));
                }
            }
        }
        return graph;
    }

    private List<Integer> getNeighbours(int row, int col) {
        List<Integer> neighbours = new ArrayList<>();
        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int nx = col + dx;
            int ny = row + dy;

            if (((0 <= nx) && (nx < this.maze.getSizeX())) && ((0 <= ny) && (ny < this.maze.getSizeY()))) {
                if (!this.maze.isWall(new Position(nx, ny))) {
                    neighbours.add(this.maze.getSizeX() * ny + nx);
                }
            }
        }

        return neighbours;
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
            System.out.println(key + " -> " + value);
        }
    }
}
