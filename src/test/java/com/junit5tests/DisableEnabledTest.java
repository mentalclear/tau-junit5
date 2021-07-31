package com.junit5tests;

import com.listeners.Listener;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

// Chapter 10 - Disabling test
@ExtendWith(Listener.class)  // Added in Chapter 16 - TestWatcher
public class DisableEnabledTest {

    @Test
    // Will be ignored when the class executed.
    @Disabled(value = "Disabled for demo of @Disabled")
    public void firstTest() {
        System.out.println("This is the first test!");
    }

    @Test
    // Will not run on a specific OS
    // @EnabledOnOs() Can be used to enable OS based execution
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Demo @DisabledOnOs")
    public void secondTest() {
        System.out.println("This is the second test!");
    }

    @Test
    // Property is set in the configurations, look there for the setup.
    @DisabledIfSystemProperty(named = "env", matches = "staging", disabledReason = "We are on staging!")
    public void thirdTest() {
        System.out.println("This is the third test!");
    }

    @Test
    @DisabledIf(value = "provider", disabledReason = "Disabled for demo of @DisabledIf")
    public void fourthTest() {
        System.out.println("This is the fourth test!");
    }

    @Test
    public void fifthTest() {
        System.out.println("This is the fifth test!");
    }

    public boolean provider() {
        return LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }
}
