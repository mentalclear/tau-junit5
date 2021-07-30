package com.junit5tests;

import org.junit.jupiter.api.*;

// Chapter 8

// Ordering test by method name
//@TestMethodOrder(MethodOrderer.MethodName.class)

// Order by Display name
@TestMethodOrder(MethodOrderer.DisplayName.class)

// Annotation that helps to use @BeforeAll without making them static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedTestClass1 {

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
    public void firstMethod() {
        System.out.println("This is the first test method");
    }

    @Test
    @DisplayName("US01 - TC1 - Second Test Method")  // Test name changes in the Run by this
    public void secondMethod() {
        System.out.println("This is the second test method");
    }

    @Test
    @DisplayName("A display name")
    public void thirdMethod(){
        System.out.println("Third test method!");
    }
}
