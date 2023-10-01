package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/features",glue="stepDefinitions")
public class TestRunner extends AbstractTestNGCucumberTests {

}
