package com.toolRental.pos.models;

import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;

public class Holiday {

    /**
     * Checks if the given date is a holiday.
     *
     * @param date the date to check
     * @return true if the date is a holiday, false otherwise
     */
    public static boolean isHoliday(LocalDate date) {
        if (isIndependenceDay(date) || isLaborDay(date)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the given date is Independence Day (July 4th).
     *
     * @param date the date to check
     * @return true if the date is Independence Day, false otherwise
     */
    private static boolean isIndependenceDay(LocalDate date) {
        return date.getMonth() == Month.JULY && date.getDayOfMonth() == 4;
    }

    /**
     * Checks if the given date is Labor Day (first Monday in September).
     *
     * @param date the date to check
     * @return true if the date is Labor Day, false otherwise
     */
    private static boolean isLaborDay(LocalDate date) {
        if (date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }
        return false;
    }
}
