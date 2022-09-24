package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FirstTest {

    public String searchDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldSend () {
        //Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        String date = searchDate(10);

        $("[data-test-id=\"city\"] input.input__control").setValue("Петропавловск-Камчатский");
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(date);
        $("[data-test-id=\"name\"] input.input__control").setValue("Андрей Андрей-Андрей");
        $("[data-test-id=\"phone\"] input.input__control").setValue("+79301111111");
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}