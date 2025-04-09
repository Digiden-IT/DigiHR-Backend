package com.digiHR.utility.exceptions;

import io.jsonwebtoken.JwtException;
import org.apache.coyote.BadRequestException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import io.jsonwebtoken.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Map<String, Object>> handleNotFoundException( NotFoundException ex ) {
        return buildErrorResponse( HttpStatus.NOT_FOUND, ex.getMessage() );
    }

    @ExceptionHandler( BadRequestException.class )
    public ResponseEntity<Map<String, Object>> handleBadRequestException( BadRequestException ex ) {
        return buildErrorResponse( HttpStatus.BAD_REQUEST, ex.getMessage() );
    }

    @ExceptionHandler( BadCredentialsException.class )
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException( BadCredentialsException ex ) {
        return buildErrorResponse( HttpStatus.BAD_REQUEST, ex.getMessage() );
    }

    @ExceptionHandler( JwtException.class )
    public ResponseEntity<Map<String, Object>> handleJwtException( JwtException ex ) {
        return buildErrorResponse( HttpStatus.FORBIDDEN, ex.getMessage() );
    }

    @ExceptionHandler( AuthenticationException.class )
    public ResponseEntity<Map<String, Object>> handleAuthenticationException( AuthenticationException ex ) {
        return buildErrorResponse( HttpStatus.UNAUTHORIZED, ex.getMessage() );
    }

    @ExceptionHandler( SignatureException.class )
    public ResponseEntity<Map<String, Object>> handleSignatureException( SignatureException ex ) {
        return buildErrorResponse( HttpStatus.FORBIDDEN, ex.getMessage() );
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse( HttpStatus httpStatus, String message ) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", httpStatus.value());
        response.put("message", message);

        return new ResponseEntity<>( response, httpStatus );
    }
}

