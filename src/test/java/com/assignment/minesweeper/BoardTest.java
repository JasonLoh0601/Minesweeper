package com.assignment.minesweeper;

import com.assignment.minesweeper.enums.GameStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    //Test game start with first reveal and the game will keep ongoing.
    @Test
    void testInitialRevealNonMine() {
        Board board = new Board(4, 1);
        board.initialize();

        board.reveal(0, 0);
        assertTrue(board.getCell(0, 0).isRevealed());
        assertEquals(GameStatus.ONGOING, board.getGameStatus());
    }


    //Test the loss condition
    @Test
    void testLoseCondition() {
        Board board = new Board(2, 1);
        board.initialize();

        // Find the mine
        int mineRow = -1, mineCol = -1;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (board.getCell(i, j).isMine()) {
                    mineRow = i;
                    mineCol = j;
                }

        board.reveal(mineRow, mineCol);
        assertEquals(GameStatus.LOSS, board.getGameStatus());
    }

    //Test the win condition
    @Test
    void testWinCondition() {
        Board board = new Board(2, 1);
        board.initialize();

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (!board.getCell(i, j).isMine())
                    board.reveal(i, j);

        assertEquals(GameStatus.WON, board.getGameStatus());
    }
}
