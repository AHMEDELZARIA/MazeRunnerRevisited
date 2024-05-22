# Maze Runner Take 2

* **Student**: [Ahmed Elzaria](elzariaa@mcmaster.ca)
  * Author: Alexandre Lachance
* **Program**: B. Eng. In Software Engineering
* **Course code**: SFWRENG 2AA4
* **Course Title**: Software Design I - Introduction to Software Development
* Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory.
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by
  the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’.
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output. 
  - User can specify which path computation method they want to use
  - Shortest path is displayed if the `dfs` algorithm is chosen
- The program can take a path as input and verify if it's a legit one.
- The program can compare the results of 2 algorithms
  - Time taken to generate maze is shown
  - Time taken to compute maze via both algorithms is shown
  - Speedup = |Algorithm1 Path| / |Algorithm2 Path| is shown

## How to run this software?

To build the program, simply package it with Maven:

```
elzaria@ahmeds A3-MazeRunner % mvn -q clean package 
```

### Delivered version

#### Command line arguments

The delivered program uses the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {tremaux, righthand, dfs}`: computes the path to a maze via the PATH_ALGORITHM specified
- `-baseline {tremaux, righthand, dfs}`: specifies the path computation algorithm to compare with

#### Examples

If no -method flag is passed, the default path computation algorithm is `righthand`.

```
elzaria@ahmeds A3-MazeRunner % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
```
If a `-method` flag is passed, the path will be computed via the specified path computation algorithm. `dfs` provides the shortest path on any input perfect maze.

```
elzaria@ahmeds A3-MazeRunner % java -jar target/mazerunner.jar -i ./examples/medium.maz.txt -method dfs
F L 2F R 2F L 18F L 2F R 2F R 8F R 2F L 6F R 10F L 4F R 10F L 10F R 4F L F
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
elzaria@ahmeds A3-MazeRunner % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
elzaria@ahmeds A3-MazeRunner % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
incorrect path
```

To compare algorithms, specify a `-method` flag path computation algorithm to compare to the algorithm specified by the `-baseline` flag.

```
elzaria@ahmeds A3-MazeRunner % java -jar target/mazerunner.jar -i ./examples/giant.maz.txt -method tremaux -baseline dfs
Time taken to generate maze: 0.9ms
Time taken to compute maze via tremaux: 5.4ms
Time taken to compute maze via dfs: 2.2ms
Speedup: 1.0
```

