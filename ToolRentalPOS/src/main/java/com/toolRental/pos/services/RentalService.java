package com.toolRental.pos.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.models.Tool;
import com.toolRental.pos.repositories.ToolRepository;

public class RentalService {

    private static final Logger logger = Logger.getLogger(RentalService.class.getName());

    private static List<String> dailyReceiptTracker = new ArrayList<>();

    /**
     * Checks out a tool for rental and generates a rental agreement.
     *
     * @param toolCode         the code of the tool to be rented
     * @param rentalDayCount   the number of days for the rental
     * @param discountPercent  the discount percentage to be applied to the rental
     * @param checkoutDate     the date of checkout
     * @return the rental agreement
     * @throws IllegalArgumentException if the input parameters are invalid
     */
    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        validateRentalInput(toolCode, rentalDayCount, discountPercent);

        Tool tool;
        try {
            tool = ToolRepository.getTool(toolCode);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Invalid tool code: " + toolCode;
            logger.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage, e);
        }

        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDayCount, checkoutDate, discountPercent);

        dailyReceiptTracker.add(rentalAgreement.generateRentalAgreement());
        logger.log(Level.INFO, "Rental agreement generated: " + rentalAgreement);

        return rentalAgreement;
    }

    /**
     * Retrieves the list of daily receipts.
     *
     * @return the list of daily receipts
     */
    public List<String> getDailyReceiptsList() {
        return dailyReceiptTracker;
    }

    private static void validateRentalInput(String toolCode, int rentalDayCount, int discountPercent) {
        validateToolCode(toolCode);
        validateRentalDayCount(rentalDayCount);
        validateDiscountPercent(discountPercent);
    }

    private static void validateToolCode(String toolCode) {
        if (toolCode == null || toolCode.isEmpty()) {
            String errorMessage = "Invalid tool code: " + toolCode;
            logger.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateRentalDayCount(int rentalDayCount) {
        if (rentalDayCount < 1) {
            String errorMessage = "Invalid rental day count: " + rentalDayCount;
            logger.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            String errorMessage = "Invalid discount percent: " + discountPercent;
            logger.log(Level.SEVERE, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
