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
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }


    public String getToolCode() {
        return this.toolCode;
    }


    public String getToolType() {
        return this.toolType;
    }


    public String getBrand() {
        return this.brand;
    }


    public BigDecimal getDailyCharge() {
        return this.dailyCharge.round(mathContext);
    }


    public boolean isWeekdayCharge() {
        return this.weekdayCharge;
    }

    public boolean getWeekdayCharge() {
        return this.weekdayCharge;
    }


    public boolean isWeekendCharge() {
        return this.weekendCharge;
    }

    public boolean getWeekendCharge() {
        return this.weekendCharge;
    }


    public boolean isHolidayCharge() {
        return this.holidayCharge;
    }

    public boolean getHolidayCharge() {
        return this.holidayCharge;
    }

    
}
