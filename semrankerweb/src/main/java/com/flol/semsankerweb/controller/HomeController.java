package com.flol.semsankerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {
 
    @RequestMapping("/index")
    String index() {
        return "index";
    }
    
    @RequestMapping("/")
    String home() {
        return "home";
    }
}
