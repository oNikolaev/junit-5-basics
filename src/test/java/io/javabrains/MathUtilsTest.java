package io.javabrains;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("When running test utils")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    void beforeAllInit() {
        System.out.println("This needs to be run before all tests...");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        
        mathUtils = new MathUtils();
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }

    @Test
    @DisplayName("Testing add method")
    @Tag("Math")
    void add_two_numbers() {
        int expected = 2;
        int actual = mathUtils.add(1, 1);
        assertEquals(expected, actual, "The add metohd should add two numbers");
    }

    @Test
    @Tag("Circle")
    void compute_circle_area() {
        boolean isServerUp = false;
        assumeTrue(isServerUp);

        double actual = mathUtils.computeCircleArea(10);
        assertEquals( 314.1592653589793, actual, "Should return right area");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void throw_if_argument_is_zero() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by zero should throw");
    }

    @Test
    @DisplayName("TDD Method. Should not run")
    @Disabled
    void testDisabled() {
        fail("This test should be disabled");
    }

    @Test
    @Tag("Math")
    @DisplayName("Test multiply")
    void testMultiply() {
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        assertAll(
                () -> assertEquals(4, mathUtils.multiplay(2, 2)),
                () -> assertEquals(0, mathUtils.multiplay(2, 0)),
                () -> assertEquals(-2, mathUtils.multiplay(2, -1))
        );
    }


    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...");
    }

    @Nested
    @DisplayName("Add method")
    class addTest {

        @Test
        @DisplayName("when adding two positive numbers")
        void testAddPositive() {
            assertEquals(2, mathUtils.add(1, 1), "should return right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative() {
            assertEquals(-2, mathUtils.add(-1, -1), "should return right sum");
        }
    }

}
