package test.controller;

import wunit.annotation.Test;
import wunit.assertion.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberControllerTest {

    @Test
    public void validateListSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Assertion.assertTrue(list.size() == 5);
    }

    @Test
    public void validateStringReference() {
        String a = "a";
        String b = new String("a");

        Assertion.assertTrue(a == b);
    }

    @Test
    public void indexOutOfBoundsException() {
        List<Integer> list = new ArrayList<>();

        Assertion.assertThatCode(() -> list.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index: 1, Size: 0");
    }

    @Test
    public void wrongException() {
        List<Integer> list = new ArrayList<>();

        Assertion.assertThatCode(() -> list.get(1))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Index: 1, Size: 0");
    }

    @Test
    public void wrongExceptionMessage() {
        List<Integer> list = new ArrayList<>();

        Assertion.assertThatCode(() -> list.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index: 1, Size: 31");
    }

    @Test
    public void doesNotThrowAnyException() {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        Assertion.assertThatCode(() -> list.get(0))
                .doesNotThrowAnyException();
    }

    @Test
    public void throwExceptionActually() {
        List<Integer> list = new ArrayList<>();

        Assertion.assertThatCode(() -> list.get(0))
                .doesNotThrowAnyException();
    }
}
