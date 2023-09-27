package com.example.categorymodule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.categorymodule.exception.RecordNotFoundException;



class RecordNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        RecordNotFoundException exception = new RecordNotFoundException();

        assertEquals(null, exception.getMessage());
    }

    @Test
    void testParameterizedConstructor() {
        String errorMessage = "Record not found";
        RecordNotFoundException exception = new RecordNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}

