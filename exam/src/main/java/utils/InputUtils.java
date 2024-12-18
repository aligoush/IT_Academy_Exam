package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static String readInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int readIntInput(String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static double readDoubleInput(String message) {
        System.out.println(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static <E extends Enum<E>> E readEnum(String message, Class<E> enumType) {
        E[] enumValues = enumType.getEnumConstants();
        E selectedValue = null;
        boolean dataOk = false;
        while (!dataOk) {
            try {
                System.out.println(message);
                for (int i = 0; i < enumValues.length; i++) {
                    System.out.println(i + 1 + "." + enumValues[i]);
                }
                System.out.println("Choose an option (1-" + enumValues.length + "):");
                int ordinal = scanner.nextInt();

                if (ordinal > 0 && ordinal <= enumValues.length) {
                    selectedValue = enumValues[ordinal - 1];
                    dataOk = true;
                } else {
                    System.out.println("Invalid option, choose an option between (1 and " + enumValues.length + "):");
                }
            } catch (InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                scanner.nextLine();
            } finally {
                scanner.nextLine();
            }
        }
        return selectedValue;
    }
}
