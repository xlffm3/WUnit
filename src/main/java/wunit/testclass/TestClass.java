package wunit.testclass;

import wunit.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClass {

    private final Class<?> testClass;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public void runTest() {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                } catch (IllegalAccessException e) {
                    System.out.println("잘못된 사용");
                } catch (InvocationTargetException e) {
                    System.out.println("예외 발생");
                }
            }
        }
    }
}
