import MyException.NotEnoughtAge;
import RegexPattern.Example;

import java.util.Scanner;

public class Input {
    public static int inputAge() {
        Scanner scanner = new Scanner(System.in);
        int age = 0;
        boolean isValidInput = false;

        do {
            try {
                age = Integer.parseInt(scanner.nextLine());

                if (age < 18) {
                    throw new NotEnoughtAge("You're not enought age");
                }

                isValidInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Wrong format, please retype!");
            } catch (NotEnoughtAge e) {
                System.err.println(e.getMessage());
            }
        } while (!isValidInput);

        return age;
    }

    public static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Wrong format, please retype!");
            }
        }
    }

    public static String inputDate() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String date = input.nextLine().trim();
            try {
                    boolean isValid = Example.checkCheckinCheckout(date);
                    if (isValid) {
                        return date;
                    } else {
                        throw new IllegalAccessException("Wrong format, please retype!");
                    }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static String inputName() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String name = input.nextLine().trim();
            try {
                boolean isValid = Example.checkCustomerName(name);
                if (isValid) {
                    return name;
                } else {
                    throw new IllegalAccessException("Wrong format, please retype!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
