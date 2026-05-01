package com.senkosun.web_calculator;

import com.senkosun.web_calculator.service.CalcServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@SpringBootTest
class WebCalculatorApplicationTests {

	private CalcServiceImpl calculator;

	@BeforeEach
	void setUp() {
		calculator = new CalcServiceImpl();
	}

	@Test
	@DisplayName("Validation Addition test")
	void testRightAdd() {
		assertTrue(calculator.validate("67"));
		assertTrue(calculator.validate("2+3"));
		assertTrue(calculator.validate(" 1 + 4 "));
		assertTrue(calculator.validate("5 + 6"));
		assertTrue(calculator.validate(" 7+8 "));
		assertTrue(calculator.validate("9+ 10"));
		assertTrue(calculator.validate("121141 +0"));
	}

	@Test
	@DisplayName("Validation Subtraction test")
	void testRightSub() {
		assertTrue(calculator.validate("2-3"));
		assertTrue(calculator.validate(" 1 - 4 "));
		assertTrue(calculator.validate("5 - 6"));
		assertTrue(calculator.validate(" 7-8 "));
		assertTrue(calculator.validate("9- 10"));
		assertTrue(calculator.validate("121141 -0"));
	}

	@Test
	@DisplayName("Validation Multiplication test")
	void testRightMult() {
		assertTrue(calculator.validate("2*3"));
		assertTrue(calculator.validate(" 1 * 4 "));
		assertTrue(calculator.validate("5 * 6"));
		assertTrue(calculator.validate(" 7*8 "));
		assertTrue(calculator.validate("9* 10"));
		assertTrue(calculator.validate("121141 *0"));
	}

	@Test
	@DisplayName("Validation Division test")
	void testRightDiv() {
		assertTrue(calculator.validate("2/3"));
		assertTrue(calculator.validate(" 1 / 4 "));
		assertTrue(calculator.validate("5 / 6"));
		assertTrue(calculator.validate(" 7/8 "));
		assertTrue(calculator.validate("9/ 10"));
		assertTrue(calculator.validate("121141 /0"));
	}

	@Test
	@DisplayName("Validation Right complex test")
	void testRightCompl() {
		assertTrue(calculator.validate("1+2+3+5+8+13"));
		assertTrue(calculator.validate(" 1/2/3/4"));
		assertTrue(calculator.validate("5*6*9"));
		assertTrue(calculator.validate("-1-2-3"));
		assertTrue(calculator.validate("1*2-3+4/5+6-7*9+10"));
		assertTrue(calculator.validate("123456789/2*123456789+1"));
	}

	@Test
	@DisplayName("Validation Incorrect complex test")
	void testIncorrectCompl() {
		assertFalse(calculator.validate("2//3"));
		assertFalse(calculator.validate(" 1  4 "));
		assertFalse(calculator.validate("5+-2"));
		assertFalse(calculator.validate("533--4"));
		assertFalse(calculator.validate("--1--2+3"));
		assertFalse(calculator.validate("*1+2*4"));
	}

	@Test
	@DisplayName("ConvertToRpn Single number")
	void testSingleNumber() {
		Deque<String> result = calculator.convertToRPN("42");
		Deque<String> expected = new ArrayDeque<>(List.of("42"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Addition")
	void testAddition() {
		Deque<String> result = calculator.convertToRPN("2+3");
		Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "+"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Subtraction")
	void testSubtraction() {
		Deque<String> result = calculator.convertToRPN("5-2");
		Deque<String> expected = new ArrayDeque<>(List.of("5", "2", "-"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Multiplication")
	void testMultiplication() {
		Deque<String> result = calculator.convertToRPN("4*3");
		Deque<String> expected = new ArrayDeque<>(List.of("4", "3", "*"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Division")
	void testDivision() {
		Deque<String> result = calculator.convertToRPN("10/2");
		Deque<String> expected = new ArrayDeque<>(List.of("10", "2", "/"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Multiplication before addition")
	void testPriority1() {
		Deque<String> result = calculator.convertToRPN("2+3*4");
		Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Division before subtraction")
	void testPriority2() {
		Deque<String> result = calculator.convertToRPN("10-6/2");
		Deque<String> expected = new ArrayDeque<>(List.of("10", "6", "2", "/", "-"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Multiple operations")
	void testMultipleOps() {
		Deque<String> result = calculator.convertToRPN("2+3*4-5/2");
		Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+", "5", "2", "/", "-"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Same priority left to right")
	void testSamePriority() {
		Deque<String> result = calculator.convertToRPN("2+3+4");
		Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "+", "4", "+"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn With spaces")
	void testWithSpaces() {
		Deque<String> result = calculator.convertToRPN(" 2 + 3 * 4 ");
		Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+"));
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("ConvertToRpn Multi-digit numbers")
	void testMultiDigit() {
		Deque<String> result = calculator.convertToRPN("12+34*56");
		Deque<String> expected = new ArrayDeque<>(List.of("12", "34", "56", "*", "+"));
		assertEquals(expected, result);
	}




}
