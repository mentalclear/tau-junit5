package com.junit5tests;

import org.junit.jupiter.api.*;

public class MiscTest {

    // Chapter 17 - Timeout, Nested Tests, Custom Annotations

    @Test
    @Timeout(5)  // In seconds by default
    //@Timeout(value = 5, unit = TimeUnit.MINUTES) // If longer - fails
    public void timeout() throws InterruptedException {
        Thread.sleep(6000);
        System.out.println("This is the test with a timeout");
    }

    // Nested test example
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class NestedTest {
        @BeforeAll
        void beforeAll(){
            System.out.println("Before all!");
        }

        @Test
        void nestedTestMethod(){
            System.out.println("This is Nested test method");
        }
    }

    @Test
    @Timeout(90)
    @DisplayName("This is a nice method")
    @Tag("TheTag")
    public void annotatedMethod(){
        System.out.println("This is the annotated method");
    }

    // Custom annotation allows only 1 second of timeout. Hence fails
    @MyAnnotation
    public void annotatedMethod2() throws InterruptedException {
        System.out.println("This is the custom annotated method");
        Thread.sleep(3000);
    }
}
