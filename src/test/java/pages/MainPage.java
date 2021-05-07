package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.Checked;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class MainPage implements Checked<MainPage> {

    @FindBy(css = ".category-cards")
    private SelenideElement categories;

    @FindBy(css = ".card-body h5")
    private ElementsCollection cards;

    @Step("Переход на страницу NavigationPage")
    public NavigationPage goToNavigationPage(){
        cards.findBy(Condition.exactText("Forms")).click();
        return page(NavigationPage.class).checkOpen();
    }


    @Override
    public MainPage checkOpen() {
        categories.shouldBe(Condition.visible);
        return this;
    }
}
