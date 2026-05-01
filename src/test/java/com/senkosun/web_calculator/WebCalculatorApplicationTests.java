package com.senkosun.web_calculator;

import com.senkosun.web_calculator.service.CalcServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@SpringBootTest
class WebCalculatorApplicationTests {

	private CalcServiceImpl calculator;


	@Nested
	class ValidateTests {
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
	}

	@Nested
	class ConvertToRpnTests {
		@BeforeEach
		void setUp() {
			calculator = new CalcServiceImpl();
		}

		@Test
		@DisplayName("ConvertToRpn Single number")
		void testSingleNumber() {
			Deque<String> result = calculator.convertToRPN("42");
			Deque<String> expected = new ArrayDeque<>(List.of("42"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Addition")
		void testAddition() {
			Deque<String> result = calculator.convertToRPN("2+3");
			Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "+"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Subtraction")
		void testSubtraction() {
			Deque<String> result = calculator.convertToRPN("5-2");
			Deque<String> expected = new ArrayDeque<>(List.of("5", "2", "-"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Multiplication")
		void testMultiplication() {
			Deque<String> result = calculator.convertToRPN("4*3");
			Deque<String> expected = new ArrayDeque<>(List.of("4", "3", "*"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Division")
		void testDivision() {
			Deque<String> result = calculator.convertToRPN("10/2");
			Deque<String> expected = new ArrayDeque<>(List.of("10", "2", "/"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Multiplication before addition")
		void testPriority1() {
			Deque<String> result = calculator.convertToRPN("2+3*4");
			Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Division before subtraction")
		void testPriority2() {
			Deque<String> result = calculator.convertToRPN("10-6/2");
			Deque<String> expected = new ArrayDeque<>(List.of("10", "6", "2", "/", "-"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Multiple operations")
		void testMultipleOps() {
			Deque<String> result = calculator.convertToRPN("2+3*4-5/2");
			Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+", "5", "2", "/", "-"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Same priority left to right")
		void testSamePriority() {
			Deque<String> result = calculator.convertToRPN("2+3+4");
			Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "+", "4", "+"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn With spaces")
		void testWithSpaces() {
			Deque<String> result = calculator.convertToRPN(" 2 + 3 * 4 ");
			Deque<String> expected = new ArrayDeque<>(List.of("2", "3", "4", "*", "+"));
			assertDequeEquals(expected, result);
		}

		@Test
		@DisplayName("ConvertToRpn Multi-digit numbers")
		void testMultiDigit() {
			Deque<String> result = calculator.convertToRPN("12+34*56");
			Deque<String> expected = new ArrayDeque<>(List.of("12", "34", "56", "*", "+"));
			assertDequeEquals(expected, result);
		}

		// Вспомогательный метод для сравнения Deque
		private void assertDequeEquals(Deque<String> expected, Deque<String> actual) {
			assertEquals(new ArrayList<>(expected), new ArrayList<>(actual));
		}
	}



	@Nested
	class CalculateTests {
		@BeforeEach
		void setUp() {
			calculator = new CalcServiceImpl();
		}

		@Test
		@DisplayName("Calculate Single number")
		void testCalcSingleNumber() {
			Deque<String> rpn = new ArrayDeque<>(List.of("42"));
			String result = calculator.calculate(rpn);
			assertEquals("42", result);
		}

		@Test
		@DisplayName("Calculate Addition")
		void testCalcAddition() {
			Deque<String> rpn = new ArrayDeque<>(List.of("2", "3", "+"));
			String result = calculator.calculate(rpn);
			assertEquals("5", result);
		}

		@Test
		@DisplayName("Calculate Subtraction")
		void testCalcSubtraction() {
			Deque<String> rpn = new ArrayDeque<>(List.of("5", "2", "-"));
			String result = calculator.calculate(rpn);
			assertEquals("3", result);
		}

		@Test
		@DisplayName("Calculate Multiplication")
		void testCalcMultiplication() {
			Deque<String> rpn = new ArrayDeque<>(List.of("4", "3", "*"));
			String result = calculator.calculate(rpn);
			assertEquals("12", result);
		}

		@Test
		@DisplayName("Calculate Division")
		void testCalcDivision() {
			Deque<String> rpn = new ArrayDeque<>(List.of("10", "2", "/"));
			String result = calculator.calculate(rpn);
			assertEquals("5", result);
		}

		@Test
		@DisplayName("Calculate Division with remainder")
		void testDivisionRemainder() {
			Deque<String> rpn = new ArrayDeque<>(List.of("7", "2", "/"));
			String result = calculator.calculate(rpn);
			assertEquals("3", result);
		}

		@Test
		@DisplayName("Calculate Multiple operations")
		void testCalcMultipleOps() {
			Deque<String> rpn = new ArrayDeque<>(List.of("2", "3", "4", "*", "+"));
			String result = calculator.calculate(rpn);
			assertEquals("14", result);
		}

		@Test
		@DisplayName("Calculate Complex expression")
		void testCalcComplex() {
			Deque<String> rpn = new ArrayDeque<>(List.of("2", "3", "4", "*", "+", "5", "2", "/", "-"));
			String result = calculator.calculate(rpn);
			assertEquals("12", result);
		}

		@Test
		@DisplayName("Calculate Division by zero throws exception")
		void testCalcDivisionByZero() {
			Deque<String> rpn = new ArrayDeque<>(List.of("10", "0", "/"));
			Exception exception = assertThrows(ArithmeticException.class, () -> {
				calculator.calculate(rpn);
			});
			assertEquals("Division by zero", exception.getMessage());
		}

		@Test
		@DisplayName("Calculate Division by zero in complex expression")
		void testCalcDivisionByZeroComplex() {
			Deque<String> rpn = new ArrayDeque<>(List.of("5", "0", "/", "3", "+"));
			Exception exception = assertThrows(ArithmeticException.class, () -> {
				calculator.calculate(rpn);
			});
			assertEquals("Division by zero", exception.getMessage());
		}
	}
}
