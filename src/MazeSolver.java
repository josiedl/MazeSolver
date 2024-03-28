/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> s = new Stack<MazeCell>();
        MazeCell temp = maze.getEndCell();
        s.push(temp);
        while (temp != maze.getStartCell()) {
            s.push(temp.getParent());
            temp = temp.getParent();
        }
        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();
        while (!(s.empty())) {
            sol.add(s.pop());
        }
        return sol;
    }
    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        int row;
        int col;
        MazeCell currentCell = maze.getStartCell();
        // While not at the end cell
        while (currentCell != maze.getEndCell()) {
            row = currentCell.getRow();
            col = currentCell.getCol();
            // Check if cell NORTH is valid
            checkCellDFS(cellsToVisit, currentCell, row - 1, col);
            // Check if cell EAST is valid
            checkCellDFS(cellsToVisit, currentCell, row, col + 1);
            // Check if cell SOUTH is valid
            checkCellDFS(cellsToVisit, currentCell, row + 1, col);
            // Check if cell WEST is valid
            checkCellDFS(cellsToVisit, currentCell, row, col - 1);
            // Set currentCell to the next cell to visit
            currentCell = cellsToVisit.pop();
        }
        return getSolution();
    }

    // Checks if a given cell is valid, sets cell to explored
    // If the given cell is valid, pushes cell to cellsToVisit, and sets its parent
    public void checkCellDFS(Stack<MazeCell> cellsToVisit, MazeCell currentCell, int row, int col) {
        if (maze.isValidCell(row, col)) {
            cellsToVisit.push(maze.getCell(row, col));
            maze.getCell(row, col).setParent(currentCell);
            maze.getCell(row, col).setExplored(true);
        }
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        int row;
        int col;
        MazeCell currentCell = maze.getStartCell();
        // While not at the end cell
        while (currentCell != maze.getEndCell()) {
            row = currentCell.getRow();
            col = currentCell.getCol();
            // Check if cell NORTH is valid
            checkCellBFS(cellsToVisit, currentCell, row - 1, col);
            // Check if cell EAST is valid
            checkCellBFS(cellsToVisit, currentCell, row, col + 1);
            // Check if cell SOUTH is valid
            checkCellBFS(cellsToVisit, currentCell, row + 1, col);
            // Check if cell WEST is valid
            checkCellBFS(cellsToVisit, currentCell, row, col - 1);
            // Set currentCell to the next cell to visit
            currentCell = cellsToVisit.remove();
        }
        return getSolution();
    }

    public void checkCellBFS(Queue<MazeCell> cellsToVisit, MazeCell currentCell, int row, int col) {
        if (maze.isValidCell(row, col)) {
            cellsToVisit.add(maze.getCell(row, col));
            maze.getCell(row, col).setParent(currentCell);
            maze.getCell(row, col).setExplored(true);
        }
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
