package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private DataGenerator() {

    }

    public static String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities= new String[]{"Пермь","Москва","Санкт-Петербург","Челябинск",
                "Екатеринбург","Казань"
        };
        return cities [new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {

        return faker.name().fullName();

    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {

            return new UserInfo(generateCity(),generateName(locale),generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phoneNumber;
    }
}