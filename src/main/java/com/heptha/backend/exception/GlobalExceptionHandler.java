package com.heptha.backend.exception;

import com.heptha.backend.dto.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExists(
            UserAlreadyExistsException ex) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .statusCode(400)
                        .data(null)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(
            RuntimeException ex) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .statusCode(400)
                        .data(null)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message("Invalid HTTP method: " + ex.getMethod())
                        .statusCode(405)
                        .data(null)
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleException(
//            Exception ex) {
//
//        ApiResponse<Object> response =
//                ApiResponse.builder()
//                        .success(false)
//                        .message("Something went wrong")
//                        .statusCode(500)
//                        .data(null)
//                        .build();
//
//        return new ResponseEntity<>(
//                response,
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(
            Exception ex) {

        ex.printStackTrace();

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .statusCode(500)
                        .data(null)
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}