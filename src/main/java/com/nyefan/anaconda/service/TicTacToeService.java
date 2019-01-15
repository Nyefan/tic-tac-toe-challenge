package com.nyefan.anaconda.service;

import com.nyefan.anaconda.data.TicTacToeBoard;
import com.nyefan.anaconda.data.TicTacToeCreateRequest;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeMoveRequest;

import java.util.List;
import java.util.UUID;

public class TicTacToeService {
    public List<TicTacToeGame> getAllGames() {
        return List.of(new TicTacToeGame(UUID.randomUUID(), "1", "2", new TicTacToeBoard()));
    }

    public TicTacToeGame createGame(TicTacToeCreateRequest createRequest) {
        return new TicTacToeGame(UUID.randomUUID(), createRequest.getPlayerOne(), createRequest.getPlayerTwo(), new TicTacToeBoard());
    }

    public TicTacToeGame getGame(UUID gameID) {
        return new TicTacToeGame(gameID, "1", "2", new TicTacToeBoard());
    }

    public TicTacToeGame submitMove(UUID gameID, TicTacToeMoveRequest moveRequest) {
        return new TicTacToeGame(gameID, "1", "2", new TicTacToeBoard());
    }
}
