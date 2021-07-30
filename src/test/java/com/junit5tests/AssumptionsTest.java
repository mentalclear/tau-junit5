package com.junit5tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

// Chapter 9 - Assumptions

public class AssumptionsTest {
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @ValueSource(ints = {1, 5 ,6})
    public void intValues(int theParam){

        // Skips the execution for theParam that is 1
        Assumptions.assumeTrue(theParam > 4);
        System.out.println("theParam = " + theParam);
    }

    @ParameterizedTest
    @CsvSource(value = {"Steve,Rogers", "Captain,Marvel", "Bucky,Barnes"})
    public void csvSource_StringString(String param1, String param2){
        // Specifying the message that will be displayed when assumption is triggered
        Assumptions.assumeFalse(param1.equals("Steve"), "The assumption failed for the " +
                "following param2: " + param2);
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @CsvSource(value = {"Steve, 32, true", "Captain, 21, false", "Bucky, 5, true"})
    public void csvSource_StringIntBoolean(String param1, int param2, boolean param3){
        // In this case the assumption runs independently
        // and has no impact on the code execution below
        Assumptions.assumingThat(param2 > 20, () -> System.out.println("This code ran"));
        System.out.println("param1 = " + param1 + ", param2 = " + param2 + ", param3 = " + param3);
    }
}
