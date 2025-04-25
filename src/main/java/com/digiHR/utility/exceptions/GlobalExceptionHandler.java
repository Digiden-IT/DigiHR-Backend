package com.digiHR.utility.exceptions;

import com.digiHR.utility.response.ApiErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.jsonwebtoken.JwtException;
import org.apache.coyote.BadRequestException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException( Exception ex ) {
        ex.printStackTrace();
        return buildErrorResponse( HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred" );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Map<String, Object>> handleValidationException( MethodArgumentNotValidException ex ) {
        return buildErrorResponse( HttpStatus.BAD_REQUEST, "Validation failed" );
    }

    @ExceptionHandler( HttpMessageNotReadableException.class )
    public ResponseEntity<Object> handleHttpMessageNotReadable( HttpMessageNotReadableException ex ) {
        if ( ex.getCause() instanceof InvalidFormatException ife ) {
            if ( ife.getTargetType().isEnum() ) {
                String enumType = ife.getTargetType().getSimpleName();
                String invalidValue = ife.getValue().toString();
                String message = "Invalid value '" + invalidValue + "' for enum " + enumType +
                        ". Please use one of: " + String.join( ", ",
                        java.util.Arrays.stream( ife.getTargetType().getEnumConstants() )
                                .map( Object::toString ).toList() );

                ApiErrorResponse errorResponse = new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        message
                );
                return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST );
            }
        }

        return new ResponseEntity<>( "Invalid request body", HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( DataIntegrityViolationException.class )
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException( DataIntegrityViolationException ex ) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage()
        );
        return new ResponseEntity<>( apiError, HttpStatus.CONFLICT );
    }

    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<?> handleNotFoundException( NotFoundException ex ) {
        return buildErrorResponse( HttpStatus.NOT_FOUND, ex.getMessage() );
    }

    @ExceptionHandler( NoResourceFoundException.class )
    public ResponseEntity<Map<String, Object>> handleNoResourceFoundException( NoResourceFoundException ex ) {
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

