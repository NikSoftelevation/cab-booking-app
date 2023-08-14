package com.cab.booking.exceeption;

import com.cab.booking.payload.ErrorDetail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetail> userException(UserException e, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> otherExceptionHandler(Exception e, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        ErrorDetail errorDetail = new ErrorDetail(exception.getBindingResult().getFieldError().getDefaultMessage(), "Validation Error", LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverException.class)
    public ResponseEntity<ErrorDetail> driverExceptionHandler(DriverException e, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(RideException.class)
    public ResponseEntity<ErrorDetail> rideExceptionHandler(RideException e, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(), request.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<>(errorDetail, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetail> handleValidationException(ConstraintViolationException e) {

        StringBuilder errorMessage = new StringBuilder();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {

            errorMessage.append(violation.getMessage() + "\n");

        }
        ErrorDetail errorDetail = new ErrorDetail(errorMessage.toString(), "Validation Error", LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
