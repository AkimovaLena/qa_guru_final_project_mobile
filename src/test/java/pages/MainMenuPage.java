package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.className;

public class MainMenuPage {
    private final SelenideElement buttonCatalog = $x("//android.view.View[@content-desc=\"Каталог\"]"),
    buttonCart = $x("//android.view.View[@resource-id=\"CartNavBar\"]");

    @Step ("Открываем меню каталога")
    public MainMenuPage openCatalog(){
        buttonCatalog.click();
        return this;
    }

    @Step ("Открываем корзину")
    public MainMenuPage openCart(){
        buttonCart.click();
        return this;
    }

    @Step ("Проверяем, что колличество товаров в корзине равно {0}")
    public MainMenuPage checkCountInCart(String value){
//        $x("//android.view.View[@resource-id=\"CartNavBar\"]").$(className("android.widget.TextView")).shouldHave(text(value));
        buttonCart.$(className("android.widget.TextView")).shouldHave(text(value));
        return this;
    }
}
