package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDrivers;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    static final String deviceHost = System.getProperty("deviceHost", "local");


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        switch (deviceHost) {
            case ("browserstack"):
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case ("local"):
                Configuration.browser = LocalDrivers.class.getName();
                break;
            default:
                System.out.println("Неверно указан девайс");
                break;
        }

        Configuration.timeout = 30000;

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        Attach.pageSource();
        closeWebDriver();
        if ((deviceHost == null) || deviceHost.equals("browserstack")) {
            String sessionId = Selenide.sessionId().toString();
            Attach.addVideo(sessionId);
        }
    }
}