package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrder {
    @BeforeEach
    public void openForm() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitRequest() {

        $("[data-test-id=name] input").setValue("Путятин Денис");
        $("[data-test-id=phone] input").setValue("+79119998877");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldCheckName() {


        $("[data-test-id=name] input").setValue("Putyatin Denis");
        $("[data-test-id=phone] input").setValue("+79119998877");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldWhenInvalidTel() {

        $("[data-test-id=name] input").setValue("Путятин Денис");
        $("[data-test-id=phone] input").setValue("+791199988");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldIfFieldsEmpty() {

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCheckIfNameEmpty() {

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79119998877");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCheckIfPhoneEmpty() {
        $("[data-test-id=name] input").setValue("Путятин Денис");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCheckIfMissClickAgreement() {
        $("[data-test-id=name] input").setValue("Путятин Денис");
        $("[data-test-id=phone] input").setValue("+79119998877");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}











