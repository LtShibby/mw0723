package com.toolRental.pos.services;
import java.time.LocalDate;


import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.models.Tool;
import com.toolRental.pos.repositories.ToolRepository;

public class RentalService {

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100.");
        }

        ToolRepository toolRepository = new ToolRepository();
        Tool tool = toolRepository.getTool(toolCode);

        return new RentalAgreement(tool, rentalDayCount, checkoutDate, discountPercent);
    }
}
