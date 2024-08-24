package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public class CatalogPage {

    private final SelenideElement categoriesLazyGrid = $x("//android.view.View[@resource-id=\"categoriesLazyGrid\"]"),
            categoriesLazyList = $x("//android.view.View[@resource-id=\"categoriesLazyList\"]"),
            toolbarTitle = $(id("com.wildberries.ru:id/toolbarTitle"));

    ElementsCollection widgetTextView = $$(className("android.widget.TextView"));

    @Step("Проверяем, что страницица содержит категории")
    public CatalogPage checkCatalog() {
        categoriesLazyGrid.shouldBe(enabled);
        return this;
    }

    @Step("Проверяем, что страницица содержит список подкатегории")
    public CatalogPage checkCategory() {
        categoriesLazyList.shouldBe(enabled);
        return this;
    }

    @Step("Открываем категорию {0}")
    public CatalogPage openCategory(String category) {
        widgetTextView.findBy(text(category)).click();
        return this;
    }

    @Step("Проверяем, что заголовок страницы равен {0}")
    public CatalogPage checkTitle(String title) {
        toolbarTitle.shouldHave(text(title));
        return this;
    }


}
