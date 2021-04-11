package wunit;

import wunit.testclass.TestClassLoader;
import wunit.testclass.TestClasses;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestClasses testClasses = TestClasses.from(TestClassLoader.findTestClasses("test"));
        testClasses.runTests();
    }
}
