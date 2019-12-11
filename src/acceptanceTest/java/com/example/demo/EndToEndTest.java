package com.example.demo;

import com.example.demo.support.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class EndToEndTest extends AbstractIntegrationTest {

    private BrowserWebDriverContainer browser;

    @Before
    public void setUp() {
        Testcontainers.exposeHostPorts(localServerPort);


        browser = new BrowserWebDriverContainer<>()
                .withCapabilities(new ChromeOptions());
        browser.start();
    }

    @Test
    public void seleniumTest() {
        RemoteWebDriver webDriver = browser.getWebDriver();
        final String rootUrl =
                String.format("http://host.testcontainers.internal:%d/", localServerPort);
        webDriver.get(rootUrl + "/view/ratings/");
        WebElement content = webDriver.findElementById("content");

        Assertions.assertEquals(content.getText(), "Foobar");
    }

    @After
    public void tearDown() {
        browser.stop();
    }
}