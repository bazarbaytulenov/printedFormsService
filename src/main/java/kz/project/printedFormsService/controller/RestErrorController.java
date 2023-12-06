package kz.project.printedFormsService.controller;

;
import kz.project.printedFormsService.ValidationException;
import kz.project.printedFormsService.data.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestErrorController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    //@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Remote service response")
    protected ResponseEntity<ResponseErrorDto> handleThereIsNoSuchUserException(ValidationException  err) {
        err.printStackTrace();
        return new ResponseEntity<>(new ResponseErrorDto(err.getMessage(),err.getCode()),HttpStatus.BAD_REQUEST);
    }

}
