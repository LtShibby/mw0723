package com.toolRental.pos.models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {

    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;
    private final MathContext mathContext = new MathContext(3);

    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.discountPercent = discountPercent;
        this.chargeDays = calculateChargeDays(tool, checkoutDate, rentalDays);
        this.preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(this.chargeDays))
                .setScale(2, RoundingMode.HALF_UP);
        this.discountAmount = this.preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        this.finalCharge = this.preDiscountCharge.subtract(this.discountAmount);
    }

    /**
     * Calculates the number of chargeable days for the rental based on the tool's charging rules and the rental duration.
     *
     * @param tool          the tool to be rented
     * @param checkoutDate  the date of checkout
     * @param rentalDays    the number of rental days
     * @return the number of chargeable days
     */
    private int calculateChargeDays(Tool tool, LocalDate checkoutDate, int rentalDays) {
        int chargeDays = 0;
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);

        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, tool)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    /**
     * Checks if a specific date is a chargeable day based on the tool's charging rules.
     *
     * @param date  the date to check
     * @param tool  the tool to be rented
     * @return true if the date is a chargeable day, false otherwise
     */
    private boolean isChargeableDay(LocalDate date, Tool tool) {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return tool.isWeekendCharge();
        } else {
            return tool.isWeekdayCharge() && !Holiday.isHoliday(date);
        }
    }

    /**
     * Generates a formatted string representation of the rental agreement.
     *
     * @return the formatted rental agreement string
     */
    public String generateRentalAgreement() {
        StringBuilder sb = new StringBuilder();
        sb.append("======================");
        sb.append("\n");
        sb.append("Rental Agreement:");
        sb.append("\n");
        sb.append("Tool code: ").append(tool.getToolCode());
        sb.append("\n");
        sb.append("Tool type: ").append(tool.getToolType());
        sb.append("\n");
        sb.append("Tool brand: ").append(tool.getBrand());
        sb.append("\n");
        sb.append("Rental days: ").append(rentalDays);
        sb.append("\n");
        sb.append("Checkout date: ").append(checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
        sb.append("\n");
        sb.append("Due date: ").append(dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
        sb.append("\n");
        sb.append("Daily rental charge: ").append(tool.getDailyCharge());
        sb.append("\n");
        sb.append("Charge days: ").append(chargeDays);
        sb.append("\n");
        sb.append("Pre-discount charge: ").append(preDiscountCharge);
        sb.append("\n");
        sb.append("Discount percent: ").append(discountPercent).append("%");
        sb.append("\n");
        sb.append("Discount amount: ").append(discountAmount);
        sb.append("\n");
        sb.append("Final charge: ").append(finalCharge);
        sb.append("\n");
        sb.append("======================");
        sb.append("\n");
        return sb.toString();
    }

    // Getters for the rental agreement attributes

    public Tool getTool() {
        return this.tool;
    }

    public int getRentalDays() {
        return this.rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return this.preDiscountCharge.round(mathContext);
    }

    public int getDiscountPercent() {
        return this.discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        if (BigDecimal.ZERO.compareTo(this.discountAmount) == 0) {
            return BigDecimal.ZERO;
        }
        return this.discountAmount.round(mathContext);
    }

    public BigDecimal getFinalCharge() {
        return this.finalCharge.round(mathContext);
    }

    public BigDecimal getDailyRentalCharge() {
        return this.tool.getDailyCharge().round(mathContext);
    }

    @Override
    public String toString() {
        return "RentalAgreement [tool=" + tool + ", rentalDays=" + rentalDays + ", checkoutDate=" + checkoutDate
                + ", dueDate=" + dueDate + ", chargeDays=" + chargeDays + ", preDiscountCharge=" + preDiscountCharge
                + ", discountPercent=" + discountPercent + ", discountAmount=" + discountAmount + ", finalCharge="
                + finalCharge + ", mathContext=" + mathContext + "]";
    }

}
