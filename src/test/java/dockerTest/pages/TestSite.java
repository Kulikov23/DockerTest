package dockerTest.pages;

import dockerTest.locators.Locators;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

@DefaultUrl("https://habr.com/")
public class TestSite extends PageObject {

    public boolean checkThatSiteLogoAppeared() {
        return $(Locators.HABR_LOGO).isVisible();
    }

    public void clickOnTheHABRSearchButton() {
        $(Locators.HABR_SRCH_BTN).click();
    }

    public void writeIntoHABRSearchField(String arg0) {
        $(Locators.HABR_SRCH_FIELD).sendKeys(arg0);
        $(Locators.HABR_SRCH_FIELD).sendKeys(Keys.ENTER);
    }

    public boolean checkThatPostIsVisible(String arg0) {
        return $(Locators.HABR_POST_PREVIEW_TITLE).isVisible() && $(Locators.HABR_POST_PREVIEW_TITLE).getText().contains(arg0);
    }

    public void clickOnThePostTitle(String arg0) {
        $(Locators.HABR_POST_PREVIEW_TITLE).click();
    }

    public boolean checkThatPostIsOpened(String arg0) {
        return $(Locators.HABR_POST_TITLE.replace("$1", arg0)).isVisible() && $(Locators.HABR_POST_IMAGE).isVisible();
    }
}
