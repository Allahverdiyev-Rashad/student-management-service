package az.spring.studentmanagementservice.studentmanagementservice.error;

import az.spring.studentmanagementservice.studentmanagementservice.enums.ErrorCode;
import az.spring.studentmanagementservice.studentmanagementservice.exception.ServiceException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(ServiceException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getCode());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentTypeMismatchException ex) {

        String parameterName = ex.getParameter().getParameter().getName();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ErrorCode.VALIDATION_ERROR.name());
        errorResponse.setMessage(parameterName + ErrorMessage.VALIDATION_ERROR);
        return errorResponse;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ErrorCode.VALIDATION_ERROR.name());
        errorResponse.setMessage(ErrorMessage.STUDENT_NOT_FOUND);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValidException(MethodArgumentNotValidException ex){

       String fieldName = ex.getBindingResult().getFieldError().getField();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ErrorCode.VALIDATION_ERROR.name());
        errorResponse.setMessage(fieldName + ErrorMessage.VALIDATION_ERROR);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleUnknownException(Exception e) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ErrorCode.INTERNAL_SERVER_ERROR.name());
        errorResponse.setMessage(ErrorMessage.INTERNAL_SERVER_ERROR);
        return errorResponse;
    }

}