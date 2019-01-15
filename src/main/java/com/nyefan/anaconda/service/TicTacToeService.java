package com.nyefan.anaconda.service;

import com.nyefan.anaconda.data.TicTacToeBoard;
import com.nyefan.anaconda.data.TicTacToeCreateGameRequest;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeSubmitMoveRequest;

import java.util.List;
import java.util.UUID;

public class TicTacToeService {
    public List<TicTacToeGame> getAllGames() {
        return List.of(new TicTacToeGame(UUID.randomUUID(), "1", "2", new TicTacToeBoard()));
    }

    public TicTacToeGame createGame(TicTacToeCreateGameRequest createRequest) {
        return new TicTacToeGame(UUID.randomUUID(), createRequest.getPlayerOne(), createRequest.getPlayerTwo(), new TicTacToeBoard());
    }

    public TicTacToeGame getGame(UUID gameID) {
        return new TicTacToeGame(gameID, "1", "2", new TicTacToeBoard());
    }

    public TicTacToeGame submitMove(UUID gameID, TicTacToeSubmitMoveRequest moveRequest) {
        return new TicTacToeGame(gameID, "1", "2", new TicTacToeBoard());
    }
}
