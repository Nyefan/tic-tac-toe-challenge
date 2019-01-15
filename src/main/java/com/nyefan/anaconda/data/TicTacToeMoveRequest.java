package com.nyefan.anaconda.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final class TicTacToeMoveRequest {

    @JsonProperty("id")
    private final UUID   gameID;
    private final String player;
    private final int    x;
    private final int    y;

    public TicTacToeMoveRequest(String player, int x, int y) {
        this(UUID.randomUUID(), player, x, y);
    }

    public TicTacToeMoveRequest(UUID gameID, String player, int x, int y) {
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
