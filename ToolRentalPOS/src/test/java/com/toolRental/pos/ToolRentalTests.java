package com.toolRental.pos;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.services.RentalService;

public class ToolRentalTests {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    MathContext mathContext = new MathContext(3);

    @Test(expected = IllegalArgumentException.class)
    public void test1() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("09/03/15", formatter);
        service.checkout("JAKR", 5, 101, date);
    }

    @Test
    public void test2() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("07/02/20", formatter);
        RentalAgreement agreement = service.checkout("LADW", 3, 10, date);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal(1.99).round(new MathContext(3)), agreement.getDailyRentalCharge());
        assertEquals(new BigDecimal(5.97).round(new MathContext(3)), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal(0.60).round(new MathContext(2)), agreement.getDiscountAmount());
        assertEquals(new BigDecimal(5.37).round(new MathContext(3)), agreement.getFinalCharge());
    }

    @Test
    public void test3() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("07/02/15", formatter);
        RentalAgreement agreement = service.checkout("CHNS", 5, 25, date);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal(1.49).round(new MathContext(3)), agreement.getDailyRentalCharge());
        assertEquals(new BigDecimal(4.47).round(new MathContext(3)), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal(1.12).round(new MathContext(3)), agreement.getDiscountAmount());
        assertEquals(new BigDecimal(3.35).round(new MathContext(3)), agreement.getFinalCharge());
    }

    @Test
    public void test4() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("09/03/15", formatter);
        RentalAgreement agreement = service.checkout("JAKD", 6, 0, date);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal(2.99).round(new MathContext(3)), agreement.getDailyRentalCharge());
        assertEquals(new BigDecimal(8.97).round(new MathContext(3)), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal(0.00), agreement.getDiscountAmount());
        assertEquals(new BigDecimal(8.97).round(new MathContext(3)), agreement.getFinalCharge());
    }

    @Test
    public void test5() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("07/02/15", formatter);
        RentalAgreement agreement = service.checkout("JAKR", 9, 0, date);
        assertEquals(6, agreement.getChargeDays());
        assertEquals(new BigDecimal(2.99).round(new MathContext(3)), agreement.getDailyRentalCharge());
        assertEquals(new BigDecimal(17.9).round(new MathContext(3)), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal(0).round(new MathContext(3)), agreement.getDiscountAmount());
        assertEquals(new BigDecimal(17.9).round(new MathContext(3)), agreement.getFinalCharge());
    }

    @Test
    public void test6() {
        RentalService service = new RentalService();
        LocalDate date = LocalDate.parse("07/02/20", formatter);
        RentalAgreement agreement = service.checkout("JAKR", 4, 50, date);
        assertEquals(2, agreement.getChargeDays());
        assertEquals(new BigDecimal(2.99).round(new MathContext(3)), agreement.getDailyRentalCharge());
        assertEquals(new BigDecimal(5.98).round(new MathContext(3)), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal(2.99).round(new MathContext(3)), agreement.getDiscountAmount());
        assertEquals(new BigDecimal(2.99).round(new MathContext(3)), agreement.getFinalCharge());
    }
}
