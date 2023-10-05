package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin= {"html: target/cucumber.html"},
		features="features",
		glue="stepDefinitions")
public class SteperRunnerTest {

}
