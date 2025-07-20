package edu.kh.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import edu.kh.project.test.Calculator;

// JUnit 테스트 코드는 반드시 src/test/java 경로에 위치해야함!

// src/main/java : 실제 어플리케이션 코드
// src/test/java : 테스트 코드 (JUnit 포함됨)
public class CalculatorTest {
	
	private static Calculator calculator;
	
	// @BeforeAll, @AfterAll - static 메서드에서만 사용가능
	@BeforeAll
	static void initAll() {
		// 모든 테스트 실행 전에 1번만 실행
		calculator = new Calculator();
		System.out.println("@BeforeAll - 테스트 시작 전 초기화");
	}
	
	@AfterAll
	static void end() {
		// 모든 테스트가 끝난 뒤 1번만 실행
		System.out.println("@AfterAll - 모든 테스트 완료");
	
	}

}
