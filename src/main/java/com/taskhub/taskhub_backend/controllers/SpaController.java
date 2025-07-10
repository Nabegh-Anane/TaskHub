package com.taskhub.taskhub_backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping(value = {"/{path:^(?!api|swagger-ui|v3|index\\.html|static|favicon\\.ico|.*\\.(css|js|png|jpg|svg|woff2?)$).*$}", "/**/{path:^(?!api|swagger-ui|v3|index\\.html|static|favicon\\.ico|.*\\.(css|js|png|jpg|svg|woff2?)$).*$}"})
    public String forward(HttpServletRequest request) {
        return "forward:/index.html";
    }
}
