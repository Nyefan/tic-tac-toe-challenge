package com.nyefan.anaconda.data;

public class TicTacToeGame {

    private String         playerOne;
    private String         playerTwo;
    private TicTacToeBoard board;

    public TicTacToeGame(String playerOne, String playerTwo, TicTacToeBoard board) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public TicTacToeBoard getBoard() {
        return board;
    }
}
