package kz.project.printedFormsService.exception;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class ValidationException extends Exception {
    private String message;
    private int code;

    public ValidationException(String message){
        super(message);
    }
    public ValidationException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
