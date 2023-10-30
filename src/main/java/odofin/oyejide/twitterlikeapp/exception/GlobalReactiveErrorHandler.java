package odofin.oyejide.twitterlikeapp.exception;

import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.BadRequestException;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.InvalidTokenException;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.RecordNotFounException;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.UnAuthorizedException;
import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static odofin.oyejide.twitterlikeapp.utils.MessageUtil.*;

@ControllerAdvice
@Slf4j
public class GlobalReactiveErrorHandler{

    /**
     * Exception handler for handling validation errors in request payloads.
     *
     * @param exception The WebExchangeBindException containing validation errors.
     * @return A response entity with details of the validation errors.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handleMethodArgumentNotValid(
            WebExchangeBindException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(FAIL, HttpStatus.BAD_REQUEST.value(), String.join(",", errors))));
    }

    /**
     * Exception handler for handling invalid token exceptions.
     *
     * @param exception The InvalidTokenException.
     * @return A response entity with details of the invalid token exception.
     */
    @ExceptionHandler(InvalidTokenException.class)
    public Mono<ResponseEntity<ApiResponse<Object>>> handleUserNotFoundException(InvalidTokenException exception) {
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), exception);
    }

    /**
     * Exception handler for handling unauthorized access exceptions.
     *
     * @param exception The UnAuthorizedException.
     * @return A response entity with details of the unauthorized access exception.
     */
    @ExceptionHandler(UnAuthorizedException.class)
    public Mono<ResponseEntity<ApiResponse<Object>>>
    handlerUnAuthorizedException(UnAuthorizedException exception) {
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), exception.getResponse());
    }

    /**
     * Exception handler for handling SQL exceptions.
     *
     * @param exception The SQLException.
     * @return A response entity with details of the SQL exception.
     */
    @ExceptionHandler(SQLException.class)
    public Mono<ResponseEntity<ApiResponse<String>>>
    handleSqlException(SQLException exception) {
        exception.printStackTrace();
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    /**
     * Exception handler for handling property access exceptions.
     *
     * @param exception The PropertyAccessException.
     * @return A response entity with details of the property access exception.
     */
    @ExceptionHandler(PropertyAccessException.class)
    public Mono<ResponseEntity<ApiResponse<String>>>
    handlePropertyAccessException(PropertyAccessException exception) {
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    /**
     * Exception handler for handling record not found exceptions.
     *
     * @param exception The RecordNotFounException.
     * @return A response entity with details of the record not found exception.
     */
    @ExceptionHandler(RecordNotFounException.class)
    public Mono<ResponseEntity<ApiResponse<String>>>
    handleRecordNotFounException(RecordNotFounException exception) {
        return exceptionMessage(exception, HttpStatus.OK.value(), exception.getMessage());
    }

    /**
     * Exception handler for handling bad request exceptions.
     *
     * @param exception The BadRequestException.
     * @return A response entity with details of the bad request exception.
     */
    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ApiResponse<String>>>
    handleBadRequestExceptions(BadRequestException exception) {
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * Exception handler for handling illegal argument exceptions.
     *
     * @param exception The IllegalArgumentException.
     * @return A response entity with details of the illegal argument exception.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ApiResponse<String>>>
    handleIllegalArgumentException(IllegalArgumentException exception) {
        return exceptionMessage(exception, HttpStatus.EXPECTATION_FAILED.value(), exception.getMessage());
    }

    /**
     * Exception handler for handling method argument type mismatch exceptions.
     *
     * @param exception The MethodArgumentTypeMismatchException.
     * @return A response entity with details of the method argument type mismatch exception.
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Mono<ResponseEntity<ApiResponse<String>>> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception) {
        String error = exception.getName() + " should be of type " + exception.getRequiredType().getName();
        return exceptionMessage(exception, HttpStatus.BAD_REQUEST.value(), error);
    }

    /**
     * Exception handler for handling generic exceptions.
     *
     * @param exception The generic Exception that occurred.
     * @return A Mono containing a ResponseEntity with details of the exception.
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiResponse<String>>> handlerGlobalError(Exception exception) {
        return exceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_ERROR);
    }

    /**
     * Constructs a ResponseEntity containing details of the exception.
     *
     * @param exception  The Exception that occurred.
     * @param statusCode The HTTP status code to be set in the ResponseEntity.
     * @param errorMessage The error message to be included in the response.
     * @param <T>        The type of the error message.
     * @return A Mono containing a ResponseEntity with details of the exception.
     */
    private static <T> Mono<ResponseEntity<ApiResponse<T>>> exceptionMessage(
            Exception exception, int statusCode, T errorMessage) {
        // Log the occurrence of the exception
        log.warn("{} {} occurred", Exception.class.getSimpleName().toUpperCase(), exception.getMessage());

        // Construct and return a ResponseEntity
        return Mono.just(ResponseEntity.status(statusCode).body(new ApiResponse<>(FAIL, statusCode, errorMessage)));
    }

}
