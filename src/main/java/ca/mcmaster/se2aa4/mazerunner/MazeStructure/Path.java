package ca.mcmaster.se2aa4.mazerunner.MazeStructure;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Character> path = new ArrayList<>();

    /**
     * Initialize an empty Path.
     */
    public Path() {
    }

    /**
     * Initialize path from a Path String.
     *
     * @param pathStr The Path String
     */
    public Path(String pathStr) {
        String expanded = expandFactorizedStringPath(pathStr);
        for (Character c : expanded.toCharArray()) {
            if (c != ' ') {
                if (c != 'F' && c != 'L' && c != 'R') {
                    throw new IllegalArgumentException("Instruction '" + c + "' is invalid. Must be 'F', 'L', or 'R'.");
                }
                addStep(c);
            }
        }
    }

    /**
     * Expand a factorized string path into a canonical one.
     *
     * @param path String path
     * @return Expanded string path
     */
    public String expandFactorizedStringPath(String path) {
        StringBuilder expanded = new StringBuilder();

        for (int i = 0; i < path.length(); i++) {
            if (!Character.isDigit(path.charAt(i))) {
                expanded.append(path.charAt(i));
            } else {
                int count = 0;
                int digit = 0;
                do {
                    count *= (int) Math.pow(10, digit++);
                    count += Character.getNumericValue(path.charAt(i++));
                } while (Character.isDigit(path.charAt(i)));

                String step = String.valueOf(path.charAt(i)).repeat(count);
                expanded.append(step);
            }
        }

        return expanded.toString();
    }

    /**
     * Get steps of Path.
     *
     * @return Chars of Path
     */
    public List<Character> getPathSteps() {
        return new ArrayList<>(this.path);
    }

    /**
     * Adds a step to the path.
     *
     * @param step The step that needs to be added.
     */
    public void addStep(Character step) {
        path.add(step);
    }

    /**
     * Generates the canonical form of the maze path.
     *
     * @return A string of the canonical form of a path.
     */
    public String getCanonicalForm() {
        StringBuilder sb = new StringBuilder();

        for (Character c : path) {
            if (sb.isEmpty() || sb.charAt(sb.length() - 1) == c) {
                sb.append(c);
            } else {
                sb.append(' ');
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Generates the factorized form of the maze path.
     *
     * @return A string of the factorized form of a path.
     */
    public String getFactorizedForm() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < path.size(); i++) {
            Character current = path.get(i);
            int count = 0;
            while (i < path.size() && current == path.get(i)) {
                count++;
                i++;
            }

            if (count == 1) {
                sb.append(current);
            } else {
                sb.append(count);
                sb.append(current);
            }

            if (i != path.size()) {
                sb.append(' ');
            }
            i--;
        }

        return sb.toString();
    }

    public Path convert(List<Integer> path) {
        Direction dir = Direction.RIGHT;
        Path finalPath = new Path();
        Integer current, next;
        int results;

        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) { break; }

            current = path.get(i);
            next = path.get(i + 1);
            results = next - current;

            switch (dir) {
                case RIGHT:
                    if (results == 1) {
                        finalPath.addStep('F');
                    } else if (results == -1) {
                        dir = dir.turnLeft().turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else if (results > 1) {
                        dir = dir.turnRight();
                        finalPath.addStep('R');
                        finalPath.addStep('F');
                    } else {
                        dir = dir.turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    }
                    break;
                case LEFT:
                    if (results == 1) {
                        dir = dir.turnLeft().turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else if (results == -1) {
                        finalPath.addStep('F');
                    } else if (results > 1) {
                        dir = dir.turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else {
                        dir = dir.turnRight();
                        finalPath.addStep('R');
                        finalPath.addStep('F');
                    }
                    break;
                case UP:
                    if (results == 1) {
                        dir = dir.turnRight();
                        finalPath.addStep('R');
                        finalPath.addStep('F');
                    } else if (results == -1) {
                        dir = dir.turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else if (results > 1) {
                        dir = dir.turnLeft().turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else {
                        finalPath.addStep('F');
                    }
                    break;
                case DOWN:
                    if (results == 1) {
                        dir = dir.turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    } else if (results == -1) {
                        dir = dir.turnRight();
                        finalPath.addStep('R');
                        finalPath.addStep('F');
                    } else if (results > 1) {
                        finalPath.addStep('F');
                    } else {
                        dir = dir.turnLeft().turnLeft();
                        finalPath.addStep('L');
                        finalPath.addStep('L');
                        finalPath.addStep('F');
                    }
                    break;
            }
        }

        return finalPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Path)) { return false; }

        Path path = (Path) o;

        return getFactorizedForm().equals(path.getFactorizedForm());
    }
}
