package com.junit5tests;

import org.junit.jupiter.params.provider.Arguments;
import java.util.List;
import java.util.stream.Stream;

// This is an interface to deal with unused warnings in the class
// this way the methods are defined and used gracefully.
public interface ParamsInterface {
    List<String> sourceString();
    Stream<String> sourceStringAsStream();
    List<Arguments> source_StringDouble();
    Stream<Arguments> sourceStream_StringDouble();
}
