package com.listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class Listener implements TestWatcher {

    // Chapter 16 - TestWatcher

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("------------------------");
        System.out.println("This test was disabled: "
                + context.getTestMethod() + " with reason: " + reason);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("------------------------");
        System.out.println("This test was Successful");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("------------------------");
        System.out.println("This test was aborted: "  + cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("------------------------");
        System.out.println("This test failed: "  + cause.getMessage());
    }
}
