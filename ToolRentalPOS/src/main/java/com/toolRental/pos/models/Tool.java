package com.toolRental.pos.models;

import java.math.BigDecimal;
import java.math.MathContext;

public class Tool {

    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;
    private final MathContext mathContext = new MathContext(3);

    public Tool(String toolCode, String toolType, String brand, BigDecimal dailyCharge,
                boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge.round(mathContext);
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    @Override
    public String toString() {
        return "Tool [toolCode=" + toolCode + ", toolType=" + toolType + ", brand=" + brand + ", dailyCharge="
                + dailyCharge + ", weekdayCharge=" + weekdayCharge + ", weekendCharge=" + weekendCharge
                + ", holidayCharge=" + holidayCharge + ", mathContext=" + mathContext + "]";
    }
}
