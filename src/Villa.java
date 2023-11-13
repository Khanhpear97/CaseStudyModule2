package src;

import method.Input;
import myException.NotFoundID;

import java.io.*;
import java.util.ArrayList;

public class Villa {
    public ArrayList<Customer> customers = new ArrayList<>();

    public Villa() {
    }

    public void writeCustomerToList() {
        try {
            FileOutputStream fo = new FileOutputStream("CustomerList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(customers);
            fo.close();
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readCustomerInList() {
        try {
            FileInputStream fi = new FileInputStream("CustomerList.txt");
            ObjectInputStream ois = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                Object obj = ois.readObject();
                System.out.println(obj);
            }
            fi.close();
            ois.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getCustomerInVilla() {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    public boolean checkVillaAvailable(Customer customer) {
        boolean check = true;

        for (Customer customers : customers) {
            if (customer.getId() == customers.getId()) {
                continue;
            }
            if (customer.getCheckoutLocalDate().isAfter(customers.getCheckinLocalDate()) &&
                    customer.getCheckinLocalDate().isBefore(customers.getCheckoutLocalDate())) {
                check = false;
            }
        }
        return check;
    }

    public void addCustomer() {
        Customer customer = new Customer();
        customer.input();


        if (checkVillaAvailable(customer)) {
            customers.add(customer);
            writeCustomerToList();
        } else {
            System.err.println("Lịch đặt phòng không còn trống.");
        }
    }

    public void removeCustomerByID() throws NotFoundID {
        System.out.println("Nhập ID để xoá khách đặt phòng:");
        int id = Input.inputNumber();
        boolean check = false;
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                check = true;
                customers.remove(customer);
                writeCustomerToList();
                break;
            }
        }

        if (!check) {
            throw new NotFoundID("Không tìm thấy ID khách hàng.");
        }
    }

    public void editCustomerByID() throws NotFoundID {
        System.out.println("Nhập ID để chỉnh sửa thông tin đặt phòng:");
        int id = Input.inputNumber();
        boolean check = false;
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                check = true;
                String checkInTemp = customer.getCheckinDate();
                String checkOutTemp = customer.getCheckoutDate();
                System.out.println("Nhập ngày check in mới:");
                String newCheckInDate = String.valueOf(Input.inputDate());
                System.out.println("Nhập ngày check out mới:");
                String newCheckOutDate = String.valueOf(Input.inputDate());
                customer.setCheckinDate(newCheckInDate);
                customer.setCheckoutDate(newCheckOutDate);

                isNewDateAvailable(customer, checkInTemp, checkOutTemp);
                break;
            }
        }

        if (!check) {
            throw new NotFoundID("Không tìm thấy ID khách hàng.");
        }
    }

    private void isNewDateAvailable(Customer customer, String checkInTemp, String checkOutTemp) {
        if (checkVillaAvailable(customer)) {
            writeCustomerToList();
        } else {
            System.err.println("Lịch đặt phòng mới không còn trống.");
            customer.setCheckinDate(checkInTemp);
            customer.setCheckoutDate(checkOutTemp);
        }
    }
}
