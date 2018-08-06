package Capabilities;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class FirefoxCapabilities {

    public class MyFirefoxDriver implements DriverSource {

        @Override
        public WebDriver newDriver() {
//            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//            capabilities.setCapability("resolution", "1280x1024");
//            capabilities.setCapability("marionette", false);

            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", false);

            return new FirefoxDriver(GeckoDriverService.createDefaultService(), capabilities);
        }

        @Override
        public boolean takesScreenshots() {
            return true;
        }
    }


}

