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
        // make an arraylist to keep track of cells and a stack to track moves
        ArrayList<MazeCell> solution = new ArrayList<>();
        Stack<MazeCell> stack = new Stack<>();
        // the current cell or the end cell is the cell at the end
        MazeCell current = maze.getEndCell();
        while (current != null) {
            // as long as there have been moves push the current cell to the stack and then make it the one before
            stack.push(current);
            current = current.getParent();
        }
        // now add all of those moves to the arraylist to come up with a final path
        while (!stack.isEmpty()) {
            solution.add(stack.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Start at the beginning of the maze
        MazeCell currentCell = maze.getStartCell();
        // This is where we want to end up
        MazeCell end = maze.getEndCell();
        // Stack to keep track of cells to visit
        Stack<MazeCell> toExplore = new Stack<MazeCell>();

        while(currentCell != end){
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            // Check north first if it's valid, mark it down and add to stack
            if (maze.isValidCell(row - 1, col)) {
                MazeCell nextCell = maze.getCell(row - 1, col);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.push(nextCell);
            }
            // check east
            if (maze.isValidCell(row, col + 1)){
                MazeCell nextCell = maze.getCell(row, col + 1);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.push(nextCell);
            }
            // now south
            if (maze.isValidCell(row + 1, col)){
                MazeCell nextCell = maze.getCell(row + 1, col);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.push(nextCell);
            }
            // now west
            if (maze.isValidCell(row, col - 1)) {
                MazeCell nextCell = maze.getCell(row, col - 1);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.push(nextCell);
            }
            //Move to the next cell from the stack
            currentCell = toExplore.pop();
        }
        // give the final path
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // same as DFS but use a queue
        MazeCell currentCell = maze.getStartCell();
        MazeCell end = maze.getEndCell();
        Queue<MazeCell> toExplore = new LinkedList<MazeCell>();

        while(currentCell != end){
            // same code as DFS
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            if (maze.isValidCell(row - 1, col)) {
                MazeCell nextCell = maze.getCell(row - 1, col);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.add(nextCell);
            }
            if (maze.isValidCell(row, col + 1)){
                MazeCell nextCell = maze.getCell(row, col + 1);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.add(nextCell);
            }
            if (maze.isValidCell(row + 1, col)){
                MazeCell nextCell = maze.getCell(row + 1, col);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.add(nextCell);
            }
            if (maze.isValidCell(row, col - 1)) {
                MazeCell nextCell = maze.getCell(row, col - 1);
                nextCell.setParent(currentCell);
                nextCell.setExplored(true);
                toExplore.add(nextCell);
            }
            currentCell = toExplore.remove();
        }
        return getSolution();
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
