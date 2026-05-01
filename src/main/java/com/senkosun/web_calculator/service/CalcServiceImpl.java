package com.senkosun.web_calculator.service;

import java.util.Deque;

public class CalcServiceImpl implements CalcService{
    @Override
    public boolean validate(String expression) {
        return false;
    }

    @Override
    public Deque<String> convertToRPN(String expression) {
        return null;
    }

    @Override
    public String calculate(Deque<String> rpn) {
        return "";
    }
}
