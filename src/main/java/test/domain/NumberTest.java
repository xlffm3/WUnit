package test.domain;

import wunit.annotation.Test;
import wunit.assertion.Assertion;

public class NumberTest {

    @Test
    public void compareFailed() {
        int a = 3;
        int b = 6;

        Assertion.assertTrue(a == b);
    }

    @Test
    public void comparePassed() {
        int a = 4;
        int b = 4;

        Assertion.assertTrue(a == b);
    }

    @Test
    public void divideFailed() {
        int a = 3;
        int b = 0;

        Assertion.assertTrue((a / b) == 0);
    }

    @Test
    public void dividePassed() {
        Assertion.assertThatCode(() -> {
            int c = 3 / 0;
        }).isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");
    }

    @Test
    void invalidTestIgnored() {
    }
}
