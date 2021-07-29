package com.junit5tests;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

@SuppressWarnings("unused") // Suppressing here for no use since this is a providing class
public class ParamsProvider {
    public static Stream<Arguments> sourceClassStream_StringDouble() {
        //Processing done here
        return Stream.of(Arguments.arguments("pineapple", 6.5),
                Arguments.arguments("mango", 4.5),
                Arguments.arguments("cherry", 8.9));
    }
}
