package wunit;

import wunit.testcase.TestClassLoader;
import wunit.testcase.TestClasses;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        TestClasses testClasses = TestClasses.from(TestClassLoader.loadTestClasses("test"));
        testClasses.runAllTests();
    }
}
