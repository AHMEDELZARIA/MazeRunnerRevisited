package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Path;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.DfsSolver;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.MazeSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DfsTest {
    private final MazeSolver mazeSolver = new DfsSolver();
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

    @Test
    void rectangleMaze() throws Exception {
        this.maze = new Maze("./examples/rectangle.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("F L 11F R 2F R 4F L 8F L 2F R 4F L 2F R 10F R 4F L 6F R 2F L 4F R 4F L 10F L 2F R 4F R F L F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void directMaze() throws Exception {
        this.maze = new Maze("./examples/direct.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("F R 2F L 4F R 2F L 2F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void largeMaze() throws Exception {
        this.maze = new Maze("./examples/large.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("15F R 2F L 8F R 2F L 4F R 2F L 4F R 4F L 6F R 2F L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 6F L 2F L 2F R F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void hugeMaze() throws Exception {
        this.maze = new Maze("./examples/huge.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("F L 7F R 2F L 4F R 4F L 2F R 6F R 2F L 6F R 4F L 2F R 2F L 10F L 2F R 2F R 2F L 4F R 10F L 2F R 4F L 6F R 2F L 2F R 4F L 2F R 6F L 2F R 2F L 2F R 2F L 2F R 4F L 4F R 2F L 4F R 4F R 2F L 4F L 4F R 2F L 6F R 2F L 2F R 6F L 4F R 2F L 4F R 2F L 2F R 2F L 2F R 4F L 6F R 4F L 4F R 6F L 4F R F L F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }

    @Test
    void giantMaze() throws Exception {
        this.maze = new Maze("./examples/giant.maz.txt");
        Path path = this.mazeSolver.solve(this.maze);
        Path expectedPath = new Path("F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F");
        assertEquals(path, expectedPath);
        assertTrue(this.maze.validatePath(path));
    }
}
