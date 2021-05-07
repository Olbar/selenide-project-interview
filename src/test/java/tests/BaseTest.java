package tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class BaseTest {

    @BeforeSuite
    public void setup(){
        open("");
    }

    @Step("Открытие страницы PracticeFormPage по урлу")
    PracticeFormPage openPracticeFormPage(){
        open("/automation-practice-form");
        return page(PracticeFormPage.class).checkOpen();
    }

    @Step("Проверка, что открыта главная страница")
    MainPage checkMaiPageIsOpened(){
        return page(MainPage.class).checkOpen();
    }

}
