package org.example.menuparser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.example.menuparser.model.MenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class WebPageReader implements DataReader<MenuItem> {
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "selenium/chromedriver.exe";
    private static final String HEADLESS_ARGUMENT = "--headless";
    private static final String EXCEPTION_MESSAGE = "Can't interact with elements on the page ";

    public List<MenuItem> readData(String url) {
        List<MenuItem> menus = new ArrayList<>();

        WebDriver driver = initializeDriver();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            List<WebElement> dishLists = driver.findElements(By.className("dish-list"));
            for (WebElement dishList : dishLists) {
                WebElement category = dishList.findElement(By.className("title"));
                List<WebElement> items = dishList.findElements(By.className("menu-list-item"));
                for (WebElement item : items) {
                    WebElement name = item.findElement(By.className("item-title"));
                    String description = getDescription(item);
                    MenuItem menuItem = new MenuItem(
                            category.getText(), name.getText(), description);
                    menus.add(menuItem);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(EXCEPTION_MESSAGE + url, e);
        } finally {
            driver.quit();
        }
        return menus;
    }

    private WebDriver initializeDriver() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER,
                CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(HEADLESS_ARGUMENT);
        return new ChromeDriver(options);
    }

    private String getDescription(WebElement menuItem) {
        String descriptionText = "";
        WebElement description;
        try {
            description = menuItem.findElement(By.className("item-description"));
        } catch (NoSuchElementException e) {
            description = null;
        }

        if (description != null) {
            descriptionText = description.getText();
        }
        return descriptionText;
    }
}
