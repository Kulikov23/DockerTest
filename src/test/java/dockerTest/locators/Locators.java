package dockerTest.locators;

public interface Locators {
    String SEARCH_INPUT = "//input[@title='Пошук']";
    String SEARCH_BUTTON = "//input[@type='button' and @value='Пошук Google']";
    String LINK = "//a[text()='$1']";
    String HABR_LOGO = "//a[@class='logo']";
    String HABR_SRCH_BTN = "//*[@id='search-form-btn']";
    String HABR_SRCH_FIELD = "//*[@id='search-form-field']";
    String HABR_POST_PREVIEW_TITLE = "//li[@id='post_275513']//h2";
    String HABR_POST_TITLE = "//span[text()='$1']";
    String HABR_POST_IMAGE = "(//div[@class='post__body post__body_full']//img)[1]";
}
