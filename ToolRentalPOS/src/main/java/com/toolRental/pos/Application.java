package com.toolRental.pos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.models.Tool;
import com.toolRental.pos.repositories.ToolRepository;
import com.toolRental.pos.services.RentalService;

public class Application {

    public static void main(String[] args) {

        RentalService rentalService = new RentalService();
        simulatePOS(rentalService);
        generateDailyReceipts(rentalService);
    }

    private static void generateDailyReceipts(RentalService rentalService) {
        List<String> dailyReceipts = rentalService.getDailyReceiptsList();

        System.out.println("Printing Daily Receipts:" + "\n");

        int i = 0;
        for (String receipt : dailyReceipts) {
            System.out.println("Transaction " + ++i + ":");
            System.out.println(receipt);
        }
    }

    private static void simulatePOS(RentalService rentalService) {
        try {
            RentalAgreement rentalAgreement;
            
            // Test 1
            rentalAgreement = rentalService.checkout("JAKR", 5, 50, LocalDate.of(2023, 9, 3));
            
            // Test 2
            rentalAgreement = rentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 07, 02));
            
            // Test 3
            rentalAgreement = rentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 07, 02));
            
            // Test 4
            rentalAgreement = rentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 03));
            
            // Test 5
            rentalAgreement = rentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 07, 02));
            
            // Test 6
            rentalAgreement = rentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 07, 02));

            ToolRepository.addTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "Kraftool", new BigDecimal(8.49), true, true, true));

            rentalAgreement = rentalService.checkout("SLDG", 10, 6, LocalDate.now());

            ToolRepository.updateTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "SoupTool", new BigDecimal(18.49), false, true, false));

            rentalAgreement = rentalService.checkout("SLDG", 10, 6,LocalDate.now());

            ToolRepository.deleteTool("SLDG");
            rentalAgreement = rentalService.checkout("SLDG", 10, 6, LocalDate.now());
            rentalAgreement.generateRentalAgreement();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }
}
