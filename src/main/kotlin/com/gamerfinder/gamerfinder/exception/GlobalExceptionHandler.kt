package com.gamerfinder.gamerfinder.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RoomAlreadyExistsException::class)
    fun handleRoomAlreadyExistsException(
        exception: RoomAlreadyExistsException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "Conflict",
            message = exception.message ?: "Room already exists",
            status = HttpStatus.CONFLICT.value(),
            path = request.requestURI,
        )
        return ResponseEntity(error, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "Not Found",
            message = exception.message ?: "Resource not found",
            status = HttpStatus.NOT_FOUND.value(),
            path = request.requestURI,
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleRequestValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "Bad Request",
            message = exception.bindingResult.fieldErrors.joinToString {
                "${it.field}: ${it.defaultMessage}"
            },
            status = HttpStatus.BAD_REQUEST.value(),
            path = request.requestURI,
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        exception: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "Internal Server Error",
            message = exception.message ?: "An unexpected error occurred",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            path = request.requestURI,
        )
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}