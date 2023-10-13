package edu.hw1;

import java.util.Objects;

public class Task8 {
    private static final int[][] KNIGHT_MOVES = {
        {1, 2}, {-1, 2}, {1, -2}, {-1, -2},
        {2, 1}, {-2, 1}, {2, -1}, {-2, -1}
    };
    private static final int BOARD_HEIGHT = 8;
    private static final int BOARD_WIDTH = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        requireNonNull(board);

        if (!isRequiredSizes(board)) {
            throw new IllegalArgumentException(String.format("The board should be %dx%d", BOARD_HEIGHT, BOARD_WIDTH));
        } else if (!isAllElementZeroOrOne(board)) {
            throw new IllegalArgumentException("All elements on board should be equals 0 or 1");
        } else if (countKnight(board) == 0) {
            throw new IllegalArgumentException("No knights on the board");
        }

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (board[i][j] == 1) {
                    if (isKnightHaveTargetToAttack(board, i, j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isKnightHaveTargetToAttack(int[][] board, int knightRow, int knightCollum) {
        for (int[] m : KNIGHT_MOVES) {
            int movRow = knightRow + m[0];
            int movCollum = knightCollum + m[1];

            if (!isBehindEdgeBoard(movRow, movCollum)) {
                if (board[movRow][movCollum] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isBehindEdgeBoard(int row, int collum) {
        return (row < 0 || row >= BOARD_HEIGHT) || (collum < 0 || collum >= BOARD_WIDTH);
    }

    private static boolean isRequiredSizes(int[][] board) {
        if (board.length != BOARD_HEIGHT) {
            return false;
        }

        for (int[] arr : board) {
            if (arr.length != BOARD_WIDTH) {
                return false;
            }
        }

        return true;
    }

    private static boolean isAllElementZeroOrOne(int[][] board) {
        for (int[] arr : board) {
            for (int i : arr) {
                if (i != 0 && i != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int countKnight(int[][] board) {
        int count = 0;

        for (int[] arr : board) {
            for (int i : arr) {
                if (i == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void requireNonNull(int[][] board) {
        Objects.requireNonNull(board);

        for (int[] arr : board) {
            Objects.requireNonNull(arr);
        }
    }
}
