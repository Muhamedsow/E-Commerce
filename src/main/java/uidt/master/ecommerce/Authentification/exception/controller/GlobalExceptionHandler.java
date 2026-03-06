package uidt.master.ecommerce.Authentification.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenExpiredException;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenNotFoundException;
import uidt.master.ecommerce.Authentification.exception.classe.RefreshTokenRevokedException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<?> handleExpired() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Refresh token expired"));
    }

    @ExceptionHandler(RefreshTokenRevokedException.class)
    public ResponseEntity<?> handleRevoked() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Refresh token revoked"));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<?> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Refresh token not found"));
    }
}