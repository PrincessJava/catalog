package ru.petproject.catalog.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.util.*
import javax.validation.ValidationException

@ControllerAdvice
class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDataFoundException::class)
    fun handleNodataFoundException(ex: NoDataFoundException, request: WebRequest):
            ResponseEntity<Map<String, Any>> {

        val body = createBodyMessage(ex.message)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException::class)
    fun handleValidationExceptions(ex: ValidationException): ResponseEntity<Map<String, Any>> {
        val body = createBodyMessage(ex.message)
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationExceptions(ex: ConstraintViolationException): ResponseEntity<Map<String, Any>> {
        val body = createBodyMessage(ex.message)
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    private fun createBodyMessage(message: String?): Map<String, Any> {
        val body: MutableMap<String, Any> = HashMap()
        body["date"] = Date()
        body["message"] = message.orEmpty()
        return body
    }
}
