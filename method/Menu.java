package src;

import myException.NotFoundID;

public class Menu implements Constance {

    public void start() {
        System.out.println("------Menu------");
        Villa villa = new Villa();
        do {
            System.out.println("1. Add customer");
            System.out.println("2. Show customer list");
            System.out.println("3. Remove customer");
            System.out.println("4. Edit customer check in/ check out date");
            System.out.println("5. Search customer");
            System.out.println("6. Show text file");
            System.out.println("0. Exit menu");
            int key = Input.inputNumber();
            switch (key) {
                case ADD_CUSTOMER:
                    villa.addCustomer();
                    break;
                case SHOW_LIST:
                    villa.getCustomerInVilla();
                    break;
                case REMOVE_CUSTOMER:
                    try {
                        villa.removeCustomerByID();
                    } catch (NotFoundID e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case EDIT_CUSTOMER:
                    try {
                        villa.editCustomerByID();
                    } catch (NotFoundID e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:

                    Search search = new Search(villa.customers);
                    try {
                        search.searchMenu();
                    } catch (NotFoundID e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case READ_TEXT_FILE:
                    villa.readCustomerInList();
                    break;
                case EXIT_MENU:
                    return;
            }
        } while (true);
    }
}
