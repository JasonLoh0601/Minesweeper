package com.assignment.minesweeper.utils;

public class InputParser {
    //Parse the user input to get the target location eg: A1
    public static int[] parse(String input, int size) {
        if (input.length() < 2) return null;

        char rowChar = input.charAt(0);
        if (rowChar < 'A' || rowChar >= 'A' + size) return null;

        String colStr = input.substring(1);
        int col;
        try {
            col = Integer.parseInt(colStr) - 1;
        } catch (NumberFormatException e) {
            return null;
        }

        int row = rowChar - 'A';
        if (col < 0 || col >= size) return null;

        return new int[]{row, col};
    }
}
