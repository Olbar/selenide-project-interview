package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Feature("MoveToPracticePage")
public class MoveToPracticeFormTest extends BaseTest {

    @Test(description = "Проверка возможности перехода с главнйо страницы на форму регистрации студента")
    public void moveToPracticeFormTest() {
        checkMaiPageIsOpened()
                .goToNavigationPage()
                .goToPracticeFormPage();
    }
}
