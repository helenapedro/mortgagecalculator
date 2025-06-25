import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte PERCENT = 100;
    final static byte MONTH_IN_YEAR = 12;

    public static double readNumber(String prompt, double min, double max) {
        Scanner sc = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextDouble(); // Corrigido de nextInt() para nextDouble()
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(int principal, float annualInterest, byte years) {
        float monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        short numOfPayments = (short) (years * MONTH_IN_YEAR);

        return principal
                * monthlyInterest * Math.pow(1 + monthlyInterest, numOfPayments)
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1);
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade
    ) {
        float monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        short totalPayments = (short) (years * MONTH_IN_YEAR);

        return principal
                * (Math.pow(1 + monthlyInterest, totalPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, totalPayments) - 1);
    }

    public static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String formatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("\n===================");
        System.out.println("   MORTGAGE RESULT");
        System.out.println("===================");
        System.out.println("Monthly Payments: " + formatted);
    }

    public static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println("\n===================");
        System.out.println("  PAYMENT SCHEDULE");
        System.out.println("===================");

        for (short month = 1; month <= years * MONTH_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println("Month " + month + ": " + NumberFormat.getCurrencyInstance().format(balance));
        }
    }
}
