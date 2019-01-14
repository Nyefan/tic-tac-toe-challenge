package com.nyefan.anaconda.api;

import com.nyefan.anaconda.data.TicTacToeBoard;
import com.nyefan.anaconda.data.TicTacToeGame;
import com.nyefan.anaconda.data.TicTacToeMoveRequest;
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


@RestController
@RequestMapping("/v1")
public class TicTacToeAPIv1 {

    @GetMapping("/")
    public HttpEntity healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("service healthy\n");
    }

    @GetMapping("/api/games")
    public HttpEntity<List<TicTacToeGame>> getAllGames() {
        return ResponseEntity.status(HttpStatus.OK).body(List.of(new TicTacToeGame("1", "2", new TicTacToeBoard())));
    }

    @PostMapping("/api/games")
    public HttpEntity<TicTacToeGame> createGame() {
        return ResponseEntity.status(HttpStatus.OK).body(new TicTacToeGame("1", "2", new TicTacToeBoard()));
    }

    @GetMapping("/api/games/{id}")
    public HttpEntity<TicTacToeGame> getGame(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new TicTacToeGame("1", "2", new TicTacToeBoard()));
    }

    @PostMapping("/api/games/{id}")
    public HttpEntity<TicTacToeGame> submitMove(@PathVariable("id") int id, @RequestBody TicTacToeMoveRequest moveRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(new TicTacToeGame("1", "2", new TicTacToeBoard()));
    }
}
