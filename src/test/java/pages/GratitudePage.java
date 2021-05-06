package pages;


import common.Checked;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class GratitudePage implements Checked<GratitudePage> {

    @FindBy(css = ".modal-header .modal-title")
    private SelenideElement gratitudePageTitle;

    @FindBy(css = "#closeLargeModal")
    private SelenideElement closeBtn;

    @FindBy(css = ".modal-body tbody tr td")
    private ElementsCollection collection;

    public PracticeFormPage clickClose() {
        closeBtn.click();
        return page(PracticeFormPage.class).checkOpen();
    }


    public GratitudePage checkParams(HashMap<String, String> params) {
        for (Map.Entry param:params.entrySet()) {
            collection.findBy(exactText(param.getKey().toString()))
                    .parent().find(By.cssSelector("td:last-child")).shouldHave(exactText(param.getValue().toString()));
        }
        return this;
    }

    @Override
    public GratitudePage checkOpen() {
        gratitudePageTitle.shouldBe(Condition.visible);
        return this;
    }
}
