package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private final SelenideElement productList = $x("//android.view.View[@resource-id=\"productList\"]");

    @Step("Проверяем, что корзина не пустая")
    public CartPage checkCart(){
        productList.shouldBe(exist);
        return this;
    }
}
