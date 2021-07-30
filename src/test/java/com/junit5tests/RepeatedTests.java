package com.junit5tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RepeatedTests {

    // Repeated test execution (times)
    @RepeatedTest(5)
    public void firstRepeatedMethod() {
        System.out.println("We are repeating this test");
    }

    // Changing the form of the output
    @RepeatedTest( value = 3, name = "Running repetition: {currentRepetition}." +
    " Total is: {totalRepetitions}")
    @DisplayName("This is the repeated test")
    public void secondRepeatedMethod() {
        System.out.println("We are repeating this test");
    }

    @RepeatedTest(3)
    // Uses the parameter from @RepeatedTest
    public void thirdRepeatedMethod(RepetitionInfo repetitionInfo) {
        System.out.println("This one runs on every repetition,\ncurrent is: " + repetitionInfo.getCurrentRepetition());
        // Assumptions work as control flow logic here.
        Assumptions.assumingThat(repetitionInfo.getCurrentRepetition() == 2,
                () -> System.out.println("This code only runs for repetition 2" ));
    }
}
