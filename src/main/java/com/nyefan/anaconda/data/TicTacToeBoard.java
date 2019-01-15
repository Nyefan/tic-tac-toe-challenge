package com.nyefan.anaconda.data;

import java.util.Arrays;

public final class TicTacToeBoard {

    public enum Space {EMPTY, X, O}

    ;

    //TODO: replace this with guava.Table and include a jackson serializer for it
    //      or just make it immutable using ImmutableList, though that's a bit messier
    //      also, use an enum here {EMPTY, X, O}
    private Space[][] board;
    private int       size;

    public TicTacToeBoard() {
        this(3);
    }

    // Making this private so I don't have to deal with the consequences of non-standard board sizes for the time being
    private TicTacToeBoard(int size) {
        this.size = size;
        board = new Space[size][size];
        for (Space[] row : board) {
            Arrays.fill(row, Space.EMPTY);
        }
    }

    public Space[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }
}
