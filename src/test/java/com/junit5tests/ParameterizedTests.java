package com.junit5tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTests {

    // Chapter 4

    //@ParameterizedTest  // Default return for parametrized test
    @ParameterizedTest(name = "Run: {index} - value: {arguments}") // Changes the output from
    @ValueSource(ints = {1, 5 ,6}) // Providing an array of values, only directly
    public void intValues(int theParam){
        System.out.println("theParam = " + theParam);
    }

    @ParameterizedTest
//  @NullSource // If null needs to be passed.
//  @EmptySource // Providing empty input for Strings etc, no primitives. Returned in first result.
    @NullAndEmptySource  // Combine the two on the top
    @ValueSource(strings = {"firstString","secondString"})
    public void stringValues(String theParam){
        System.out.println("theParam = " + theParam);
    }

    // Ideas on how this can be used:
    public final String CONST_STRING1 = "This is one!";
    public final String CONST_STRING2 = "This is two!";

    // Interesting idea is to be able to test with null or empty string input.
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {CONST_STRING1, CONST_STRING2, "This is three!"}) // If variables used must be constants
    public void anotherParamTest(String params) {
        if (params == null || params.equals("")) {
            System.out.println("Empty or null param: " + params);
        } else {
            System.out.println("And this: " + params);
        }
    }

    // Chapter 5

    @ParameterizedTest
    @CsvSource(value = {"Steve,Rogers", "Captain,Marvel", "Bucky,Barnes"}) // CSV format here
    public void csvSource_StringString(String param1, String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @CsvSource(value = {"Steve, 32, true", "Captain, 21, false", "Bucky, 5, true"})
    public void csvSource_StringIntBoolean(String param1, int param2, boolean param3){
        System.out.println("param1 = " + param1 + ", param2 = " + param2 + ", param3 = " + param3);
    }

    @ParameterizedTest
    // Escaping the comma with single quotes, use + to continue on another line
    @CsvSource(value = {"Captain America, 'Steve,Rogers'", "Winter Soldier," +
        "'Bucky, Barnes'"})
    public void csvSource_String_StringWithComma(String parma1, String param2){
        System.out.println("parma1 = " + parma1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    // Using another delimiter instead of comma
    @CsvSource(value = {"Steve?Rogers","Bucky?Barnes"}, delimiter = '?')
    public void csvSource_StringWithDifferentDelimiter(String param1, String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    // Ideas - some simple test can be written using those parameters in CSV,
    // but most likely CSV file would be much more interesting to use.

}
