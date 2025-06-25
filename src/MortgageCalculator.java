import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte PERCENT = 100;
    final static byte MONTH_IN_YEAR = 12;
    private static byte years = 0;
    private static  float annualInterest = 0;
    private static int principal = 0;

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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("Principal ($1k - $1M): ");
            principal = sc.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
                break;
            }
            System.out.println("Enter a number between 1,000 and 1,000,000.");
        }

        while(true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = sc.nextFloat(); // Interest Rate is a small number, so float is sufficient for that
            if (annualInterest >= 1 && annualInterest <= 30) {
                break;
            }
            System.out.println("Enter a value greater than 0 and less or equal to 30");
        }

        while (true) {
            System.out.print("Period (Years): ");
            years = sc.nextByte(); // byte because the maximum number you want to support is 30
            if (years >= 1 && years <= 30) {
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("mortgage: " + mortgageFormatted);
    }
}
