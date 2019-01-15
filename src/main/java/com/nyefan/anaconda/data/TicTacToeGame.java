package com.nyefan.anaconda.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final class TicTacToeGame {

    @JsonIgnore
    public static final String NONE = "NONE";
    public static final String DRAW = "CAT";

    @JsonProperty("id")
    private final UUID           gameID;
    private final String         playerOne;
    private final String         playerTwo;
    private final TicTacToeBoard board;
    private final String         victor;

    public TicTacToeGame(UUID gameID, String playerOne, String playerTwo, TicTacToeBoard board) {
        this(gameID, playerOne, playerTwo, board, NONE);
    }

    public TicTacToeGame(UUID gameID, String playerOne, String playerTwo, TicTacToeBoard board, String victor) {
        this.gameID = gameID;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
        this.victor = victor;
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

    public String getVictor() {
        return victor;
    }
}
