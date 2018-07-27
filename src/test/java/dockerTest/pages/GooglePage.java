package dockerTest.pages;

import dockerTest.locators.Locators;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

@DefaultUrl("https://www.google.com/")
public class GooglePage extends PageObject {

    public void writeIntoSearchInput(String arg0) {
        withTimeoutOf(6, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.SEARCH_INPUT)));
        $(Locators.SEARCH_INPUT).sendKeys(arg0);
    }

    public void clickOnTheGoogleSearchButton() {
        withTimeoutOf(6, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.SEARCH_INPUT)));
        $(Locators.SEARCH_BUTTON).click();
    }

    public boolean checkThatAppearedInSiteList(String arg0) {
        return $(Locators.LINK.replace("$1", arg0)).isVisible();
    }

    public void clickNTheLinkThatAppearedInSiteList(String arg0) {
        $(Locators.LINK.replace("$1",arg0)).click();
    }
}