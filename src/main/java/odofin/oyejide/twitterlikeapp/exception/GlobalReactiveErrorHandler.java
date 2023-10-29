package odofin.oyejide.twitterlikeapp.exception;

import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.BadRequestException;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.RecordNotFounException;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.UserNotFoundException;
import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static odofin.oyejide.twitterlikeapp.utils.MessageUtil.*;

@ControllerAdvice
@Slf4j
public class GlobalReactiveErrorHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(FAIL, HttpStatus.BAD_REQUEST.value(), String.join(",", errors))));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleUserNotFoundException(UserNotFoundException exception) {
        return exceptionMessage(exception, HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleSqlException(SQLException exception) {
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    @ExceptionHandler(PropertyAccessException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handlePropertyAccessException(PropertyAccessException exception) {
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    @ExceptionHandler(RecordNotFounException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleRecordNotFounException(RecordNotFounException exception) {
        return exceptionMessage(exception, HttpStatus.NOT_FOUND.value(), RECORD_NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleBadRequestExceptions(BadRequestException exception) {
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return exceptionMessage(exception, HttpStatus.EXPECTATION_FAILED.value(), exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Mono<ResponseEntity<ApiResponse<String>>> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception) {
        String error = exception.getName() + " should be of type " + exception.getRequiredType().getName();
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), error);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handlerGlobalError(Exception exception) {
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    private static Mono<ResponseEntity<ApiResponse<String>>> exceptionMessage(Exception exception, int statusCode, String errorMessage) {
        log.warn("{} {} occurred", Exception.class.getSimpleName().toUpperCase(), exception.getMessage());
        return Mono.just(ResponseEntity.status(statusCode).body(new ApiResponse<>(FAIL, statusCode, errorMessage)));
    }
}
