package kg.attractor.job_search.exceptions;

import kg.attractor.job_search.model.CustomErrorResponse;
import kg.attractor.job_search.service.impl.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomErrorResponse> noSuchElementHandler(NoSuchElementException e){
        return new ResponseEntity<>( errorService.makeResponse(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> validationHandler(MethodArgumentNotValidException e){
        return new ResponseEntity<> (errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
