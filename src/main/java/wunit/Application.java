package wunit;

import wunit.testclass.TestClassLoader;
import wunit.testclass.TestClasses;
import wunit.testclass.VerificationResult;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        TestClasses testClasses = TestClasses.from(TestClassLoader.loadTestClasses("test"));
        VerificationResult verificationResult = testClasses.runAllTests();
        verificationResult.logTestStatistics();
    }
}
