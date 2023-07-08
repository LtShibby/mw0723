package com.toolRental.pos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.toolRental.pos.models.RentalAgreement;
import com.toolRental.pos.models.Tool;
import com.toolRental.pos.repositories.ToolRepository;
import com.toolRental.pos.services.RentalService;

/**
 * The main application class.
 */
public class Application {

    /**
     * The entry point of the application.
     *
     * @param args the command line arguments - none required for this program.
     */
    public static void main(String[] args) {

        RentalService rentalService = new RentalService();
        simulatePOS(rentalService);
        generateDailyReceipts(rentalService);
    }

    /**
     * Generates and prints the List of daily receipts from the rental service.
     *
     * @param rentalService the rental service
     */
    private static void generateDailyReceipts(RentalService rentalService) {
        List<String> dailyReceipts = rentalService.getDailyReceiptsList();

        System.out.println("Printing Daily Receipts:" + "\n");

        int i = 0;
        for (String receipt : dailyReceipts) {
            System.out.println("Transaction " + ++i + ":");
            System.out.println(receipt);
        }
    }

    /**
     * Simulates the Point of Sale (POS) system by checking out tools and performing operations.
     * 
     * Runs all six tests from the demonstration document.
     *
     * @param rentalService the rental service
     */
    private static void simulatePOS(RentalService rentalService) {
        try {
            RentalAgreement rentalAgreement;
            
            // Test 1
            rentalAgreement = rentalService.checkout("JAKR", 5, 50, LocalDate.of(2023, 9, 3));
            
            // Test 2
            rentalAgreement = rentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
            
            // Test 3
            rentalAgreement = rentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
            
            // Test 4
            rentalAgreement = rentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
            
            // Test 5
            rentalAgreement = rentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
            
            // Test 6
            rentalAgreement = rentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));

            ToolRepository.addTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "Kraftool", new BigDecimal(8.49), true, true, true));

            rentalAgreement = rentalService.checkout("SLDG", 10, 6, LocalDate.now());

            ToolRepository.updateTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "SoupTool", new BigDecimal(18.49), false, true, false));

            rentalAgreement = rentalService.checkout("SLDG", 10, 6, LocalDate.now());

            ToolRepository.deleteTool("SLDG");
            
            // Next checkout should throw in logger -> "SEVERE: Invalid tool code: SLDG" 
            rentalAgreement = rentalService.checkout("SLDG", 10, 6, LocalDate.now());

            // Print the receipts from all the valid checkouts today. 
            rentalAgreement.generateRentalAgreement();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }
}
