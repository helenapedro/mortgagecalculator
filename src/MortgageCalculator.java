import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte PERCENT = 100;
    final static byte MONTH_IN_YEAR = 12;
    public static void userInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = sc.nextInt();

        System.out.print("Annual Interest Rate: ");
        float annualInterest = sc.nextFloat(); // Interest Rate is a small number, so float is sufficient for that
        float monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        System.out.println("Annual Interest Rate: " + annualInterest);

        System.out.print("Period (Years): ");
        byte years = sc.nextByte(); // byte because the maximum number you want to support is 30
        int numOfPayments = years * MONTH_IN_YEAR;

        double mortgage = principal
                * monthlyInterest * Math.pow(1 + monthlyInterest, numOfPayments)
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("mortgage: " + mortgageFormatted);
    }
}
