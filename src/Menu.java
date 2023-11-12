package src;

import myException.NotFoundID;

public class Menu {
    public void start() {
        System.out.println("------Menu------");
        Villa villa = new Villa("Src.Villa 1");
        do {
            System.out.println("1. Add customer");
            System.out.println("2. Show customer manager");
            System.out.println("3. Remove customer");
            System.out.println("4. Edit customer check in/ check out date");
            System.out.println("5. Show text file");
            System.out.println("0. Exit menu");
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
                case 4:
                    try {
                        villa.editCustomerByID();
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
