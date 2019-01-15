package com.nyefan.anaconda.data;

public final class TicTacToeBoard {

    //TODO: replace this with guava.Table and include a jackson serializer for it
    //      or just make it immutable using ImmutableList, though that's a bit messier
    //      also, use an enum here {EMPTY, X, O}
    private Boolean[][] board;
    private int         size;

    public TicTacToeBoard() {
        this(3);
    }

    // Making this private so I don't have to deal with the consequences of non-standard board sizes for the time being
    private TicTacToeBoard(int size) {
        this.size = size;
        board = new Boolean[size][size];
    }

    public Boolean[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }
}
