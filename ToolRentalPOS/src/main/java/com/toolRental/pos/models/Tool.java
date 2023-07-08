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

    /**
     * Retrieves the tool code.
     *
     * @return the tool code
     */
    public String getToolCode() {
        return toolCode;
    }

    /**
     * Retrieves the tool type.
     *
     * @return the tool type
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * Retrieves the brand of the tool.
     *
     * @return the brand of the tool
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Retrieves the daily rental charge for the tool.
     *
     * @return the daily rental charge
     */
    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Checks if the tool has weekday charge.
     *
     * @return true if the tool has weekday charge, false otherwise
     */
    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Checks if the tool has weekend charge.
     *
     * @return true if the tool has weekend charge, false otherwise
     */
    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Checks if the tool has holiday charge.
     *
     * @return true if the tool has holiday charge, false otherwise
     */
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
