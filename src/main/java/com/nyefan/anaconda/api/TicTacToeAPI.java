package com.nyefan.anaconda.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeAPI {

    @GetMapping("/")
    public HttpEntity healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("service healthy");
    }


}
