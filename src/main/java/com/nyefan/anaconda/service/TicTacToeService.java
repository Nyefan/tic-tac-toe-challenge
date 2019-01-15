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
        TicTacToeGame game = new TicTacToeGame(
                UUID.randomUUID(),
                createRequest.getPlayerOne(),
                createRequest.getPlayerTwo(),
                new TicTacToeBoard());
        return dao.upsertGame(game);
    }

    public TicTacToeGame getGame(UUID gameID) {
        return dao.selectGameByID(gameID)
                  .orElseThrow(
                          () -> new TicTacToeException(
                                  HttpStatus.NOT_FOUND,
                                  String.format("Game %s does not exist.", gameID))
                  );
    }

    public TicTacToeGame submitMove(UUID gameID, TicTacToeSubmitMoveRequest moveRequest) {
        return dao.selectGameByID(gameID)
                  .map(game -> applyMove(game, moveRequest))
                  .map(dao::upsertGame)
                  .orElseThrow(
                          () -> new TicTacToeException(
                                  HttpStatus.NOT_FOUND,
                                  String.format("Game %s does not exist.", gameID))
                  );
    }

    // This is a bit convoluted - there is probably a cleaner way to do this, but not that immediately pops to mind for
    // a short one-off project while using the prescribed api
    private TicTacToeGame applyMove(TicTacToeGame game, TicTacToeSubmitMoveRequest move) {
        // Ideally, the api would be accessed using a token unique per user from which the player's name can be pulled
        // instead of this
        Boolean moveValue;
        if (move.getPlayer().equals(game.getPlayerOne())) {
            moveValue = Boolean.FALSE;
        } else if (move.getPlayer().equals(game.getPlayerTwo())) {
            moveValue = Boolean.TRUE;
        } else {
            throw new TicTacToeException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Player %s is not in Game %s", move.getPlayer(), game.getGameID()));
        }

        TicTacToeBoard board = game.getBoard();
        if (move.getX() >= board.getSize()
                || move.getY() >= board.getSize()
                || move.getX() < 0
                || move.getY() < 0
        ) {
            throw new TicTacToeException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Selected square (%d, %d) is not on the board")
            );
        }

        Boolean[][] internalBoard = board.getBoard();
        if (internalBoard[move.getX()][move.getY()] != null) {
            throw new TicTacToeException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Space (%s,%s) is already occupied", move.getX(), move.getY()));
        }

        // Modifying the game state on a supposedly immutable object feels bad,
        // but so does making a full deep copy of the original TicTacToeGame
        internalBoard[move.getX()][move.getY()] = moveValue;

        return game;
    }
}
