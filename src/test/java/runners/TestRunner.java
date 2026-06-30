package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.Test;
import utilClass.RetryUtil;

@CucumberOptions(features = "src/test/resources/features", glue = "stepdefinitions", plugin = {
        "pretty",
        "html:target/cucumber-report/Cucumber.html",
        "json:target/cucumber-report/CucumberTestReport.json",
        "timeline:target/cucumber-report/timeline"
})

public class TestRunner extends AbstractTestNGCucumberTests {

    private static final int MAX_RETRY = 1;

    @Override
    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {

        RetryUtil.retry(new Runnable() {

            @Override
            public void run() {
                TestRunner.super.runScenario(pickleWrapper, featureWrapper);
            }

        }, MAX_RETRY);
    }
}
