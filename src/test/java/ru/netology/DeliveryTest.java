
package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

    class DeliveryTest {

        @BeforeEach
        void setup() {
            open("http://localhost:9999");
        }

        @Test
        @DisplayName("Should successful plan and replan meeting")
        void shouldSuccessfulPlanAndReplanMeeting() {
            var validUser = DataGenerator.Registration.generateUser("ru");
            var daysToAddForFirstMeeting = 4;
            var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
            var daysToAddForSecondMeeting = 7;
            var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

            $("[data-test-id='city'] input").setValue(validUser.getCity());
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='date'] input").setValue(firstMeetingDate);
            $("[data-test-id='name'] input").setValue(validUser.getName());
            $("[data-test-id='phone'] input").setValue(validUser.getPhoneNumber());
            $("[data-test-id='agreement']").click();
            $(byText("Запланировать")).click();
            $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(14));
            $("[data-test-id='success-notification'] .notification__content")
                    .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(14))
                    .shouldBe(visible);
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='date'] input").setValue(secondMeetingDate);
            $(byText("Запланировать")).click();
            $("[data-test-id='replan-notification'] .notification__title")
                    .shouldHave(exactText("Необходимо подтверждение"))
                    .shouldBe(visible);
            $("[data-test-id='replan-notification'] .notification__content")
                    .shouldHave(exactText("У вас уже запланирована встреча на другую дату. Перепланировать? "),Duration.ofSeconds(12))
                    .shouldBe(visible);
            $("[data-test-id='replan-notification'] button").click();
            $("[data-test-id='success-notification'] .notification__content")
                    .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(14))
                    .shouldBe(visible);

        }
        }



