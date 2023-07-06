package Cucumber001;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Cucumber001",
        plugin = {"pretty","html:test-output/cucumber/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
