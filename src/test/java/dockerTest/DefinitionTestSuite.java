package dockerTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

//@RunWith(CucumberWithSerenity.class)

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/GooleSearch.fetaure")
public class DefinitionTestSuite {}

