package dockerTest.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dockerTest.steps.serenity.EndUserSteps;
import net.thucydides.core.annotations.Steps;

public class DefinitionSteps {

    @Steps
    EndUserSteps anna;

    @Given("^open Google search page$")
    public void openGoogleSearchPage() throws Throwable {
        anna.openHomePage();
    }

    @When("^write \"([^\"]*)\" into search input$")
    public void writeIntoSearchInput(String arg0) throws Throwable {
        anna.writeIntoSearchInput(arg0);
    }

    @When("^Click on the search button$")
    public void clickOnTheSearchButton() throws Throwable {
        anna.clickOnTheSearchButton();
    }

    @Then("^Check that \"([^\"]*)\" appeared in site list$")
    public void checkThatAppearedInSiteList(String arg0) throws Throwable {
        anna.checkThatAppearedInSiteList(arg0);
    }

    @When("^click n the \"([^\"]*)\" link that appeared in site list$")
    public void clickNTheLinkThatAppearedInSiteList(String arg0) throws Throwable {
        anna.clickNTheLinkThatAppearedInSiteList(arg0);
    }

    @Then("^Check that site logo appeared$")
    public void checkThatSiteLogoAppeared() throws Throwable {
        anna.checkThatSiteLogoAppeared();
    }

    @When("^Click on the HABR search button$")
    public void clickOnTheHABRSearchButton() throws Throwable {
        anna.clickOnTheHABRSearchButton();
    }

    @When("^Write \"([^\"]*)\" into HABR search field and press ENTER$")
    public void writeIntoHABRSearchField(String arg0) throws Throwable {
        anna.writeIntoHABRSearchField(arg0);
    }

    @Then("^Check that \"([^\"]*)\" post is visible$")
    public void checkThatPostIsVisible(String arg0) throws Throwable {
        anna.checkThatPostIsVisible(arg0);
    }

    @When("^Click on the \"([^\"]*)\" post title$")
    public void clickOnThePostTitle(String arg0) throws Throwable {
        anna.clickOnThePostTitle(arg0);
    }

    @Then("^Check that \"([^\"]*)\" post is opened$")
    public void checkThatPostIsOpened(String arg0) throws Throwable {
        anna.checkThatPostIsOpened(arg0);
    }
}
