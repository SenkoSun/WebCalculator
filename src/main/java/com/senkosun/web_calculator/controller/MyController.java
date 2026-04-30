package com.senkosun.web_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller  // ВАЖНО: @Controller, а не @RestController!
public class MyController {

    @GetMapping("/")
    public String hello(Model model) {
        // Добавляем данные в модель
        model.addAttribute("title", "Hello World!");
        model.addAttribute("message", "Добро пожаловать в Spring Boot с Thymeleaf!");
        model.addAttribute(
                "serverTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        );

        // Возвращаем имя html файла (без расширения)
        return "hello";
    }
}