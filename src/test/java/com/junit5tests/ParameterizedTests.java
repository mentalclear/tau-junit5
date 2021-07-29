package com.junit5tests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTests implements ParamsInterface {

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

    // Chapter 6

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/params/shoppinglist.csv",
            numLinesToSkip = 1) // The first line in .csv is a header, this is to skip that line
    public void csvFileSource_StringDoubleIntStringString(String name,
                                                          double price, int qty, String uom,
                                                          String provider){
        System.out.println("name = " + name + ", price = " + price + ", qty = " + qty
                + ", uom = " + uom + ", provider = " + provider);
    }

    // Multiple .csv can be supplied to the test
    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/params/shoppinglist.csv",
            "src/test/resources/params/shoppinglist2.csv"},
            numLinesToSkip = 1) // The first line in .csv is a header, this is to skip that line
    public void csvFileSource_StringDoubleIntStringString2(String name,
                                                          double price, int qty, String uom,
                                                          String provider){
        System.out.println("name = " + name + ", price = " + price + ", qty = " + qty
                + ", uom = " + uom + ", provider = " + provider);
    }

    // Using different delimiter in the .csv
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/params/shoppinglist3.csv",
            numLinesToSkip = 1, delimiterString = "___") // Delimiter string in this case.
    public void csvFileSource_StringDoubleIntStringString3(String name,
                                                           double price, int qty, String uom,
                                                           String provider){
        System.out.println("name = " + name + ", price = " + price + ", qty = " + qty
                + ", uom = " + uom + ", provider = " + provider);
    }
    // Important note. If I need an empty string - in the .csv it should be provided
    // as empty quotes see shoppinglist3.csv line 2 for example.
    // For null value the value shouldn't be provided at all see shoppinglist3.csv line 3

    // Chapter 7 - @MethodSource annotation

    @ParameterizedTest
    @MethodSource(value = "sourceString") // Method should return Stream compatible
    public void methSource_String(String param1){
        System.out.println("param1 = " + param1);
    }

    // This is a method that will be used in the test, by default it should be static
    // but with class annotation @TestInstance that can be avoided
    public List<String> sourceString() {
        // Processing is done here
        return Arrays.asList("tomato", "carrot", "cabbage");
    }

    public Stream<String> sourceStringAsStream() {
        // Processing is done here
        return Stream.of("beetroot", "apple", "grape");
    }

    @ParameterizedTest
    @MethodSource(value = "sourceStringAsStream") // Stream example
    public void methSource_StringFromStream(String param1){
        System.out.println("param1 = " + param1);
    }

    // Custom arguments provider method with a List
    public List<Arguments> source_StringDouble() {
        //Processing done here
        return Arrays.asList(Arguments.arguments("tomato", 2.0),
                Arguments.arguments("carrot", 4.5),
                Arguments.arguments("cabbage", 7.8));
    }

    @ParameterizedTest
    @MethodSource(value = "source_StringDouble") // Method with custom arguments supplied
    public void methSource_StringDouble(String param1, double param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    // Custom arguments provider method with a Stream
    public Stream<Arguments> sourceStream_StringDouble() {
        //Processing done here
        return Stream.of(Arguments.arguments("peach", 2.5),
                Arguments.arguments("plum", 7.5),
                Arguments.arguments("banana", 3.8));
    }

    @ParameterizedTest
    @MethodSource(value = "sourceStream_StringDouble") // Method with custom arguments supplied
    public void methSourceStream_StringDouble(String param1, double param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    // Using provider Methods from a separate class
    // See ParamsProvider class as a source of the method
    @ParameterizedTest
    // This is the way how to point to a method from another class use pound character
    @MethodSource(value = "com.junit5tests.ParamsProvider#sourceClassStream_StringDouble")
    public void methodFromClassSourceStream_StringDouble(String param1, double param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }
}
