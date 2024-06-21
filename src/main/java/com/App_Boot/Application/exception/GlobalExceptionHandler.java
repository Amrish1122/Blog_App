package com.App_Boot.Application.exception;
import com.App_Boot.Application.payload.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }
   @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>>handleConstraintViolationException(ConstraintViolationException ex){
      Map<String,String> resp = new HashMap<>();
       StringBuilder errorMsg = new StringBuilder();

       for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
           errorMsg.append(violation.getMessage()).append(" ");
           System.out.println();

       }

       resp.put("errorMsg", errorMsg.toString());

      return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
        String errorField = ((FieldError)error).getField();
          String errorMsg =   error.getDefaultMessage();
          resp.put(errorField,errorMsg);

                });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }



}
