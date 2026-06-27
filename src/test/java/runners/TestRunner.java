package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    plugin = {
        "pretty",
        "html:target/cucumber-report/Cucumber.html",
        "json:target/cucumber-report/CucumberTestReport.json",
        "timeline:target/cucumber-report/timeline",
        "rerun:target/failed_scenarios.txt"
    }
)

public class TestRunner extends AbstractTestNGCucumberTests{
    
}
