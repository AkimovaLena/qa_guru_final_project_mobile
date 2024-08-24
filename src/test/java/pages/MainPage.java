package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {

    private final SelenideElement search = $x("//android.view.View[@content-desc=\"Поиск\"]"),
            widgetEditText = $(className("android.widget.EditText"));

    ElementsCollection catalogContainer = $$(id("com.wildberries.ru:id/catalogContainer")),
            widgetEditTexts = $$(className("android.widget.TextView"));

    @Step("Добавляем первый продук в корзину")
    public MainPage addProduct() {
        catalogContainer.first().$(id("com.wildberries.ru:id/background")).click();
        return this;
    }

    @Step("Проверяем, что название кнопки равно {0}")
    public MainPage checkButtonName(String name) {
        catalogContainer.first().$(id("com.wildberries.ru:id/counterTv")).shouldHave(text(name));
        return this;
    }

    @Step("Вводим в строку поиска значение {0}")
    public MainPage inputSearchValue(String name) {
        search.click();
        widgetEditText.sendKeys(name);
        sleep(3000); //адержка чобы успел прогрузся подсказки
        return this;
    }

    @Step("Проверяем, что среди подсказок есть которые содержат поисковое слово {0}")
    public MainPage checkPromptSearch(String value) {
        assertTrue(!(widgetEditTexts.filterBy(text(value)).isEmpty()));
        return this;
    }

}

