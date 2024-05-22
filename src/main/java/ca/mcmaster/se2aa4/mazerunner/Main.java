package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Path;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.DfsSolver;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.TremauxSolver;
import ca.mcmaster.se2aa4.mazerunner.SolvingMaze.BfsSolver;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');

            // If -p is specified by user
            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Maze maze = new Maze(filePath);
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }

            // If -baseline is specified by user
            } else if (cmd.getOptionValue("baseline") != null) {
                Benchmark bench = new Benchmark();
                // Print time needed to generate maze
                bench.startTimer();
                Maze maze = new Maze(filePath);
                bench.endTimer();
                double mazeTime = bench.getElapsedTime();
                System.out.println("Time taken to generate maze: " + mazeTime + "ms");

                // Print time needed for method to compute path
                String method = cmd.getOptionValue("method", "righthand");
                bench.startTimer();
                Path methodPath = solveMaze(method, maze);
                bench.endTimer();
                double methodTime = bench.getElapsedTime();
                System.out.println("Time taken to compute maze via " + method + ": " + methodTime + "ms");

                // Print time needed for baseline to compute path
                String baseline = cmd.getOptionValue("baseline");
                bench.startTimer();
                Path baselinePath = solveMaze(baseline, maze);
                bench.endTimer();
                double baselineTime = bench.getElapsedTime();
                System.out.println("Time taken to compute maze via " + baseline + ": " + baselineTime + "ms");

                // Print speedup of baseline over method
                double speedup = bench.calcSpeedUp(baselinePath, methodPath);
                System.out.println("Speedup: " + speedup);

            // default behavior
            } else {
                Maze maze = new Maze(filePath);
                String method = cmd.getOptionValue("method", "righthand");
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());
            }

        } catch (Exception e) {
            // Handle exceptions and errors
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    public static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "dfs" -> {
                logger.debug("dfs algorithm chosen.");
                solver = new DfsSolver();
            }
            case "bfs" -> {
                logger.debug("bfs algorithm chosen.");
                solver = new BfsSolver();
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        logger.info("Computing path");
        return solver.solve(maze);
    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Specify the path computation algorithm to compare with -method"));

        return options;
    }
}
