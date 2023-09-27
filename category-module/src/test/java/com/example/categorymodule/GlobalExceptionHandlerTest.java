package com.example.categorymodule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.categorymodule.exception.GlobalExceptionHandler;
import com.example.categorymodule.exception.RecordNotFoundException;



class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testExceptionHandler() {
        String errorMessage = "Internal server error";
        Exception exception = new Exception(errorMessage);

        String result = globalExceptionHandler.exceptionHandler(exception);

        assertEquals(errorMessage, result);
    }

    @Test
    void testHandleRecordNotFoundException() {
        String errorMessage = "Record not found";
        RecordNotFoundException exception = new RecordNotFoundException(errorMessage);

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleRecordNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}

