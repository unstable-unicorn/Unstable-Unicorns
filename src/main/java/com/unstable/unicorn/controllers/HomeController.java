package com.unstable.unicorn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "", method=RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}
