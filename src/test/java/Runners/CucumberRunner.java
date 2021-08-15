package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main.resources/main.resources.Features.main.resources.Features",
                 glue = "Steps",
                 plugin = {"pretty"})
public class CucumberRunner extends AbstractTestNGCucumberTests{
}
