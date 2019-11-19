package com.education.web.response;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private String message = "Success";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
