package com.senkosun.web_calculator.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CalcServiceImpl implements CalcService{
    @Override
    public boolean validate(String expr) {
        // Проверка на пустоту
        if (expr == null || expr.trim().isEmpty()) return true;

        // Проверка на "1 2" (два числа подряд)
        if (expr.replaceAll("\\s+", " ").matches(".*\\d+\\s+\\d+.*")) return false;

        // Удаляем пробелы
        String clean = expr.replaceAll("\\s", "");

        // Проверка: первый символ (цифра или минус), последний (цифра)
        if ((!Character.isDigit(clean.charAt(0)) && clean.charAt(0) != '-')
                || !Character.isDigit(clean.charAt(clean.length() - 1))) return false;

        // Если первый символ минус, проверяем что после него цифра
        if (clean.charAt(0) == '-' && !Character.isDigit(clean.charAt(1))) return false;

        // Проверка последовательности: не должно быть двух операторов подряд
        for (int i = 1; i < clean.length(); i++) {
            char c = clean.charAt(i), p = clean.charAt(i - 1);
            if ((c == '-' || c == '+' || c == '*' || c == '/') && ((p == '+' || p == '*' || p == '/') || p == '-')) return false;
            if (!Character.isDigit(c) && c != '+' && c != '*' && c != '/' && c != '-') return false;
        }
        return true;
    }

    @Override
    public Deque<String> convertToRPN(String expression) {

        Map<Character, Integer> priority = new HashMap<>(Map.of(
                '+', 1,
                '-', 1,
                '*', 2,
                '/', 2
        ));
        Deque<Character> stack = new ArrayDeque<>();
        Deque<String> res = new ArrayDeque<>();
        String expr = expression.replaceAll("\\s", "");
        boolean flag = false;
        for (int i = 0; i < expr.length(); i++) {
            char symbol = expr.charAt(i);
            if (symbol == '.' || (flag && Character.isDigit(symbol))) {
                String last = res.isEmpty() ? "" : res.removeLast();
                res.addLast(last + symbol);
            } else if (symbol == '-' || symbol == '+' || symbol == '*' || symbol == '/') {
                flag = false;
                if (priority.getOrDefault(stack.peek(), 0) >= priority.getOrDefault(symbol, 0)) res.push(stack.pop()+"");
                stack.push(symbol);
            } else {
                flag = true;
                res.addLast(symbol+"");
            }
        }
        while (!stack.isEmpty()) {
            res.addLast(stack.pop()+"");
        }
        return res;

    }

    @Override
    public String calculate(Deque<String> rpn) {
        if (rpn.isEmpty()) {
            return "≽^•⩊•^≼";
        }
        Deque<Double> stack = new ArrayDeque<>();
        for (String token : rpn) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": if (b == 0) return "Division by zero"; stack.push(a / b); break;
                    default: return "ERROR";
                }
            }
        }
        return String.valueOf(stack.pop());
    }
}
