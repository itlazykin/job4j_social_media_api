package ru.job4j.social.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public void catchDataIntegrityViolationException(Exception e,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws IOException {
        Map<String, String> details = new HashMap<>();
        details.put("message", e.getMessage());
        details.put("type", String.valueOf(e.getClass()));
        details.put("timestamp", String.valueOf(LocalDateTime.now()));
        details.put("path", request.getRequestURI());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(details));
        log.error(e.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошла ошибка: " + e.getMessage());
    }
}
