package com.ths.resfulapikotlin.controller

import com.ths.resfulapikotlin.error.NotFoundException
import com.ths.resfulapikotlin.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value=[ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException):WebResponse<String>{
        return WebResponse(
            code =400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException:NotFoundException):WebResponse<String>{
        return WebResponse(
            code =404,
            status = "NOT FOUND",
            data = "NOTFOUND"
        )
    }
}