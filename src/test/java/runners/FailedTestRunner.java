package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "@target/failed_scenarios.txt",
    glue = "stepdefinitions",
    plugin = {
        "html:target/cucumber-report/rerun-report.html"
    }
)

public class FailedTestRunner extends AbstractTestNGCucumberTests{
    
}
