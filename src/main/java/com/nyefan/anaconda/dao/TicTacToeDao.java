package com.nyefan.anaconda.dao;

import com.nyefan.anaconda.data.TicTacToeGame;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicTacToeDao {
    Optional<TicTacToeGame> selectGameByID(UUID gameID);
    List<TicTacToeGame> selectAllGames();
    TicTacToeGame upsertGame(TicTacToeGame game);
}
