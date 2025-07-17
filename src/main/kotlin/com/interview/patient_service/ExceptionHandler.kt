package com.interview.patient_service.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

data class ApiError(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatus(
        ex: ResponseStatusException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val statusCode = ex.statusCode

        val reason = (statusCode as? HttpStatus)?.reasonPhrase ?: statusCode.toString()

        val apiError = ApiError(
            status  = statusCode.value(),
            error   = reason,
            message = ex.reason ?: "Unexpected error",
            path    = request.requestURI
        )
        return ResponseEntity(apiError, statusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val apiError = ApiError(
            status  = status.value(),
            error   = status.reasonPhrase,
            message = ex.message ?: "Unexpected error",
            path    = request.requestURI
        )
        return ResponseEntity(apiError, status)
    }
}
