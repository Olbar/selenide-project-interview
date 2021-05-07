package tests;

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

    PracticeFormPage openPracticeFormPage(){
        open("/automation-practice-form");
        return page(PracticeFormPage.class).checkOpen();
    }

    MainPage checkMaiPageIsOpened(){
        return page(MainPage.class).checkOpen();
    }

}
