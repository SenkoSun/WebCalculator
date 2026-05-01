package com.senkosun.web_calculator.service;

import java.util.Deque;

public interface CalcService {

    public boolean validate(String expression);

    public Deque<String> convertToRPN(String expression);

    public String calculate(Deque<String> rpn);

}
