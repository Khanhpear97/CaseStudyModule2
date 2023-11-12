package regexPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String CUSTOMER_NAME = "^[A-Za-z]+(?:\\s[A-Za-z]+)?$";
    private static final String CHECKIN_CHECKOUT = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)" +
            "(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3" +
            "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
            "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    public static boolean checkCustomerName(String customerName) {
        pattern = Pattern.compile(CUSTOMER_NAME);
        matcher = pattern.matcher(customerName);
        return matcher.matches();
    }

    public static boolean checkCheckinCheckout(String checkInCheckOut) {
        pattern = Pattern.compile(CHECKIN_CHECKOUT);
        matcher = pattern.matcher(checkInCheckOut);
        return matcher.matches();
    }
}
