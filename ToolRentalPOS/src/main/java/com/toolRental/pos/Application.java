package com.cardinal.pos;
import java.time.LocalDate;

import com.cardinal.pos.models.RentalAgreement;
import com.cardinal.pos.services.RentalService;

public class Application {

    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        try {
            RentalAgreement rentalAgreement = rentalService.checkout("JAKR", 5, 50, LocalDate.of(2023, 9, 3));
            rentalAgreement.printRentalAgreement();

            rentalAgreement = rentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 07, 02));
            rentalAgreement.printRentalAgreement();

            rentalAgreement = rentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 07, 02));
            rentalAgreement.printRentalAgreement();

            rentalAgreement = rentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 03));
            rentalAgreement.printRentalAgreement();

            rentalAgreement = rentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 07, 02));
            rentalAgreement.printRentalAgreement();

            rentalAgreement = rentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 07, 20));
            rentalAgreement.printRentalAgreement();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
