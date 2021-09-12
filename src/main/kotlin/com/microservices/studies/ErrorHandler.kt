package com.microservices.studies

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
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