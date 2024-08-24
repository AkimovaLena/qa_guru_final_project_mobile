package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public class PresetsPage {

    private final SelenideElement permissionDenyButton = $(id("com.android.permissioncontroller:id/permission_deny_button")),
            buttonNotUpdate = $x("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button");

    ElementsCollection widgetEditTexts = $$(className("android.widget.TextView"));

    @Step("Выбираем страну {0}")
    public PresetsPage selectCountry(String country) {
        widgetEditTexts.findBy(text(country)).click();
        return this;
    }

    @Step("Отказываемся от уведомлений")
    public PresetsPage notGetNotifications() {
        permissionDenyButton.click();
        return this;
    }

    @Step("Не обновлям приложене")
    public PresetsPage notUpdate() {
        buttonNotUpdate.click();
        return this;
    }


}
