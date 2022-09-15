package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testTest {


    @Test
    void shouldSend () {
        //Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] input.input__control").setValue("Петропавловск-Камчатский");
        //$("[placeholder=\"Дата встречи\"]").clear();
        //$("[placeholder=\"Дата встречи\"]").setValue("22.09.2022");
        $("[data-test-id=\"name\"] input.input__control").setValue("Андрей Андрей-Андрей");
        $("[data-test-id=\"phone\"] input.input__control").setValue("+79301111111");
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}