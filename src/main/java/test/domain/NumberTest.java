package test.domain;

import wunit.annotation.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class NumberTest {

    @Test
    public static void compareFailed() {
        int a = 3;
        int b = 6;

        assertThat(a).isEqualTo(b);
    }

    @Test
    public static void comparePassed() {
        int a = 4;
        int b = 4;

        assertThat(a).isEqualTo(b);
    }

    @Test
    public static void divideFailed() {
        int a = 3;
        int b = 0;

        int c = a / b;
    }

    @Test
    public static void dividePassed() {
        assertThatCode(() -> {
            int c = 3 / 0;
        }).isInstanceOf(ArithmeticException.class);
    }

    @Test
    void invalidTestIgnored() {
    }
}
