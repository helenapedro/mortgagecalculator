import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte PERCENT = 100;
    final static byte MONTH_IN_YEAR = 12;

    public static double readNumber(String prompt, double min, double max) {
        Scanner sc = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = sc.nextInt();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }
    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years
    ) {
        float monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        short numOfPayments = (short) (years * MONTH_IN_YEAR); // casting
        double mortgage = principal
                * monthlyInterest * Math.pow(1 + monthlyInterest, numOfPayments)
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1);
        return mortgage;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years
            ) {
        float monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        short numOfPayments = (short) (years * MONTH_IN_YEAR); // casting
        double balance = principal
                * (Math.pow(1 + monthlyInterest, numOfPayments)
                - Math.pow(1 + monthlyInterest, principal))
                / (Math.pow(1 + monthlyInterest, numOfPayments -1));
        return balance;
    }
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) readNumber("Period (Years) ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("mortgage: " + mortgageFormatted);

        System.out.println("REMAINING BALANCE");
        double balance = calculateBalance(principal, annualInterest, years);
        String balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
        System.out.println("loanBalance: " + balanceFormatted);
    }
}
