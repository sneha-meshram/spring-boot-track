package com.stackroute.exceptions;


import com.stackroute.domain.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<String>notFoundException(final TrackAlreadyExistsException e){
        return new ResponseEntity<>("error"+e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
