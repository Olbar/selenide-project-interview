package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static common.Constants.*;
import static common.Gender.MALE;
import static common.Hobby.*;
import static common.States.*;
import static common.Cities.*;

@Feature("PracticePage")
public class PracticeFormTest extends BaseTest {

    private PracticeFormPage practiceFormPage;
    private LocalDate date = LocalDate.now().minusYears(18);
    private Locale locale = new Locale("en", "EN");

    private  DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", locale);
    private  String month = date.format(monthFormatter);
    private  DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
    private  String day = date.format(dayFormatter);
    private  DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
    private  String year = date.format(yearFormatter);

    private DateTimeFormatter mapFormatter =
            DateTimeFormatter
                    .ofPattern("dd MMM,yyyy", locale);

    private String firstName = "Vasya";
    private String lastName = "Ivanov";
    private String email = "vasya@gmail.com";
    private String gender = MALE.getText();
    private String mobilePhone = "9213335566";
    private String birthDateResult = mapFormatter.format(date);
    private List<String> subjects = new ArrayList<>();
    private List<String> hobby = new ArrayList<>();

    @BeforeMethod
    public PracticeFormPage openPracticeForm() {
        practiceFormPage = openPracticeFormPage();
        return practiceFormPage;
    }

    @Test(description = "Заполнение всех полей и проверка их заполненности")
    public void allFieldsFilledTest() {
        subjects.add(MATHS);
        subjects.add(CHEMISTRY);

        hobby.add(SPORTS.getText());
        hobby.add(READING.getText());
        hobby.add(MUSIC.getText());

        HashMap<String, String> params = new HashMap<>();
        params.put(STUDENT_NAME_LABEL, firstName + " " + lastName);
        params.put(STUDENT_EMAIL_LABEL, email);
        params.put(GENDER, gender);
        params.put(MOBILE, mobilePhone);
        params.put(BIRTH_DATE, birthDateResult);
        params.put(SUBJECTS, subjects.get(0) + ", " + subjects.get(1));
        params.put(HOBBIES, hobby.get(0) + ", " + hobby.get(1) + ", " + hobby.get(2));
        String fileName = "Generalokot.jpg";
        params.put(PICTURE, fileName);
        String address = "Lenina street 8";
        params.put(ADDRESS, address);
        params.put(STATE_AND_CITY, NCR.getName() + " " + DELHI.getName());

        practiceFormPage
                .checkRegFormTitle()
                .checkLabels()
                .checkPlaceHolders()
                .checkPracticeFormTitle()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .chooseGender(gender)
                .fillPhoneNumber(mobilePhone)
                .fillBirthDate(day,month,year)
                .fillSubject(subjects.get(0), subjects.get(1))
                .uploadFile(fileName)
                .chooseHobbies(hobby.get(0), hobby.get(1), hobby.get(2))
                .fillAddress(address)
                .selectState(NCR.getName())
                .selectCity(DELHI.getName())
                .clickSubmit()
                .checkParams(params)
                .clickClose();
    }

    @Test(description = "Заполнение только обязательных полей")
    public void requiredFieldsFilledTest() {
        HashMap<String, String> params = new HashMap<>();
        params.put(STUDENT_NAME_LABEL, firstName + " " + lastName);
        params.put(GENDER, gender);
        params.put(MOBILE, mobilePhone);

        practiceFormPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .chooseGender(gender)
                .fillPhoneNumber(mobilePhone)
                .clickSubmit()
                .checkParams(params)
                .clickClose();
    }

    @Test(description = "Проверка, что срабатывает валидация по кнопке 'submit',если не заполнены обязательные поля " +
            "и пользователь остается на том же PageObject")
    public void validationRequiredFieldsTest() {
        practiceFormPage
                .clickSubmitErr();
    }


    @Test(description = "Проверка работы валидации поля email")
    public void emailValidationTest() {
        practiceFormPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .chooseGender(gender)
                .fillPhoneNumber(mobilePhone)
                .fillEmail("dsfdsfs")
                .clickSubmitErr()
                .fillEmail("dsfdsfs@dsfsfsdf")
                .clickSubmitErr()
                .fillEmail("апр@поро.ру")
                .clickSubmitErr();
    }

    @Test(description = "Проверка работы валидации поля mobile")
    public void mobilePhoneValidationTest() {
        practiceFormPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .chooseGender(gender)
                .fillEmail(email)
                .fillPhoneNumber("012345678A")
                .clickSubmitErr()
                .fillPhoneNumber("012345678")
                .clickSubmitErr()
                .fillPhoneNumber("000*000000")
                .clickSubmitErr();
    }

    @Test(description = "Проверка, что dropbox 'Cities' задизэйблен")
    public void citiesDropDownIsDisabledTest() {
        practiceFormPage
                .checkDropDownCitiesIsDisabled();
    }
}
