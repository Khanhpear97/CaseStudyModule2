package method;

import myException.NotFoundID;
import src.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class Search implements Constance {
    ArrayList<Customer> list;

    public Search(ArrayList<Customer> customerArrayList) {
        this.list = customerArrayList;
    }

    public void searchMenu() throws NotFoundID {
        do {
            System.out.println("1. Search by ID");
            System.out.println("2. Search by date range");
            System.out.println("0. Exit search menu");
            int key = Input.inputNumber();
            switch (key) {
                case SEARCH_BY_ID:
                    searchByID();
                    break;
                case SEARCH_BY_DATE:
                    searchByDate();
                    break;
                case EXIT_SEARCH:
                    return;
            }
        } while (true);
    }

    private void searchByID() throws NotFoundID {
        ArrayList<Customer> data = new ArrayList<>();
        System.out.println("Enter ID to search customer");
        int id = Input.inputNumber();
        boolean check = false;
        for (Customer customer : list) {
            if (id == customer.getId()) {
                check = true;
                data.add(customer);
                break;
            }
        }
        if (!check) {
            throw new NotFoundID("Not found customer ID");
        }
        display(data);
    }

    private void searchByDate() {
        ArrayList<Customer> data = new ArrayList<>();
        System.out.println("Enter a date range to search");
        String firstDate = String.valueOf(Input.inputDate());
        String secondDate = String.valueOf(Input.inputDate());
        for (Customer customer : list) {
            int checkFirst = getLocalDate(firstDate).compareTo(customer.getCheckinLocalDate());
            int checkLast = getLocalDate(secondDate).compareTo(customer.getCheckoutLocalDate());
            if (checkFirst <= 0 && checkLast >= 0) {
                data.add(customer);
            }
        }
        display(data);
    }

    private void display(ArrayList<Customer> data) {
        for (Customer customer : data) {
            System.out.println(customer.toString());
        }
    }

    private LocalDate getLocalDate(String inputDate) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter();
        return LocalDate.parse(inputDate, dateTimeFormatter);
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[dd/MM/yyyy]" + "[dd-MM-yyyy]" + "[dd.MM.yyyy]"));
        return dateTimeFormatterBuilder.toFormatter();
    }
}
