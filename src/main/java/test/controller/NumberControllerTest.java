package test.controller;

import wunit.annotation.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberControllerTest {

    @Test
    public static void validateListSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        assertThat(list).hasSize(5);
    }

    @Test
    public static void validateStringReference() {
        String a = "a";
        String b = new String("a");

        assertThat(a == b).isTrue();
    }
}
