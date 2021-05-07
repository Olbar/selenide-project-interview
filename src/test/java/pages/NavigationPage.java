package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.Checked;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class NavigationPage implements Checked<NavigationPage> {

    @FindBy(css = ".accordion")
    private SelenideElement navigationBar;

    @FindBy(css = ".menu-list .btn span")
    private ElementsCollection menuList;

    @Step("Переход на страницу PracticeFormPage")
    public PracticeFormPage goToPracticeFormPage(){
        menuList.findBy(Condition.exactText("Practice Form")).click();
        return page(PracticeFormPage.class).checkOpen();
    }

    @Override
    public NavigationPage checkOpen() {
        navigationBar.shouldBe(Condition.visible);
        return this;
    }
}
