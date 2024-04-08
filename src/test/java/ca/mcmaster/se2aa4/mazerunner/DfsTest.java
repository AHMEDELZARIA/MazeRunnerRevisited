package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DfsTest {
    private final MazeSolver mazeSolver = new Dfs();
    private Maze maze;

    @Test
    void straightMaze() throws Exception {
        this.maze = new Maze("./examples/straight.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("4F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void smallMaze() throws Exception {
        this.maze = new Maze("./examples/small.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void tinyMaze() throws Exception {
        this.maze = new Maze("./examples/tiny.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("3F L 4F R 3F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }
}
