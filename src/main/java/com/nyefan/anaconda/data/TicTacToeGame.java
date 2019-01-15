package com.nyefan.anaconda.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TicTacToeGame {

    @JsonProperty("id")
    private final UUID           gameID;
    private final String         playerOne;
    private final String         playerTwo;
    private final TicTacToeBoard board;

    public TicTacToeGame(UUID gameID, String playerOne, String playerTwo, TicTacToeBoard board) {
        this.gameID = gameID;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
    }

    @JsonProperty("id")
    public UUID getGameID() {
        return gameID;
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
