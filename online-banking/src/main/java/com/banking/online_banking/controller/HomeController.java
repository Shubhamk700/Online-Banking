package com.banking.online_banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "🏦 Online Banking System");
        model.addAttribute("message", "Welcome to Online Banking! (Public Home Page)");
        return "home"; // loads home.html (in src/main/resources/templates/)
    }

    @GetMapping("/public-info")
    public String publicInfo(Model model) {
        model.addAttribute("title", "Public Information");
        model.addAttribute("message", "Here are some basic banking details available without login.");
        return "public-info"; // loads public-info.html
    }
}