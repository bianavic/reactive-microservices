package com.microservices.studies

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(CustomerNotFoundException::class)
    fun JsonParserExceptionHandler(servletRequest: HttpServletRequest,
                                   exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Customer not found", exception.message!! ),
            HttpStatus.BAD_REQUEST)
    }
}