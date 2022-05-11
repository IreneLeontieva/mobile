package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserStackMobileDriver;
import helpers.Attach;
import io.appium.java_client.MobileBy;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = BrowserStackMobileDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
        step("Pass welcome", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();
        Attach.video(sessionId);


    }
}