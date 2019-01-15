package com.nyefan.anaconda.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final class TicTacToeSubmitMoveRequest {

    @JsonProperty("id")
    private final UUID   gameID;
    private final String player;
    private final int    x;
    private final int    y;

    public TicTacToeSubmitMoveRequest(
            @JsonProperty("player") String player,
            @JsonProperty("x") int x,
            @JsonProperty("y") int y) {
        this(UUID.randomUUID(), player, x, y);
    }

    public TicTacToeSubmitMoveRequest(
            @JsonProperty("id") UUID gameID,
            @JsonProperty("player") String player,
            @JsonProperty("x") int x,
            @JsonProperty("y") int y) {
        this.gameID = gameID;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    @JsonProperty("id")
    public UUID getGameID() {
        return gameID;
    }

    public String getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
