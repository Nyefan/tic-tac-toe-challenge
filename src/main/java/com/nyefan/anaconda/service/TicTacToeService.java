package com.nyefan.anaconda.service;

import com.nyefan.anaconda.dao.TicTacToeDao;
import com.nyefan.anaconda.dao.TicTacToeInMemoryDao;
import com.nyefan.anaconda.data.TicTacToeBoard;
import com.nyefan.anaconda.data.TicTacToeCreateGameRequest;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeSubmitMoveRequest;
import com.nyefan.anaconda.exception.TicTacToeException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.IntStream;

public class TicTacToeService {

    //TODO: inject an implementation of TicTacToeDao based on environment
    //      this also would allow for easier testing
    private TicTacToeDao dao = new TicTacToeInMemoryDao();

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
        if (!game.getVictor().equals(game.NONE)) {
            throw new TicTacToeException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Game %s is already complete: %s won", game.getGameID(), game.getVictor())
            );
        }

        // Ideally, the api would be accessed using a token unique per user from which the player's name can be pulled
        TicTacToeBoard.Space moveValue;
        if (move.getPlayer().equals(game.getPlayerOne())) {
            moveValue = TicTacToeBoard.Space.X;
        } else if (move.getPlayer().equals(game.getPlayerTwo())) {
            moveValue = TicTacToeBoard.Space.O;
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

        TicTacToeBoard.Space[][] internalBoard = board.getBoard();
        if (internalBoard[move.getX()][move.getY()] != TicTacToeBoard.Space.EMPTY) {
            throw new TicTacToeException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Space (%s,%s) is already occupied", move.getX(), move.getY()));
        }

        // Modifying the game state on a supposedly immutable object feels bad, but so does making a full deep copy
        internalBoard[move.getX()][move.getY()] = moveValue;

        TicTacToeGame outputGame = determineVictor(game, move)
                //if this becomes a common pattern, it makes sense to put a builder in front of this
                .map(victor -> new TicTacToeGame(game.getGameID(), game.getPlayerOne(), game.getPlayerTwo(), game.getBoard(), victor))
                .orElse(game);

        return outputGame;
    }

    private Optional<String> determineVictor(TicTacToeGame game, TicTacToeSubmitMoveRequest move) {
        if (testVictoryHorizontal(game.getBoard(), move.getX(), move.getY())
                || testVictoryVertical(game.getBoard(), move.getX(), move.getY())
                || testVictoryDiagonal(game.getBoard(), move.getX(), move.getY())
        ) {
            return Optional.of(move.getPlayer());
        }

        boolean gameBoardFull = Arrays.stream(game.getBoard().getBoard())
                                      .flatMap(Arrays::stream)
                                      .noneMatch(space -> space.equals(TicTacToeBoard.Space.EMPTY));

        if (gameBoardFull) {
            return Optional.of(TicTacToeGame.DRAW);
        }

        return Optional.empty();
    }

    private boolean testVictoryVertical(TicTacToeBoard board, int lastMoveX, int lastMoveY) {
        return testVictory(board, lastMoveX, lastMoveY, y -> board.getBoard()[lastMoveX][y]);
    }

    private boolean testVictoryHorizontal(TicTacToeBoard board, int lastMoveX, int lastMoveY) {
        return testVictory(board, lastMoveX, lastMoveY, x -> board.getBoard()[x][lastMoveY]);
    }

    private boolean testVictoryDiagonal(TicTacToeBoard board, int lastMoveX, int lastMoveY) {
        // Down-Right Diagonal \
        if (lastMoveX == lastMoveY) {
            return testVictory(board, lastMoveX, lastMoveY, i -> board.getBoard()[i][i]);
        }

        // Up-Right Diagonal /
        if (lastMoveX + lastMoveX + 1 == board.getSize()) {
            return testVictory(board, lastMoveX, lastMoveY, i -> board.getBoard()[i][board.getSize() - i - 1]);
        }

        // Not on a diagonal
        return false;
    }

    private boolean testVictory(TicTacToeBoard board, int lastMoveX, int lastMoveY, Function<Integer, TicTacToeBoard.Space> spaceSelectionFunction) {
        TicTacToeBoard.Space lastMoveValue = board.getBoard()[lastMoveX][lastMoveY];

        return IntStream.range(0, board.getSize())
                        .boxed()
                        .map(spaceSelectionFunction)
                        .allMatch(space -> space.equals(lastMoveValue));
    }
}
