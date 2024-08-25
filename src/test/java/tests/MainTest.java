package tests;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.*;

@Epic("Тесты на мобильное приложение WB")
@DisplayName("Главная страница")
public class MainTest extends TestBase {
    PresetsPage presetsPage = new PresetsPage();
    MainMenuPage mainMenu = new MainMenuPage();
    CatalogPage catalogPage = new CatalogPage();
    MainPage mainPage = new MainPage();
    CartPage cartPage = new CartPage();

    @CsvSource(value = {
            "Женщинам, Блузки и рубашки",
            "Женщинам, Брюки",
            "Дом, Зеркала",
            "Игрушки, Антистресс",
    })
    @ParameterizedTest(name = "Проверяем открытие каталога {0}->{1}")
    @Tag("android")
    public void openCatalogTest(String category, String subdirectory) {
        presetsPage.notGetNotifications()
                .selectCountry("Россия")
                .notUpdate();
        mainMenu.openCatalog();
        catalogPage.checkCatalog()
                .openCategory(category)
                .checkCategory()
                .openCategory(subdirectory)
                .checkTitle(subdirectory);
    }


    @Test
    @DisplayName("Проверка добавления товара в корзину")
    @Tag("android")
    public void addProductInCart() {
        presetsPage.notGetNotifications()
                .selectCountry("Россия")
                .notUpdate();
        mainPage.addProduct()
                .checkButtonName("1");
        mainMenu.checkCountInCart("1")
                .openCart();
        cartPage.checkCart();
    }

    @CsvSource(value = {
            "Платье",
            "кастрюля",
    })
    @ParameterizedTest(name = "Проверяем подсказки при поиске по ключевому слову {0}")
    @Tag("android")
    public void searchTest(String value) {
        presetsPage.notGetNotifications()
                .selectCountry("Россия")
                .notUpdate();
        mainPage.inputSearchValue(value)
                .checkPromptSearch(value);
    }
}

