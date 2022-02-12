package com.intelliarts.test.exception.handler;


import com.intelliarts.test.exception.category.CategoryItemsException;
import com.intelliarts.test.exception.category.CategoryNameException;
import com.intelliarts.test.exception.category.CategoryPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CategoryHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryItemsException.class)
    public ResponseEntity<ErrorMessage> handleException(CategoryItemsException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryPriceException.class)
    public ResponseEntity<ErrorMessage> handleException(CategoryPriceException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNameException.class)
    public ResponseEntity<ErrorMessage> handleException(CategoryNameException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
