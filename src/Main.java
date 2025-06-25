public class Main {
    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("    MORTGAGE CALCULATOR");
        System.out.println("===========================\n");

        int principal = (int) MortgageCalculator.readNumber("Principal ($1K - $1M): ", 1_000, 1_000_000);
        float annualInterest = (float) MortgageCalculator.readNumber("Annual Interest Rate (1% - 30%): ", 1, 30);
        byte years = (byte) MortgageCalculator.readNumber("Period (Years, 1 - 30): ", 1, 30);

        MortgageCalculator.printMortgage(principal, annualInterest, years);
        MortgageCalculator.printPaymentSchedule(principal, annualInterest, years);
    }
}
