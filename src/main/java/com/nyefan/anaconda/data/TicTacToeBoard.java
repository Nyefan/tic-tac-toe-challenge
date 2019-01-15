package com.nyefan.anaconda.data;

public final class TicTacToeBoard {

    //TODO: replace this with guava.Table and include a jackson serializer for it
    //      or just make it immutable using ImmutableList, though that's a bit messier
    private Boolean[][] board;

    public TicTacToeBoard() {
        this(3);
    }

    public TicTacToeBoard(int size) {
        board = new Boolean[size][size];
    }

    public Boolean[][] getBoard() {
        return board;
    }
}
