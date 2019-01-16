package com.nyefan.anaconda.service;

import com.nyefan.anaconda.dao.TicTacToeDao;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.exception.TicTacToeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TicTacToeServiceTest {

    private TicTacToeService service = new TicTacToeService();
    @Mock
    private TicTacToeDao     dao;

    @Before
    public void setupMocks() throws PrivilegedActionException {
        // This should be handled by injecting the db into TicTacToeInMemoryDao
        AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
            TicTacToeService.class.getDeclaredField("dao").setAccessible(true);
            TicTacToeService.class.getDeclaredField("dao").set(service, dao);
            return null;
        });
    }

    @Test
    public void getAllGames() {
        List<TicTacToeGame> expected = List.of(
                new TicTacToeGame(UUID.randomUUID(), null, null, null),
                new TicTacToeGame(UUID.randomUUID(), null, null, null));

        when(dao.selectAllGames())
                .thenReturn(expected);

        List<TicTacToeGame> result = service.getAllGames();

        assertEquals(expected, result);
        verify(dao).selectAllGames();
    }

    @Test
    public void getGame() {
        TicTacToeGame expected = new TicTacToeGame(UUID.randomUUID(), null, null, null);

        when(dao.selectGameByID(eq(expected.getGameID())))
                .thenReturn(Optional.of(expected));

        TicTacToeGame result = service.getGame(expected.getGameID());

        assertEquals(expected, result);
        verify(dao).selectGameByID(eq(expected.getGameID()));
    }

    @Test(expected = TicTacToeException.class)
    public void getGameOrElseThrow() {
        TicTacToeGame expected = new TicTacToeGame(UUID.randomUUID(), null, null, null);

        when(dao.selectGameByID(eq(expected.getGameID())))
                .thenReturn(Optional.empty());

        TicTacToeGame result = service.getGame(expected.getGameID());

        // Should have thrown an exception here
        // (should also be testing the actual content of the exception instead of allowing the @Test annotation to handle it)
        fail();
    }

    // TODO: write out the rest of these test cases
}
