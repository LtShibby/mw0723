package com.cardinal.pos.models;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        this.preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(this.chargeDays)).setScale(2, RoundingMode.HALF_UP);
        this.discountAmount = this.preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        this.finalCharge = this.preDiscountCharge.subtract(this.discountAmount);
    }

    public int calculateChargeDays(Tool tool, LocalDate checkoutDate, int rentalDays) {
        int chargeDays = 0;
        LocalDate dueDate = this.checkoutDate.plusDays(rentalDays);

        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            switch (date.getDayOfWeek()) {
                case SATURDAY:
                case SUNDAY:
                    if (tool.isWeekendCharge()) {
                        chargeDays++;
                    }
                    break;
                default:
                    if (tool.isWeekdayCharge()) {
                        if (Holiday.isHoliday(date))
                            if (tool.isHolidayCharge()) {
                                chargeDays++;
                                break;
                            } else
                                break;
                        chargeDays++;
                    }
            }
        }
        return chargeDays;
    }

    public void printRentalAgreement() {
        System.out.println();
        System.out.println("======================");
        System.out.println("Rental Agreement:");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
        System.out.println("Due date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
        System.out.println("Daily rental charge: " + tool.getDailyCharge());
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + discountAmount);
        System.out.println("Final charge: " + finalCharge);
        System.out.println("======================");
        System.out.println();
    }


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
        if(BigDecimal.ZERO.compareTo(this.discountAmount) == 0)
            return new BigDecimal(0);
        return this.discountAmount.round(mathContext);
    }


    public BigDecimal getFinalCharge() {
        return this.finalCharge.round(mathContext);
    }

    public BigDecimal getDailyRentalCharge() {
        return this.tool.getDailyCharge().round(mathContext);
    }


}
