package com.nyefan.anaconda.service;

import com.nyefan.anaconda.dao.TicTacToeDao;
import com.nyefan.anaconda.dao.TicTacToeInMemoryDao;
import com.nyefan.anaconda.data.TicTacToeBoard;
import com.nyefan.anaconda.data.TicTacToeCreateGameRequest;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeSubmitMoveRequest;
import com.nyefan.anaconda.exception.TicTacToeException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class TicTacToeService {

    //TODO: inject an implementation of TicTacToeDao based on environment
    private final TicTacToeDao dao = new TicTacToeInMemoryDao();

    public List<TicTacToeGame> getAllGames() {
        return dao.selectAllGames();
    }

    public TicTacToeGame createGame(TicTacToeCreateGameRequest createRequest) {
        TicTacToeGame game = new TicTacToeGame(UUID.randomUUID(), createRequest.getPlayerOne(), createRequest.getPlayerTwo(), new TicTacToeBoard());
        return dao.upsertGame(game);
    }

    public TicTacToeGame getGame(UUID gameID) {
        return dao.selectGameByID(gameID).orElseThrow(() -> new TicTacToeException(HttpStatus.NOT_FOUND, String.format("Game %s does not exist.", gameID)));
    }

    public TicTacToeGame submitMove(UUID gameID, TicTacToeSubmitMoveRequest moveRequest) {
        return new TicTacToeGame(gameID, "1", "2", new TicTacToeBoard());
    }
}
