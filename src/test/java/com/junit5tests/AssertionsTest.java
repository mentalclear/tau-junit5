package com.junit5tests;

import com.listeners.Listener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// Tests in this class generate a bunch of warnings, suppressing those.
@SuppressWarnings("all")

@ExtendWith(Listener.class)   // Chapter 16 Suggested adding this
public class AssertionsTest {

    // Chapter 13 Assertions

    @Test
    public void assertEqualsTest(){
        assertEquals("firstString", "secondString", "The Strings aren't equal");
    }

    @Test
    public void assertEqualsListTest(){
        List<String> expectedValues = Arrays
                .asList("firstString","secondString", "thirdString");

        List<String> actualValues = Arrays
                .asList("firstString","secondString");

        assertEquals(expectedValues, actualValues);
        // assertNotEquals();  // Also can be used.
    }

    @Test
    public void assertArraysEqualsTest() {
        int[] expectedValues = {1, 2, 3};
        int[] actualValues = {1, 2, 3};

        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void assertTrueTest(){
        assertTrue(false, "Didn't evaluate to true");
        assertFalse(false);
    }

    // My method that returns null
    public Executable nullTrigger(){
        return null;
    }

    @Test
    public void assertThrowsTest(){
        assertThrows(NullPointerException.class, null); // Example from the course
        assertThrows(NullPointerException.class, nullTrigger());
    }

    @Test
    public void assertAllTest(){
        assertAll(
                () -> assertEquals("firstString", "secondString", "The Strings aren't equal"),
                () -> assertThrows(NullPointerException.class, null),
                () -> assertTrue(false, "Didn't evaluate to true")
        );
    }

    // Chapter 14 Hamcrest

    @Test
    public void assertForMapTest() {
        Map<String, Integer> theMap = new HashMap<>();
        theMap.put("firstKey", 1);
        theMap.put("secondKey2", 2);
        theMap.put("thirdKey", 3);

        assertThat(theMap, hasValue(22));
        assertThat(theMap, hasKey("secondKey"));
    }

    @Test
    public void assertForALIst() {
        List<String> theList = Arrays.asList("firstString","secondString", "thirdString");
        assertThat(theList, hasItem("thirdString"));
        assertThat(theList, hasItem("thirdStrings"));
    }

    @Test
    public void assertForAnyOf() {
        List<String> theList = Arrays.asList("firstString", "secondString", "thirdString");
        assertThat(theList, anyOf(hasItem("thirdString"),
                hasItem("noString")));

        // Will fail, not all are in the list
        assertThat(theList, allOf(hasItem("thirdString"),
                hasItem("noString")));
    }

    @Test
    public void assertForContainsInAnyOrder() {
        List<String> theList = Arrays.asList("secondString", "firstString", "thirdString");
        assertThat(theList, containsInAnyOrder("firstString", "secondString", "thirdString"));
    }
}