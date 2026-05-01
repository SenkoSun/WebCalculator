package com.senkosun.web_calculator.service;

import java.util.Deque;

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
        return null;
    }

    @Override
    public String calculate(Deque<String> rpn) {
        return "";
    }
}
