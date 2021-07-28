package com.junit5tests;

import org.junit.jupiter.api.*;

// Annotation that helps to use @BeforeAll without making them static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstTestClass {

    @BeforeAll
    public void beforAll(){
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
    @DisplayName("US01 - TC1 - Second Test Method")
    public void secondMethod() {
        System.out.println("This is the second test method");
    }
}
