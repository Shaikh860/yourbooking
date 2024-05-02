package com.yourhome.controller;


import com.yourhome.entity.TrainingCenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/training")
public class TrainingCenterController {

    @PostMapping("/training-centers")
    public ResponseEntity<TrainingCenter> createTrainingCenter(@Valid @RequestBody TrainingCenter trainingCenter) {
        // Populate createdOn with server timestamp
        trainingCenter.setCreatedOn(System.currentTimeMillis());
        // Save the training center to database (implementation not shown)
        // Return the saved training center in JSON format
        return new ResponseEntity<>(trainingCenter, HttpStatus.CREATED);
    }

    // Exception handler for validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getDefaultMessage()).append("; ");
        });
        return errors.toString();
    }
}

