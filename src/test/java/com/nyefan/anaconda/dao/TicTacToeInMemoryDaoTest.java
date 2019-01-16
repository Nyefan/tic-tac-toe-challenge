package com.nyefan.anaconda.dao;

import com.nyefan.anaconda.data.TicTacToeGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TicTacToeInMemoryDaoTest {

    private ConcurrentMap<UUID, TicTacToeGame> db;
    private TicTacToeDao                       dao = new TicTacToeInMemoryDao();

    // This should be handled by injecting the db into TicTacToeDao
    @Before
    public void beforeEach() {
        db = new ConcurrentHashMap<>();
        ((TicTacToeInMemoryDao) dao).setDb(db);
    }

    //TODO: Implement the rest of the tests
    //      These make way more sense for actual dbs
    //      And this would be annotated as integration tests as well in that case
    @Test
    public void upsertGameTest() {
        TicTacToeGame game = new TicTacToeGame(UUID.randomUUID(), null, null, null, null);
        dao.upsertGame(game);
        Assert.assertEquals(game, db.get(game.getGameID()));
    }
}
