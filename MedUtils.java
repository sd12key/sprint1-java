import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class MedUtils {
    public static String formatDoseNumber(double dose, int decimal_places) {
        // Check if the dose number is an integer
        if (dose == Math.floor(dose)) {
            return String.format("%.0f", dose);  
        } else {
            return String.format("%." + decimal_places + "f", dose);  
        }
    }

    /**
     * Validates the name.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean validateName(String name) {
        return (name != null) && (!name.trim().isEmpty());
    }

    /**
     * Validates the age.
     *
     * @param age the age to validate
     * @return true if the age is valid, false otherwise
     */
    public static boolean validateAge(int age) {
        return (age > 0) && (age <= 150);
    }

    public static boolean validateStockQty(int stock_qty) {
        return (stock_qty >= 0);
    }

    public static boolean validateDose(double dose) {
        return (dose > 0.0);
    }

    /**
     * Validates the phone number.
     *
     * @param phone_number the phone number to validate
     * @return true if the phone number is valid, false otherwise
     */
    public static boolean validatePhoneNumber(String phone_number) {
        return (phone_number != null) && (phone_number.trim().replaceAll("\\D", "").length() >= 10);
    }

    public static boolean validateYearMonthString(String year_month) {
        // Regular expression to match the format YEAR-MO
        String year_month_regex = "^(20\\d{2})-(0[1-9]|1[0-2])$";
        if (!year_month.trim().matches(year_month_regex)) {
            return false; 
        }
        return true;
    }

    public static LocalDate stringMedExpToLocalDate(String year_month) {
        DateTimeFormatter med_exp_formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth med_exp_year_month = YearMonth.parse(year_month.trim(), med_exp_formatter);
        return med_exp_year_month.atEndOfMonth();
    }

    public static LocalDate localDateToMedExp(LocalDate expiry_date){
        YearMonth med_exp_year_month = YearMonth.from(expiry_date);
        return med_exp_year_month.atEndOfMonth();
    }

    public static String localDateMedExpToString(LocalDate med_exp_local_date) {
        DateTimeFormatter med_exp_formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return med_exp_local_date.format(med_exp_formatter);
    }

    public static boolean validateDateString(String date) {
        // regex to match the format XXXX-XX-XX
        // not checking leap years!
        String dateRegex = "^(20\\d{2})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        if (!date.trim().matches(dateRegex)) {
            return false;
        }
        return true;
    }    
    
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date.trim(), formatter);
    }

    // normalize name
    public static String normalizeName(String name) {
        return name.trim().replaceAll("\\s+", " ");
    }

    // normalize phone number
    public static String normalizePhoneNumber(String phone_number) {
        phone_number = phone_number.trim().replaceAll("\\D", "");
        return phone_number.substring(0, Math.min(10, phone_number.length()));
    }

    // check if string represents integer
    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // check if string represents double
    public static boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



}
