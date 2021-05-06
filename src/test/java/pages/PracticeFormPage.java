package pages;

import com.codeborne.selenide.ElementsCollection;
import common.Checked;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class PracticeFormPage implements Checked<PracticeFormPage> {

    private final String photoFilePath = System.getProperty("user.dir") + "/src/test/resources/img/";

    @FindBy(css = ".main-header")
    private SelenideElement practiceFormTitle;

    @FindBy(css = "#userForm")
    private SelenideElement practiceFormBody;

    @FindBy(css = "#firstName")
    private SelenideElement firstName;

    @FindBy(css = "#lastName")
    private SelenideElement lastName;

    @FindBy(css = "#userEmail")
    private SelenideElement userEmail;

    @FindBy(css = "#userNumber")
    private SelenideElement userNumber;

    @FindBy(css = "#dateOfBirthInput")
    private SelenideElement dateOfBirthInput;

    @FindBy(css = "#subjectsInput")
    private SelenideElement subjectsInput;

    @FindBy(css = "#currentAddress")
    private SelenideElement currentAddress;

    @FindBy(css = "#state")
    private SelenideElement state;

    @FindBy(css = "#city")
    private SelenideElement city;

    @FindBy(css = "#uploadPicture")
    private SelenideElement uploadPicture;

    @FindBy(css = "#submit")
    private SelenideElement submitButton;

    @FindBy(css = "#userName-label")
    private SelenideElement userNameLabel;

    @FindBy(css = "#userEmail-label")
    private SelenideElement emailLabel;

    @FindBy(css = "#genterWrapper div:first-child")
    private SelenideElement genderLabel;

    @FindBy(css = "#userNumber-label")
    private SelenideElement phoneLabel;

    @FindBy(css = "#dateOfBirth-label")
    private SelenideElement birthDateLabel;

    @FindBy(css = "#subjects-label")
    private SelenideElement subjectsLabel;

    @FindBy(css = "#hobbiesWrapper .form-label")
    private SelenideElement hobbiesLabel;

    @FindBy(css = "#subjects-label")
    private ElementsCollection labels;

    @FindBy(css = "#currentAddress-label")
    private SelenideElement addressLabel;

    @FindBy(css = "#stateCity-label")
    private SelenideElement stateCityLabel;

    @FindBy(css = ".practice-form-wrapper h5")
    private SelenideElement practiceFormWrapper;

    @FindBy(css = ".react-datepicker__month-select")
    private SelenideElement monthPicker;

    @FindBy(css = ".react-datepicker__month-select option")
    private ElementsCollection months;

    @FindBy(css = ".react-datepicker__year-select")
    private SelenideElement yearPicker;

    @FindBy(css = ".react-datepicker__year-select option")
    private ElementsCollection years;

    @FindBy(css = ".react-datepicker__day:not(.react-datepicker__day--outside-month)")
    private ElementsCollection days;

    @Step("Проверка заголовка формы")
    public PracticeFormPage checkPracticeFormTitle() {
        practiceFormTitle.shouldHave(exactText("Practice Form"));
        return this;
    }

    @Step("Выбор половой принадлежности")
    public PracticeFormPage chooseGender(String gender) {
        $$("#genterWrapper .custom-radio label").find(exactText(gender)).click();
        return this;
    }

    @Step("Выбор хобби")
    public PracticeFormPage chooseHobbies(String... hobby) {
        for (int i = 0; i < hobby.length; i++) {
            $$("#hobbiesWrapper .custom-checkbox label").findBy(exactText(hobby[i])).click();
        }
        return this;
    }

    @Step("Заполение имени")
    public PracticeFormPage fillFirstName(String name) {
        firstName.shouldBe(enabled).setValue(name);
        return this;
    }

    @Step("Заполение фамилии")
    public PracticeFormPage fillLastName(String surname) {
        lastName.shouldBe(enabled).setValue(surname);
        return this;
    }

    @Step("Заполение электронной почты")
    public PracticeFormPage fillEmail(String email) {
        userEmail.shouldBe(enabled).setValue(email);
        return this;
    }

    @Step("Заполение номер телефона")
    public PracticeFormPage fillPhoneNumber(String phone) {
        userNumber.shouldBe(enabled).setValue(phone);
        return this;
    }

    @Step("Заполение даты рождения")
    public PracticeFormPage fillBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        monthPicker.shouldBe(enabled).click();
        months.findBy(exactText(month)).click();
        yearPicker.shouldBe(enabled).click();
        years.findBy(exactText(year)).scrollTo().click();
        days.findBy(exactText(day)).click();
        return this;
    }

    @Step("Заполение предметов")
    public PracticeFormPage fillSubject(String... subject) {
        for (int i = 0; i < subject.length; i++) {
            subjectsInput.shouldBe(enabled).setValue(subject[i]).pressEnter();
        }
        return this;
    }

    @Step("Заполение адреса")
    public PracticeFormPage fillAddress(String address) {
        currentAddress.shouldBe(enabled).setValue(address);
        return this;
    }

    @Step("Загрузка изображения")
    public PracticeFormPage uploadFile(String fileName) {
        uploadPicture.uploadFile(new File(photoFilePath + fileName));
        return this;
    }

    @Step("Выбор штата")
    public PracticeFormPage selectState(String state) {
        this.state.shouldBe(enabled).click();
        $$("[id*='select']").findBy(exactText(state)).click();
        return this;
    }

    @Step("Выбор города")
    public PracticeFormPage selectCity(String city) {
        this.city.shouldBe(enabled).click();
        $$("[id*='select']").findBy(exactText(city)).click();
        return this;
    }

    @Step("Проверка, что dropBox 'Cities' задизэйблен")
    public PracticeFormPage checkDropDownCitiesIsDisabled() {
        city.shouldHave(attribute("disabled", ""));
        return this;
    }

    @Step("Нажатие на кнопку подтверждения регистрации")
    public GratitudePage clickSubmit() {
        submitButton.click();
        return page(GratitudePage.class).checkOpen();
    }

    @Step("Срабатывание валидации при нажатии на кнопку подтверждения регистрации")
    public PracticeFormPage clickSubmitErr() {
        submitButton.click();
        return this;
    }

    @Step("Проверка лэйблов")
    public PracticeFormPage checkLabels() {
        userNameLabel.shouldHave(exactText("Name"));
        emailLabel.shouldHave(exactText("Email"));
        genderLabel.shouldHave(exactText("Gender"));
        phoneLabel.shouldHave(exactText("Mobile(10 Digits)"));
        birthDateLabel.shouldHave(exactText("Date of Birth"));
        subjectsLabel.shouldHave(exactText("Subjects"));
        hobbiesLabel.shouldHave(exactText("Hobbies"));
        labels.get(2).shouldHave(exactText("Picture"));
        addressLabel.shouldHave(exactText("Current Address"));
        stateCityLabel.shouldHave(exactText("State and City"));
        return this;
    }

    @Step("Проверка заголова формы регистрации")
    public PracticeFormPage checkRegFormTitle() {
        practiceFormWrapper.shouldHave(exactText("Student Registration Form"));
        return this;
    }

    @Step("Проверка плэйсхолдеров")
    public PracticeFormPage checkPlaceHolders() {
        firstName.shouldHave(attribute("placeholder","First Name"));
        lastName.shouldHave(attribute("placeholder","Last Name"));
        userEmail.shouldHave(attribute("placeholder","name@example.com"));
        userNumber.shouldHave(attribute("placeholder","Mobile Number"));
        currentAddress.shouldHave(attribute("placeholder","Current Address"));
        state.find(By.cssSelector("[class*=placeholder]")).shouldHave(exactText("Select State"));
        city.find(By.cssSelector("[class*=placeholder]")).shouldHave(exactText("Select City"));
        return this;
    }

    @Override
    public PracticeFormPage checkOpen() {
        practiceFormBody.shouldBe(visible);
        return this;
    }


}
