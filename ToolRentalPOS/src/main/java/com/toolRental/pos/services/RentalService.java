package com.toolRental.pos.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.models.Tool;
import com.toolRental.pos.repositories.ToolRepository;

public class RentalService {
    private List<String> dailyReceiptTracker;

    public RentalService() {
        dailyReceiptTracker = new ArrayList<>();
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100.");
        }

        Tool tool = ToolRepository.getTool(toolCode);

        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDayCount, checkoutDate, discountPercent);
        dailyReceiptTracker.add(rentalAgreement.generateRentalAgreement());

        return rentalAgreement;
    }

    public List<String> getDailyReceiptsList() {
        return dailyReceiptTracker;
    }
}
