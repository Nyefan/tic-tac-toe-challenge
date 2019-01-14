package com.nyefan.anaconda.data;

public class TicTacToeBoard {

    //TODO: replace this with guava.Table and include a jackson serializer for it
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
