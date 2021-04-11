package wunit.testclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VerificationResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationResult.class);
    private static final String LOG_FORMAT = "Total : {}, Passed : {}, Failed : {}, Error : {}";

    private final List<TestCaseResult> testCaseResults;

    public VerificationResult(List<TestCaseResult> testCaseResults) {
        this.testCaseResults = testCaseResults;
    }

    public void logTestStatistics() {
        int totalTestCaseCounts = testCaseResults.size();
        int passCaseCounts = calculateTestCaseCountsByResult(TestCaseResult.PASS);
        int failCaseCounts = calculateTestCaseCountsByResult(TestCaseResult.FAIL);
        int errorCaseCounts = totalTestCaseCounts - passCaseCounts - failCaseCounts;
        LOGGER.info(LOG_FORMAT, totalTestCaseCounts, passCaseCounts, failCaseCounts, errorCaseCounts);
    }

    private int calculateTestCaseCountsByResult(TestCaseResult expectedResult) {
        return (int) testCaseResults.stream()
                .filter(testCaseResult -> testCaseResult.isSameAs(expectedResult))
                .count();
    }
}
