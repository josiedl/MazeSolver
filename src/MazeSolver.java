/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam and Josie Lee
 * @version 03/29/2024
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
        // Create a stack to keep track of all the MazeCells part of the solution
        Stack<MazeCell> s = new Stack<MazeCell>();
        // Start at the endCell
        MazeCell temp = maze.getEndCell();
        // Push endCell to stack
        s.push(temp);
        // While the temp cell is not the startCell
        while (temp != maze.getStartCell()) {
            // Push the parent of the cell
            s.push(temp.getParent());
            // Go to parent cell
            temp = temp.getParent();
        }
        // Pop the stack to an ArrayList to create a solution from startCell to EndCell
        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();
        while (!(s.empty())) {
            sol.add(s.pop());
        }
        // Return solution
        return sol;
    }
    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Create a stack to keep track of all the cellsToVisit
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        int row;
        int col;
        // Start at the startCell
        MazeCell currentCell = maze.getStartCell();
        // While not at the endCell
        while (currentCell != maze.getEndCell()) {
            // Set row and col to the currentCell's values
            row = currentCell.getRow();
            col = currentCell.getCol();
            // Check if cell NORTH is valid
            checkCell(cellsToVisit, currentCell, row - 1, col);
            // Check if cell EAST is valid
            checkCell(cellsToVisit, currentCell, row, col + 1);
            // Check if cell SOUTH is valid
            checkCell(cellsToVisit, currentCell, row + 1, col);
            // Check if cell WEST is valid
            checkCell(cellsToVisit, currentCell, row, col - 1);
            // Set currentCell to the next cell to visit
            currentCell = cellsToVisit.pop();
        }
        // Return the solution
        return getSolution();
    }

    // Checks if a given cell is valid, sets cell to explored
    // If the given cell is valid, pushes cell to cellsToVisit, and sets its parent
    public void checkCell(Stack<MazeCell> cellsToVisit, MazeCell currentCell, int row, int col) {
        // If the inputted cell is valid
        if (maze.isValidCell(row, col)) {
            // Push the cell to cells to visit
            cellsToVisit.push(maze.getCell(row, col));
            // Set the cell's parent to the currentCell
            maze.getCell(row, col).setParent(currentCell);
            // Set explored to true
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
        // Create a queue to keep track of all the cellsToVisit
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        int row;
        int col;
        // Start at the startCell
        MazeCell currentCell = maze.getStartCell();
        // While not at the endCell
        while (currentCell != maze.getEndCell()) {
            // Set row and col to currentCell's values
            row = currentCell.getRow();
            col = currentCell.getCol();
            // Check if cell NORTH is valid
            checkCell(cellsToVisit, currentCell, row - 1, col);
            // Check if cell EAST is valid
            checkCell(cellsToVisit, currentCell, row, col + 1);
            // Check if cell SOUTH is valid
            checkCell(cellsToVisit, currentCell, row + 1, col);
            // Check if cell WEST is valid
            checkCell(cellsToVisit, currentCell, row, col - 1);
            // Set currentCell to the next cell to visit
            currentCell = cellsToVisit.remove();
        }
        // Return the solution
        return getSolution();
    }

    // Checks if a given cell is valid, sets cell to explored
    // If the given cell is valid, adds cell to cellsToVisit, and sets its parent
    public void checkCell(Queue<MazeCell> cellsToVisit, MazeCell currentCell, int row, int col) {
        // If the inputted cell is valid
        if (maze.isValidCell(row, col)) {
            // Add the cell to cells to visit
            cellsToVisit.add(maze.getCell(row, col));
            // Set the cell's parent to the currentCell
            maze.getCell(row, col).setParent(currentCell);
            // Set explored to true
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
