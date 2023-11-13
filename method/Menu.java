package method;

import myException.NotFoundID;
import src.Villa;

public class Menu implements Constance {

    public void start() {
        System.out.println("------Menu------");
        Villa villa = new Villa();
        do {
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Hiển thị danh sách khách hàng");
            System.out.println("3. Xoá thông tin khách hàng");
            System.out.println("4. Sửa thông tin đặt phòng");
            System.out.println("5. Tìm kiếm khách hàng");
            System.out.println("6. Hiển thị file text");
            System.out.println("0. Thoát chương trình");
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
                case SEARCH_CUSTOMER:
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
