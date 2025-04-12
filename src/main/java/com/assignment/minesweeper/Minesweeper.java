package com.assignment.minesweeper;

import com.assignment.minesweeper.enums.GameStatus;
import com.assignment.minesweeper.utils.BoardPrinter;
import com.assignment.minesweeper.utils.InputParser;

import java.util.Scanner;

public class Minesweeper {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size= -1;
        System.out.println("Welcome to Minesweeper!");

        while (true) {

            if(size == -1){
                System.out.print("\nEnter the size of the grid (e.g. 4 for a 4x4 grid): ");

                try{
                    size = scanner.nextInt();
                }catch (Exception e){
                    System.out.print("Invalid value insert!");
                    continue;
                }finally {
                    scanner.nextLine(); // consume newline
                }
            }

            int maxMines = (int) Math.floor(size * size * 0.35);
            System.out.print("\nEnter the number of mines to place on the grid (maximum is " + maxMines + "): ");
            int mineCount;

            try{
                mineCount = scanner.nextInt();
            }catch (Exception e){
                System.out.print("Invalid value insert!");
                continue;
            }finally {
                scanner.nextLine(); // consume newline
            }

            Board board = new Board(size, mineCount);
            board.initialize();

            while (board.getGameStatus() == GameStatus.ONGOING) {
                BoardPrinter.print(board);
                System.out.print("\nSelect a square to reveal (e.g. A1): ");
                String input = scanner.nextLine().trim().toUpperCase();
                int[] pos = InputParser.parse(input, size);

                if (pos == null) {
                    System.out.println("Invalid input. Please try again.");
                    continue;
                }

                board.reveal(pos[0], pos[1]);
            }

            BoardPrinter.print(board, true);

            if (board.getGameStatus() == GameStatus.LOSS) {
                System.out.println("Oh no, you detonated a mine! Game over.");
            } else {
                System.out.println("Congratulations, you have won the game!");
            }

            System.out.println("Press Enter to play again or type 'exit' to quit...");
            if (scanner.nextLine().equalsIgnoreCase("exit")) break;
        }

        scanner.close();
    }
}