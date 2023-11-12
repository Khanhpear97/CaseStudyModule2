import MyException.NotFoundID;

import java.util.Scanner;

public class Menu {
    public void start() {
        System.out.println("------Menu------");
        Villa villa = new Villa("Villa 1");
        do {
            System.out.println("1. Add customer");
            System.out.println("2. Show customer manager");
            System.out.println("3. Remove customer");

            System.out.println("0. Exit menu");
            Scanner scanner = new Scanner(System.in);
            int key = Input.inputNumber();
            switch (key) {
                case 1:
                    villa.addCustomer();
                    break;
                case 2:
                    villa.getCustomerInVilla();
                    break;
                case 3:
                    try {
                        villa.removeCustomerByID();
                    } catch (NotFoundID e) {
                        System.err.println(e.getMessage());
                    } finally {
                        break;
                    }
                case 0:
                    return;
            }
        } while (true);
    }
}
