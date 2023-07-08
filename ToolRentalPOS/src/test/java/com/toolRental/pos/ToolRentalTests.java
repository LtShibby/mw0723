package com.toolRental.pos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.services.RentalService;

public class ToolRentalTests {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
    private MathContext mathContext = new MathContext(3);

    private RentalService rentalService;
    private LocalDate currentDate;

    @Before
    public void setup() {
        rentalService = new RentalService();
        currentDate = LocalDate.now();
    }

    private void assertRentalAgreement(RentalAgreement agreement, int chargeDays, BigDecimal dailyRentalCharge,
            BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge) {
        assertEquals("Incorrect charge days", chargeDays, agreement.getChargeDays());
        assertEquals("Incorrect daily rental charge", dailyRentalCharge.round(mathContext), agreement.getDailyRentalCharge());
        assertEquals("Incorrect pre-discount charge", preDiscountCharge.round(mathContext), agreement.getPreDiscountCharge());
        assertEquals("Incorrect discount amount", discountAmount.round(mathContext), agreement.getDiscountAmount());
        assertEquals("Incorrect final charge", finalCharge.round(mathContext), agreement.getFinalCharge());
    }

    @Test
    public void testInvalidCheckout_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("JAKR", 5, 101, currentDate);
        });
    }

    @Test
    public void testCheckoutLadder_ReturnsValidRentalAgreement() {
        LocalDate checkoutDate = LocalDate.parse("7/2/20", formatter);
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, checkoutDate);
        assertRentalAgreement(agreement, 3, new BigDecimal("1.99"), new BigDecimal("5.97"),
                new BigDecimal("0.60"), new BigDecimal("5.37"));
    }

    @Test
    public void testCheckoutChainsaw_ReturnsValidRentalAgreement() {
        LocalDate checkoutDate = LocalDate.parse("7/2/15", formatter);
        RentalAgreement agreement = rentalService.checkout("CHNS", 5, 25, checkoutDate);
        assertRentalAgreement(agreement, 3, new BigDecimal("1.49"), new BigDecimal("4.47"),
                new BigDecimal("1.12"), new BigDecimal("3.35"));
    }

    @Test
    public void testCheckoutJackhammerDeWalt_ReturnsValidRentalAgreement() {
        LocalDate checkoutDate = LocalDate.parse("9/3/15", formatter);
        RentalAgreement agreement = rentalService.checkout("JAKD", 6, 0, checkoutDate);
        assertRentalAgreement(agreement, 3, new BigDecimal("2.99"), new BigDecimal("8.97"),
                BigDecimal.ZERO, new BigDecimal("8.97"));
    }

    @Test
    public void testCheckoutJackhammerRidgid_ReturnsValidRentalAgreement() {
        LocalDate checkoutDate = LocalDate.parse("9/3/15", formatter);
        RentalAgreement agreement = rentalService.checkout("JAKR", 9, 0, checkoutDate);
        assertRentalAgreement(agreement, 5, new BigDecimal("2.99"), new BigDecimal("14.95"),
                BigDecimal.ZERO, new BigDecimal("14.95"));
    }

    @Test
    public void testCheckoutJackhammerRidgidWithDiscount_ReturnsValidRentalAgreement() {
        LocalDate checkoutDate = LocalDate.parse("7/2/20", formatter);
        RentalAgreement agreement = rentalService.checkout("JAKR", 4, 50, checkoutDate);
        assertRentalAgreement(agreement, 2, new BigDecimal("2.99"), new BigDecimal("5.98"),
                new BigDecimal("2.99"), new BigDecimal("2.99"));
    }
}
