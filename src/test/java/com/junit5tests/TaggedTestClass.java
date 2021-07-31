package com.junit5tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// Annotation that helps to use @BeforeAll without making them static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaggedTestClass {

    // Chapter 12 - Tags

    @BeforeAll
    public void beforeAll(){
        System.out.println("--This is the before All method");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("----This the before Each method");
    }

    @AfterAll
    public void afterAll(){
        System.out.println("--This is the after All method");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("----This the after Each method");
    }

    @Test
    @Tag("Sanity")  // If used must be filled with value, not empty and null, no reserved chars
    public void firstMethod() {
        System.out.println("This is the first test method");
    }

    @Test
    @Tag("Sanity")
    @Tag("Acceptance")
    @DisplayName("US01 - TC1 - Second Test Method")  // Test name changes in the Run by this
    public void secondMethod() {
        System.out.println("This is the second test method");
    }

    @Test
    @Tag("Acceptance")
    public void thirdMethod(){
        System.out.println("This is the third test method");
    }

    @Tag("Acceptance")
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @ValueSource(ints = {1, 5 ,6})
    public void intValues(int theParam){
        System.out.println("theParam = " + theParam);
    }

}
