package com.senkosun.web_calculator;

import com.senkosun.web_calculator.service.CalcServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebCalculatorApplicationTests {

	private CalcServiceImpl calculator;

	@BeforeEach
	void setUp() {
		calculator = new CalcServiceImpl();
	}

	@Test
	@DisplayName("Addition test")
	void testRightAdd() {
		assertTrue( calculator.validate("67"));
		assertTrue( calculator.validate("2+3"));
		assertTrue( calculator.validate(" 1 + 4 "));
		assertTrue( calculator.validate("5 + 6"));
		assertTrue( calculator.validate(" 7+8 "));
		assertTrue( calculator.validate("9+ 10"));
		assertTrue( calculator.validate("121141 +0"));
	}

	@Test
	@DisplayName("Subtraction test")
	void testRightSub() {
		assertTrue( calculator.validate("2-3"));
		assertTrue( calculator.validate(" 1 - 4 "));
		assertTrue( calculator.validate("5 - 6"));
		assertTrue( calculator.validate(" 7-8 "));
		assertTrue( calculator.validate("9- 10"));
		assertTrue( calculator.validate("121141 -0"));
	}

	@Test
	@DisplayName("Multiplication test")
	void testRightMult() {
		assertTrue( calculator.validate("2*3"));
		assertTrue( calculator.validate(" 1 * 4 "));
		assertTrue( calculator.validate("5 * 6"));
		assertTrue( calculator.validate(" 7*8 "));
		assertTrue( calculator.validate("9* 10"));
		assertTrue( calculator.validate("121141 *0"));
	}

	@Test
	@DisplayName("Division test")
	void testRightDiv() {
		assertTrue( calculator.validate("2/3"));
		assertTrue( calculator.validate(" 1 / 4 "));
		assertTrue( calculator.validate("5 / 6"));
		assertTrue( calculator.validate(" 7/8 "));
		assertTrue( calculator.validate("9/ 10"));
		assertTrue( calculator.validate("121141 /0"));
	}

	@Test
	@DisplayName("Right complex test")
	void testRightCompl() {
		assertTrue( calculator.validate("1+2+3+5+8+13"));
		assertTrue( calculator.validate(" 1/2/3/4"));
		assertTrue( calculator.validate("5*6*9"));
		assertTrue( calculator.validate("-1-2-3"));
		assertTrue( calculator.validate("1*2-3+4/5+6-7*9+10"));
		assertTrue( calculator.validate("123456789/2*123456789+1"));
	}

	@Test
	@DisplayName("Incorrect complex test")
	void testIncorrectCompl() {
		assertFalse( calculator.validate("2//3"));
		assertFalse( calculator.validate(" 1  4 "));
		assertFalse( calculator.validate("5+-2"));
		assertFalse( calculator.validate("533--4"));
		assertFalse( calculator.validate("--1--2+3"));
		assertFalse( calculator.validate("*1+2*4"));
	}

}
