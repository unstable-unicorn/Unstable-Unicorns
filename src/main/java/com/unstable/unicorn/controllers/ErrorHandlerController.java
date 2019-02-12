package com.unstable.unicorn.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController {
    @RequestMapping(value="/error")
    public String error() {
        return "Error Handling";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
