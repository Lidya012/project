package org.example.Util;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
public class UtilInput {
    private static Scanner scanner = new Scanner(System.in);

    private UtilInput() {
    }

    public static String getRequiredStringFromUser() {
        Optional<String> inputString;
        while ((inputString = inputStringFromUser()).isEmpty()) {
        }
        return inputString.get();
    }

    public static Optional<String> inputStringFromUser() {
        String result = inputFromUser();
        if (result.isEmpty()) {
            System.out.println("Вы ввели пустую строку");
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public static int getRequiredIntFromUser() {
        Optional<Integer> inputInt;
        while ((inputInt = getIntFromUser(null, null)).isEmpty()) {
        }
        return inputInt.get();
    }


    public static int getRequiredIntFromUser(int min, int max) {
        Optional<Integer> inputInt;
        while ((inputInt = getIntFromUser(min, max)).isEmpty() || checkOdz(min, max, inputInt.get())) {
        }
        return inputInt.get();
    }

    private static boolean checkOdz(int min, int max, int checkValue) {
        if (checkValue < min || checkValue > max) {
            System.out.println("Введеное значени не соответствует ОДЗ");
            return true;
        }
        return false;
    }


    public static LocalDate getRequiredDateFromUser() {
        LocalDate today = LocalDate.now();
        Optional<LocalDate> inputDate;

        while ((inputDate = inputDateFromUser()).isEmpty() || inputDate.get().isBefore(today)) {
            System.out.println("Дата должна быть не позже сегодняшней: " + today);
        }

        return inputDate.get();
    }


    private static Optional<LocalDate> inputDateFromUser() {
        System.out.print("Введите дату (в формате ГГГГ-ММ-ДД): ");

        String dateString = inputFromUser();

        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            return Optional.of(date);
        } catch (DateTimeParseException e) {
            System.out.println("Некорректный формат даты. Пожалуйста, используйте формат ГГГГ-ММ-ДД.");
            return Optional.empty();
        }
    }





public static Optional<Integer> getIntFromUser(Integer min, Integer max) {

        if (min == null || max == null) {
            System.out.print("Введите число: ");
        } else {
            System.out.printf("Введите число в промежутке от %s до %s: ", min, max);
        }

        try {
            return Optional.of(Integer.parseInt(inputFromUser()));
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число");
            return Optional.empty();
        }
    }

    private static String inputFromUser() {
        return scanner.nextLine().trim();
    }
}
