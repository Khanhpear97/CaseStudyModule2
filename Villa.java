import MyException.NotFoundID;

import java.io.*;
import java.util.ArrayList;

public class Villa {
    ArrayList<Customer> customers = new ArrayList<>();
    private String villaName;

    public Villa(String villaName) {
        this.villaName = villaName;
    }

    public String getVillaName() {
        return villaName;
    }

    public void setVillaName(String villaName) {
        this.villaName = villaName;
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
            System.out.println("Phòng " + getVillaName() + " đã có người.");
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
}
