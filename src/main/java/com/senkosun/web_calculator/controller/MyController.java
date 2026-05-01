package com.senkosun.web_calculator.controller;

import com.senkosun.web_calculator.service.CalcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    private final CalcService calcService;

    @Autowired
    public MyController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/")
    public String Calculate(Model model) {
        model.addAttribute("expression", "");
        model.addAttribute("needReset", "false");

        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("expression") String expression, Model model) {
        if (calcService.validate(expression)) {
            String result = calcService.calculate(calcService.convertToRPN(expression));
            model.addAttribute("expression", result);
            model.addAttribute("needReset", "false");
        } else {
            model.addAttribute("expression", "ERROR");
            model.addAttribute("needReset", "true");
        }
        return "calculator"; // возвращаем ту же страницу
    }

}