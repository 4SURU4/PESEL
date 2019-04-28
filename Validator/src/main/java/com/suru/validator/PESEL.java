package com.suru.validator;

import com.google.common.base.Strings;
import com.suru.validator.exception.InvalidPeselException;
import lombok.extern.java.Log;
import java.util.Arrays;
import java.util.stream.IntStream;

@Log
public class PESEL {

    private Integer[] peselNumbers = new Integer[11];
    private int[] chceckumConsts = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};

    public PESEL(String pesel) throws InvalidPeselException {
        if (!isPESELValid(pesel)) {
            throw new InvalidPeselException();
        }
    }

    private boolean isPESELValid(String pesel) {
        return !Strings.isNullOrEmpty(pesel) && pesel.length() == 11 && checkSum(pesel) && checkDayAndMonth();
    }

    private boolean checkSum(String pesel) {
        peselNumbers = Arrays.stream(pesel.split("")).map(Integer::valueOf).toArray(Integer[]::new);
        int sum = IntStream.range(0, 10).map(i -> peselNumbers[i] * chceckumConsts[i]).sum() % 10;
        return sum == peselNumbers[10];
    }

    public String getSex() {
        return peselNumbers[9] % 2 == 0 ? "F" : "M";
    }

    public String getBirthDate() {
        int year = getYear();
        int month = getMonth();
        int day = getDay();

        return String.format("%02d", day) + "." + String.format("%02d", month) + "." + year;
    }

    private boolean checkDayAndMonth() {
        int year = getYear();
        int month = getMonth();
        int day = getDay();

        if (day > 0) {
            return (month > 0 && month < 13) &&
                    ((day < 29 && !leapYear(year)) ||
                            (day < 30 && leapYear(year)) ||
                            (day < 31 && Arrays.asList(4, 6, 9, 11).contains(month) ||
                                    (day < 32 && Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month))));
        }

        return false;
    }

    private boolean leapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public int getDay() {
        return 10 * peselNumbers[4] + peselNumbers[5];
    }

    public int getMonth() {
        int month = 10 * peselNumbers[2] + peselNumbers[3];

        if (month > 80) {
            month -= 80;
        } else if (month > 60) {
            month -= 60;
        } else if (month > 40) {
            month -= 40;
        } else if (month > 20) {
            month -= 20;
        }

        return month;
    }

    public int getYear() {
        int year = 10 * peselNumbers[0] + peselNumbers[1];
        int month = 10 * peselNumbers[2] + peselNumbers[3];
        if (month > 80) {
            year += 1800;
        } else if (month > 60) {
            year += 2200;
        } else if (month > 40) {
            year += 2100;
        } else if (month > 20) {
            year += 2000;
        } else
            year += 1900;

        return year;
    }

}
