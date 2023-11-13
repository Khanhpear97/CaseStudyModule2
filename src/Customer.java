package src;

import method.Input;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Customer implements Serializable{
    private String customerName;
    private int customerAge;
    private String checkinDate;
    private String checkoutDate;
    private static int count = 0;
    private int id = 0;

    public Customer(String customerName, int customerAge,
                    String checkinDate, String checkoutDate) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.id = ++count;
    }

    public Customer(){
        this.id = ++count;
    }

    public void input() {
        System.out.println("Customer name:");
        this.customerName = Input.inputName();
        System.out.println("Customer age:");
        this.customerAge = Input.inputAge();
        System.out.println("Check in:");
        checkinDate = String.valueOf(Input.inputDate());
        System.out.println("Check out:");
        checkoutDate = String.valueOf(Input.inputDate());
    }


    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public LocalDate getCheckinLocalDate() {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter();
        return LocalDate.parse(getCheckinDate(), dateTimeFormatter);
    }

    public LocalDate getCheckoutLocalDate() {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter();
        return LocalDate.parse(getCheckoutDate(), dateTimeFormatter);
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[dd/MM/yyyy]" + "[dd-MM-yyyy]" + "[dd.MM.yyyy]"));
        return dateTimeFormatterBuilder.toFormatter();
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public static void setCount(int count) {
        Customer.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "ID: " + getId() + " | Customer name: " + getCustomerName() + " | Age: " + getCustomerAge() +
                " | Checkin: " + getCheckinLocalDate() + " | Checkout: " + getCheckoutLocalDate();
    }
}
