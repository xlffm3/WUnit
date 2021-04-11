package test.controller;

import wunit.annotation.Test;
import wunit.assertion.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberControllerTest {

    @Test
    public static void validateListSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Assertion.assertTrue(list.size() == 5);
    }

    @Test
    public static void validateStringReference() {
        String a = "a";
        String b = new String("a");

        Assertion.assertTrue(a == b);
    }

    @Test
    public static void validateIndex() {
        List<Integer> list = new ArrayList<>();

        Assertion.assertThatCode(() -> list.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index: 1, Size: 0");
    }
}
