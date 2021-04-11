package wunit;

import wunit.testclass.TestClassLoader;
import wunit.testclass.TestClasses;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        TestClasses testClasses = TestClasses.from(TestClassLoader.loadTestClasses("test"));
        testClasses.runTests();
    }
}
