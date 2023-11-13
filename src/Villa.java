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
            System.out.println("Villa is unavailable");
        }
    }

    public void removeCustomerByID() throws NotFoundID {
        System.out.println("Enter ID to remove customer");
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
            throw new NotFoundID("Not found customer ID");
        }
    }

    public void editCustomerByID() throws NotFoundID {
        System.out.println("Enter ID to edit customer");
        int id = Input.inputNumber();
        boolean check = false;
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                check = true;
                String checkInTemp = customer.getCheckinDate();
                String checkOutTemp = customer.getCheckoutDate();
                System.out.println("Enter new check in date:");
                String newCheckInDate = String.valueOf(Input.inputDate());
                System.out.println("Enter new check out date:");
                String newCheckOutDate = String.valueOf(Input.inputDate());
                customer.setCheckinDate(newCheckInDate);
                customer.setCheckoutDate(newCheckOutDate);

                isNewDateAvailable(customer, checkInTemp, checkOutTemp);
                break;
            }
        }

        if (!check) {
            throw new NotFoundID("Not found customer ID");
        }
    }

    private void isNewDateAvailable(Customer customer, String checkInTemp, String checkOutTemp) {
        if (checkVillaAvailable(customer)) {
            writeCustomerToList();
        } else {
            System.out.println("Your new check in/ check out date is unavailable");
            customer.setCheckinDate(checkInTemp);
            customer.setCheckoutDate(checkOutTemp);
        }
    }
}
