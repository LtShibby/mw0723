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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    MathContext mathContext = new MathContext(3);

    private RentalService rentalService;
    private LocalDate currentDate;

    @Before
    public void setup() {
        rentalService = new RentalService();
        currentDate = LocalDate.parse("07/02/20", formatter);
    }

    private void assertRentalAgreement(RentalAgreement agreement, int chargeDays, BigDecimal dailyRentalCharge,
            BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge) {
        assertEquals(chargeDays, agreement.getChargeDays());
        assertEquals(dailyRentalCharge.round(mathContext), agreement.getDailyRentalCharge());
        assertEquals(preDiscountCharge.round(mathContext), agreement.getPreDiscountCharge());
        assertEquals(discountAmount.round(mathContext), agreement.getDiscountAmount());
        assertEquals(finalCharge.round(mathContext), agreement.getFinalCharge());
    }

    @Test
    public void testInvalidCheckout_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("JAKR", 5, 101, currentDate);
        });
    }

    @Test
    public void testCheckoutLadder_ReturnsValidRentalAgreement() {
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, currentDate);
        assertRentalAgreement(agreement, 3, new BigDecimal("1.99"), new BigDecimal("5.97"),
                new BigDecimal("0.60"), new BigDecimal("5.37"));
    }

    @Test
    public void testCheckoutChainsaw_ReturnsValidRentalAgreement() {
        RentalAgreement agreement = rentalService.checkout("CHNS", 5, 25, currentDate);
        assertRentalAgreement(agreement, 3, new BigDecimal("1.49"), new BigDecimal("4.47"),
                new BigDecimal("1.12"), new BigDecimal("3.35"));
    }

    @Test
    public void testCheckoutJackhammerDeWalt_ReturnsValidRentalAgreement() {
        RentalAgreement agreement = rentalService.checkout("JAKD", 6, 0, currentDate);
        assertRentalAgreement(agreement, 4, new BigDecimal("2.99"), new BigDecimal("12.0"),
                BigDecimal.ZERO, new BigDecimal("12.0"));
    }

    @Test
    public void testCheckoutJackhammerRidgid_ReturnsValidRentalAgreement() {
        RentalAgreement agreement = rentalService.checkout("JAKR", 9, 0, currentDate);
        assertRentalAgreement(agreement, 6, new BigDecimal("2.99"), new BigDecimal("17.9"),
                BigDecimal.ZERO, new BigDecimal("17.9"));
    }

    @Test
    public void testCheckoutJackhammerRidgidWithDiscount_ReturnsValidRentalAgreement() {
        RentalAgreement agreement = rentalService.checkout("JAKR", 4, 50, currentDate);
        assertRentalAgreement(agreement, 2, new BigDecimal("2.99"), new BigDecimal("5.98"),
                new BigDecimal("2.99"), new BigDecimal("2.99"));
    }
}
