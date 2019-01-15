package com.nyefan.anaconda.api;

import com.nyefan.anaconda.data.TicTacToeCreateGameRequest;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeSubmitMoveRequest;
import com.nyefan.anaconda.service.TicTacToeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/v1")
public class TicTacToeAPIv1 {

    private TicTacToeService service = new TicTacToeService();

    @GetMapping("/")
    public HttpEntity healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("service healthy\n");
    }

    @GetMapping("/api/games")
    public HttpEntity<List<TicTacToeGame>> getAllGames() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllGames());
    }

    @PostMapping("/api/games")
    public HttpEntity<TicTacToeGame> createGame(@RequestBody TicTacToeCreateGameRequest createRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(service.createGame(createRequest));
    }

    @GetMapping("/api/games/{id}")
    public HttpEntity<TicTacToeGame> getGame(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getGame(id));
    }

    @PostMapping("/api/games/{id}")
    public HttpEntity<TicTacToeGame> submitMove(@PathVariable("id") UUID id, @RequestBody TicTacToeSubmitMoveRequest moveRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(service.submitMove(id, moveRequest));
    }

    private <T> HttpEntity wrapException(Callable<T> callable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(callable.call());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
