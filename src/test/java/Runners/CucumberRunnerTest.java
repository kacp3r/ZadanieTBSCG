package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features",
                 glue = "Steps",
                 plugin = {"pretty"})
public class CucumberRunnerTest extends AbstractTestNGCucumberTests{
}
