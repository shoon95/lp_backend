package com.lp.couple.exception;

import com.lp.couple.dto.ResponseDto;
import com.lp.couple.exception.authException.EmailAlreadyExistsException;
import com.lp.couple.exception.authException.PasswordMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseDto<Object>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ResponseDto<Object> responseDto = ResponseDto.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ResponseDto<Object>> handlePasswordMismatchException(PasswordMismatchException ex) {
        ResponseDto<Object> responseDto = ResponseDto.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
