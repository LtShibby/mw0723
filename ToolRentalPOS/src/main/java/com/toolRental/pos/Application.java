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
        simulatePOS();
        generateDailyReceipts();
    }

    private static void generateDailyReceipts() {
        List<String> dailyReceipts = RentalService.getDailyReceiptsList();

        System.out.println("Printing Daily Receipts:"+"\n");

        int i = 0;
        for (String receipt : dailyReceipts) {
            System.out.println("Transaction " + ++i +":");
            System.out.println(receipt);
        }
    }

    private static void simulatePOS() {
        try {
            RentalAgreement rentalAgreement = RentalService.checkout("JAKR", 5, 50, LocalDate.of(2023, 9, 3));
            rentalAgreement = RentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 07, 02));
            rentalAgreement = RentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 07, 02));
            rentalAgreement = RentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 03));
            rentalAgreement = RentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 07, 02));
            rentalAgreement = RentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 07, 20));

            ToolRepository.addTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "Kraftool", new BigDecimal(8.49), true, true, true));

            rentalAgreement = RentalService.checkout("SLDG", 10, 6, LocalDate.of(2020, 07, 20));

            ToolRepository.updateTool("SLDG",
                    new Tool("SLDG", "Sledgehammer", "SoupTool", new BigDecimal(18.49), false, true, false));

            rentalAgreement = RentalService.checkout("SLDG", 10, 6, LocalDate.of(2020, 07, 20));

            ToolRepository.deleteTool("SLDG");
            rentalAgreement = RentalService.checkout("SLDG", 10, 6, LocalDate.of(2020, 07, 20));
            rentalAgreement.generateRentalAgreement();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }
}
