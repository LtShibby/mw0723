package com.toolRental.pos.models;
import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;

public class Holiday {

    public static boolean isHoliday(LocalDate date) {
        if (isIndependenceDay(date) || isLaborDay(date)) {
            return true;
        }
        return false;
    }
    
    private static boolean isIndependenceDay(LocalDate date) {
        return date.getMonth() == Month.JULY && date.getDayOfMonth() == 4;
    }
    
    private static boolean isLaborDay(LocalDate date) {
        if (date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }
        return false;
    }
}
