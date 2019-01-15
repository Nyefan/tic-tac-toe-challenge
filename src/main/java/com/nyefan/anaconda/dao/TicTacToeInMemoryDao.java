package com.nyefan.anaconda.dao;

import com.nyefan.anaconda.data.TicTacToeGame;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public final class TicTacToeInMemoryDao implements TicTacToeDao {

    //emulating a table indexed by gameID
    private static volatile ConcurrentMap<UUID, TicTacToeGame> db = new ConcurrentHashMap<>();

    @Override
    public Optional<TicTacToeGame> selectGameByID(UUID gameID) {
        return Optional.ofNullable(db.getOrDefault(gameID, null));
    }

    @Override
    public List<TicTacToeGame> selectAllGames() {
        return db.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public TicTacToeGame upsertGame(final TicTacToeGame game) {
        db.put(game.getGameID(), game);
        return db.get(game.getGameID());
    }
}
