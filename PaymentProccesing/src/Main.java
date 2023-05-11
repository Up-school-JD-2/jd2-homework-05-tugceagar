import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Enter payment amount:");
			String paymentAmount = sc.nextLine();
			if (!isInvalidPaymentAmount(paymentAmount)) {
				throw new InvalidPaymentAmount("Invalid payment amount. It should be positive integer.");
			}

			System.out.println("Enter card number:");
			String cardNumber = sc.nextLine();
			if (!isValidCardNumber(cardNumber)) {
				throw new InvalidCardNumberException(
						"Invalid card number. It should be 16 number, and does not have character");
			}
			System.out.println("Please enter expiration date( MM YY ) :");
			String expirationDate = sc.nextLine();
			String expirationDateYY = sc.nextLine();
			if (!isValidExpirationDate(expirationDate, expirationDateYY)) {
				throw new InvalidExpirationDate("Invalid expiration date.");
			}
			System.out.println("Please enter CCV:");
			String ccv = sc.nextLine();
			if (!isValidCCV(ccv)) {
				throw new InvalidCCV("Invalid CCV. It should be integer, and size should be 3");
			}
			pay(0);
		} catch (InvalidPaymentAmount | InvalidCardNumberException | InvalidExpirationDate | InvalidCCV e) {
			System.out.println(e.getMessage());
		}

	}

	private static boolean isInvalidPaymentAmount(String paymentAmount) {
		for (char c : paymentAmount.toCharArray()) {
			if (c == 44 || c == 46) 
				return false;
		}
		if (Integer.parseInt(paymentAmount) <= 0) {
			return false;
		}

		return true;
	}

	private static void pay(int i) {
		System.out.println("\nDirecting to the payment page...");
		try {
			int number = (int) (Math.random() * 100);
//			System.out.println(number);
			if (number > 75)
				throw new SystemNotWorkingException("System is not working");
			System.out.println("Payment successful");
		} catch (SystemNotWorkingException e) {
			System.out.println(e.getMessage());
			if (i != 1) {
				pay(1);
			}
		}

	}

	private static boolean isValidCCV(String ccv) {
		for (char c : ccv.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		if (ccv.toCharArray().length > 3) {
			return false;
		}
		return true;
	}

	private static boolean isValidExpirationDate(String expirationDate, String expirationDateYY) {
		for (char c : expirationDateYY.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		for (char c : expirationDate.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		if (Integer.parseInt(expirationDate) < 5 && Integer.parseInt(expirationDateYY) == 2023) {
			return false;
		}
		if (Integer.parseInt(expirationDate) >= 13) {
			return false;
		}

		if (Integer.parseInt(expirationDateYY) < 2023) {
			return false;
		}

		return true;
	}

	private static boolean isValidCardNumber(String cardNumber) {
		if (cardNumber.length() != 16) {
			return false;
		}
		for (char c : cardNumber.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

}
