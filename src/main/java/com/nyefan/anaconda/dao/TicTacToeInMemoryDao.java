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

    // emulating a table indexed by gameID
    private static ConcurrentMap<UUID, TicTacToeGame> db = new ConcurrentHashMap<>();

    // This is bad, but a simple way to get access to the db for tests in an example project
    public void setDb(ConcurrentMap<UUID, TicTacToeGame> db) {
        this.db = db;
    }

    @Override
    public Optional<TicTacToeGame> selectGameByID(UUID gameID) {
        //This is the api most db libraries would expose, though it's a bit convoluted for this particular implementation
        return Optional.ofNullable(db.getOrDefault(gameID, null));
    }

    @Override
    public List<TicTacToeGame> selectAllGames() {
        return db.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public TicTacToeGame upsertGame(TicTacToeGame game) {
        db.put(game.getGameID(), game);
        return db.get(game.getGameID());
    }
}
