import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        try {
            RentalAgreement rentalAgreement = rentalService.checkout("JAKR", 5, 50, LocalDate.of(2023, 9, 3));
            System.out.println(rentalAgreement);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
