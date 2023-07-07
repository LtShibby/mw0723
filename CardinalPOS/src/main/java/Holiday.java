import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;

public class Holiday {

    public static boolean isHoliday(LocalDate date) {
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4) {
            // Independence Day
            return true;
        } 
        // else if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
        //     // If Independence Day falls on Saturday, it is observed on Friday
        //     return true;
        // } else if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
        //     // If Independence Day falls on Sunday, it is observed on Monday
        //     return true;
        // } 
        else if (date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            // Labor Day (First Monday in September)
            return true;
        }
        return false;
    }
}
