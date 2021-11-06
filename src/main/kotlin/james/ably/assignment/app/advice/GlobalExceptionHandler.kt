package james.ably.assignment.app.advice

import james.ably.assignment.app.dto.Response
import james.ably.assignment.app.exception.IntendedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IntendedException::class)
    fun handleException(ex: IntendedException): ResponseEntity<Response<Any?>> {
        return ResponseEntity.ok(
            Response.fail(null, ex.message)
        )
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun notValidException(ex: MethodArgumentNotValidException): ResponseEntity<Response<Any?>> {
        return ResponseEntity.ok(
            Response.fail(null, ex.bindingResult.allErrors[0].defaultMessage)
        )
    }
}