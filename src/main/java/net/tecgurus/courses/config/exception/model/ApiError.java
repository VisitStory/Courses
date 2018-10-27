package net.tecgurus.courses.config.exception.model;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Data
public class ApiError {

    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private Object message;
    private String technicalMessage;
    private String path;

    public ApiError() {
        timestamp = Instant.now().toEpochMilli();
    }

    public ApiError(Exception ex, String message, HttpStatus status, WebRequest request) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = ex.getClass().getTypeName();
        this.message = message;
        this.technicalMessage = ex.getMessage();
        //this.path = request.getDescription(false);
        this.path = ((ServletWebRequest) request).getRequest().getServletPath();
    }

}
