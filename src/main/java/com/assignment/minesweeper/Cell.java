package com.assignment.minesweeper;

public class Cell {

    //whether the cell is mine or not
    private boolean isMine;
    //whether the cell is revealed or not
    private boolean isRevealed;
    //whether the near cell have been mine
    private int adjacentMines;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        this.isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int count) {
        this.adjacentMines = count;
    }
}
