package dockerTest.steps.serenity;

import dockerTest.pages.GooglePage;
import dockerTest.pages.TestSite;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class EndUserSteps {

    GooglePage googlePage;
    TestSite testSite;

    @Step
    public void openHomePage() {
        googlePage.open();
    }

    @Step
    public void writeIntoSearchInput(String arg0) {
        googlePage.writeIntoSearchInput(arg0);
    }

    @Step
    public void clickOnTheSearchButton() {
        googlePage.clickOnTheGoogleSearchButton();
    }

    @Step
    public void checkThatAppearedInSiteList(String arg0) {
        Assert.assertTrue(googlePage.checkThatAppearedInSiteList(arg0));
    }

    @Step
    public void clickNTheLinkThatAppearedInSiteList(String arg0) {
        googlePage.clickNTheLinkThatAppearedInSiteList(arg0);
    }

    @Step
    public void checkThatSiteLogoAppeared() {
        Assert.assertTrue(testSite.checkThatSiteLogoAppeared());
    }

    @Step
    public void clickOnTheHABRSearchButton() {
        testSite.clickOnTheHABRSearchButton();
    }

    @Step
    public void writeIntoHABRSearchField(String arg0) {
        testSite.writeIntoHABRSearchField(arg0);
    }

    @Step
    public void checkThatPostIsVisible(String arg0) {
        Assert.assertTrue(testSite.checkThatPostIsVisible(arg0));
    }

    @Step
    public void clickOnThePostTitle(String arg0) {
        testSite.clickOnThePostTitle(arg0);
    }

    @Step
    public void checkThatPostIsOpened(String arg0) {
        Assert.assertTrue(testSite.checkThatPostIsOpened(arg0));
    }
}