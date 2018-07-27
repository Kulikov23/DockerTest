package dockerTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//@RunWith(CucumberWithSerenity.class)

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/GooleSearch.fetaure")
public class DefinitionTestSuite {}

