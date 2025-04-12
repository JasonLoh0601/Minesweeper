package com.assignment.minesweeper.utils;


import com.assignment.minesweeper.Board;
import com.assignment.minesweeper.Cell;

public class BoardPrinter {
    public static void print(Board board) {
        print(board, false);
    }

    public static void print(Board board, boolean revealAll) {
        int size = board.getSize();

        System.out.print("  ");
        for (int i = 1; i <= size; i++)
            System.out.print(i + " ");
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < size; col++) {
                Cell cell = board.getCell(row, col);

                if (cell.isRevealed() || revealAll) {
                    if (cell.isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(cell.getAdjacentMines() + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
}
