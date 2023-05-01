package org.example.exception;

import org.example.constant.EntityConstant;
import org.example.response.DataResponse;
import org.example.response.ErrorDataResponse;
import org.example.response.ErrorResponse;
import org.example.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DataResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        return new ErrorDataResponse(EntityConstant.VALIDATION_ERROR, validationErrors);
    }

    @ExceptionHandler(EntityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleEntityExceptions(EntityException entityException) {

        //if current entity should do anything when an error occurred. invoke handleException method..
        //maybe error may insert into the log database???
        //handleException method can be empty!
        entityException.handleException();

        return new ErrorResponse(entityException.getMessage());
    }
}
