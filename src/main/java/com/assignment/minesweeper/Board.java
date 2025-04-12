package com.assignment.minesweeper;

import com.assignment.minesweeper.enums.GameStatus;

import java.util.Random;

public class Board {
    private final int size;
    private final int mineCount;
    private final Cell[][] grid;
    private GameStatus gameStatus;
    private int revealedCells;

    public Board(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.grid = new Cell[size][size];
        this.gameStatus = GameStatus.ONGOING;
    }


    public void initialize() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                grid[row][col] = new Cell();

        placeMines();
        calculateAdjacents();
    }

    //Set the mines randomly.
    private void placeMines() {
        Random rand = new Random();
        int placed = 0;

        while (placed < mineCount) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);

            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                placed++;
            }
        }
    }

    private void calculateAdjacents() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                grid[row][col].setAdjacentMines(countAdjacentMines(row, col));
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int r = row - 1; r <= row + 1; r++)
            for (int c = col - 1; c <= col + 1; c++)
                if (isValid(r, c) && !(r == row && c == col) && grid[r][c].isMine())
                    count++;

        return count;
    }

    // Reveal the cell can check whether the cell has mine or not
    public void reveal(int row, int col) {
        if (!isValid(row, col) || grid[row][col].isRevealed()) return;

        Cell cell = grid[row][col];
        cell.setRevealed(true);
        revealedCells++;

        if (cell.isMine()) {
            gameStatus = GameStatus.LOSS;
            return;
        }

        if (cell.getAdjacentMines() == 0)
            for (int r = row - 1; r <= row + 1; r++)
                for (int c = col - 1; c <= col + 1; c++)
                    if (isValid(r, c)) reveal(r, c);

        if (revealedCells == size * size - mineCount)
            gameStatus = GameStatus.WON;
    }

    //check the row and col is valid or not
    public boolean isValid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    //Get the cell detail
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
