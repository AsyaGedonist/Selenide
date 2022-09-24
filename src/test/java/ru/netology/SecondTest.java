package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SecondTest {

    public String searchDay(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd"));
    }

    public String searchMonth(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("MM"));
    }

    @Test
    void shouldSend () {
        //Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        String today = searchDay(0);
        String weekDay = searchDay(7);
        String thisMonth = searchMonth(0);
        String weekMonth = searchMonth(7);

        $("[data-test-id=\"city\"] input.input__control").setValue("Ка");
        $(byText("Петропавловск-Камчатский")).click();

        $$(".icon-button_size_m").first().click();
        if (thisMonth.equals(weekMonth)) {
            $(byText(weekDay)).click();
        } else {
            $("[data-step=\"1\"]").click();
            $(byText(weekDay)).click();
        }

        $("[data-test-id=\"name\"] input.input__control").setValue("Андрей Андрей-Андрей");
        $("[data-test-id=\"phone\"] input.input__control").setValue("+79999999999");
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();

        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}