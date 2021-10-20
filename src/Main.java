import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static double enteredNum;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) {

		// Делаем возможность вводить дробное число с '.', ','
		boolean flag = false;
		while (!flag) {
			try {
				sc.useLocale(Locale.ENGLISH);
				enteredNum = sc.nextDouble();
				flag = true;
			}catch (InputMismatchException exception) {
				try {
					sc.useLocale(Locale.FRANCE);
					enteredNum = sc.nextDouble();
					flag = true;
				}catch (Exception e) {
					System.out.println("Число введено не корректно");
				}
			}
		}

		// Проверяю является ли число дробным, и перевожу число в двоичный код.
		double fractionalOfNum = enteredNum - (int) enteredNum;

		if (fractionalOfNum == 0) {
			if (enteredNum > 0) {
				Main.convertNaturalNumberToBinaryCode(enteredNum);
			} else
				Main.convertNegativeNumberToBinaryCode(enteredNum);
		} else
			Main.convertFractionalNumberToBinaryCode(enteredNum);

	}

	// Метод переводит число в бинарный вид столбиком, для других методов.
	private static void convertNumberToBinaryCodeByColumn(double num) {
		int answerOfDivision = 0;
		StringBuilder BinaryNum = new StringBuilder();
		int integerNum = (int) num;

		// Делю число столбиком.
		if (num != 0) {
			while (integerNum != 1) {
				answerOfDivision = integerNum / 2;
				BinaryNum.append(integerNum - (answerOfDivision * 2));
				integerNum = answerOfDivision;
			}
		}

		BinaryNum.append(answerOfDivision);

		result.append(BinaryNum);
		result.reverse();
	}

	// Метод переводит натуральное число в бинарный код.
	public static void convertNaturalNumberToBinaryCode(double enteredNum) {
		convertNumberToBinaryCodeByColumn(enteredNum);

		outputInDifferentVariablesForNaturalNumber();
	}

	// Вывожу число во всех возможных типов данных.
	private static void outputInDifferentVariablesForNaturalNumber() {

		if (enteredNum <= 127) {
			int amountZeros = 8 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			System.out.println("byte:  " + result);
		}
		if (enteredNum <= 32677) {
			int amountZeros = 16 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			System.out.println("short: " + result);
		}
		if (enteredNum <= Math.pow(2, 31) - 1) {
			int amountZeros = 32 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			System.out.println("int:   " + result);
		}
		if (enteredNum <= Math.pow(2, 63) - 1) {
			int amountZeros = 64 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			System.out.println("long:  " + result);
		}
	}

	// Метод переводит отрицательное число в бинарный код.
	public static void convertNegativeNumberToBinaryCode(double enteredNum) {
		enteredNum *= -1;

		convertNumberToBinaryCodeByColumn(enteredNum);

		outputInDifferentVariablesForNegativeNumber(enteredNum);

	}

	// Вывожу число во всех возможных типов данных для отрицательного числа.
	private static void outputInDifferentVariablesForNegativeNumber(double enteredNum) {

		if (-128 <= enteredNum && enteredNum <= 128) {
			int amountZeros = 8 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			changeNumberSign(result);
			System.out.println("byte:  " + result);
		}
		if (-32678 <= enteredNum && enteredNum <= 32677) {
			result.delete(0, result.length());
			result.append(result);

			int amountZeros = 16 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			changeNumberSign(result);
			System.out.println("short: " + result);
		}
		if (-Math.pow(2, 31) <= enteredNum && enteredNum <= Math.pow(2, 31) - 1) {
			result.delete(0, result.length());
			result.append(result);

			int amountZeros = 32 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			changeNumberSign(result);
			System.out.println("int:   " + result);
		}
		if (-Math.pow(2, 63) <= enteredNum && enteredNum <= Math.pow(2, 63) - 1) {
			result.delete(0, result.length());
			result.append(result);

			int amountZeros = 64 - result.length();
			for (int i = 0; i < amountZeros; i++) {
				result.insert(0, '0');
			}
			changeNumberSign(result);
			System.out.println("long:  " + result);
		}
	}

	private static void changeNumberSign(StringBuilder result) {
		exchange0and1(result);
		addOne(result);
	}


	// Меняю нули и единицы друг на друга.
	private static void exchange0and1(StringBuilder result) {

		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == '0')
				result.setCharAt(i, '1');

			else if (result.charAt(i) == '1')
				result.setCharAt(i, '0');
		}
	}

	private static void addOne(StringBuilder result) {
		// Добавляю единицу в двоичном коде.
		boolean isOccupationDischarge  = false;

		for (int i = result.length() - 1; i > 0; i--) {
			if (result.charAt(i) == '0') {
				result.setCharAt(i, '1');
				isOccupationDischarge = true;
				break;
			} else if (result.charAt(i) == '1')
				result.setCharAt(i, '0');
		}

		if (!isOccupationDischarge)
			result.insert(0, '1');
	}


	// Метод переводит дробное число в бинарный код.
	public static void convertFractionalNumberToBinaryCode(double variableResults) {

		int integerOfNum = (int) variableResults; // Целая часть
		double fractionalOfNum = variableResults - integerOfNum; // Дробная часть

		// Перевожу целую часть числа.
		convertNumberToBinaryCodeByColumn(integerOfNum);

		result.append(',');

		// Перевожу дробную часть числа.
		for (int i = 0; i < 8; i++) {
			fractionalOfNum = fractionalOfNum * 2;

			result.append((int) fractionalOfNum); // Добавляю к ответу целую часть от числа.

			fractionalOfNum = fractionalOfNum - (int) fractionalOfNum; // Беру дробную часть от числа.
		}
		System.out.println(result);
	}
}
