package com.nyefan.anaconda.data;

public final class TicTacToeCreateGameRequest {
    private final String playerOne;
    private final String playerTwo;

    public TicTacToeCreateGameRequest(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }
}
