package wunit.testcase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestClassLoaderTest {

    @DisplayName("Test 패키지에 속한 클래스들을 스캔한다.")
    @Test
    void loadTestClasses() throws ClassNotFoundException {
        List<Class<?>> testClasses = TestClassLoader.loadTestClasses("test");

        String controllerTestClass = testClasses.get(0).getName();
        String domainTestClass = testClasses.get(1).getName();

        assertThat(testClasses).hasSize(2);
        assertThat(controllerTestClass).isEqualTo("test.controller.NumberControllerTest");
        assertThat(domainTestClass).isEqualTo("test.domain.NumberTest");
    }
}
