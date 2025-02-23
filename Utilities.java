public class Utilities {
    public static String formatNumber(double number_to_format, int decimal_places) {
        // Check if the number is an integer
        if (number_to_format == Math.floor(number_to_format)) {
            return String.format("%.0f", number_to_format);  
        } else {
            return String.format("%." + decimal_places + "f", number_to_format);  
        }
    }
}
