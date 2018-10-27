package net.tecgurus.courses.util;

import lombok.Getter;

@Getter
public enum EventMessage {

    UNEXPECTED_SERVER_ERROR(1, "Unexpected server error. Call technical support.");

    private Integer code;
    private String message;

    EventMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
