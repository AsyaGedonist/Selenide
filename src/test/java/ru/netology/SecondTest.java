package ru.netology;

import com.codeborne.selenide.Condition;
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

    public String searchByDate(int days, String pattern ){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldSend () {
        //Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");

        String planningDate = searchByDate(4, "dd.MM.yyyy");
        String weekDay = searchByDate(4, "d");
        String thisMonth = searchByDate(0, "M");
        String weekMonth = searchByDate(4, "M");

        $("[data-test-id=\"city\"] input.input__control").setValue("Ка");
        $$(".menu-item_type_block").find(exactText("Петропавловск-Камчатский")).click();

        $$(".icon-button_size_m").first().click();
        if (thisMonth.equals(weekMonth)) {
            $$(".calendar__day").find((exactText(weekDay))).click();
        } else {
            $("[data-step=\"1\"]").click();
            $$(".calendar__day").find((exactText(weekDay))).click();
        }

        $("[data-test-id=\"name\"] input.input__control").setValue("Андрей Андрей-Андрей");
        $("[data-test-id=\"phone\"] input.input__control").setValue("+79999999999");
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}