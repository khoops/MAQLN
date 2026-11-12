import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class SetterHelperMethods {
    // IllegalArgumentException for the setters
    // Field and value are named if bad for tracing, but only for manadatory fields
    // (those from Tabled API)

     static String requireText(String field, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " missing");
        }
        return value;
    }

    // for Answer related fields which may be missing until answered
     static boolean isMissing(String value) {
        return value == null || value.isBlank();
    }

     static int parseInt(String field, String value) {
        requireText(field, value);
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(field + " is not a whole number: \"" + value + "\"", e);
        }
    }

    // Trims API's YYYY-MM-DDT00:00:00+00:00 formatting to keep only YYYY-MM-DD
     static LocalDate parseDate(String field, String value) {
        requireText(field, value);
        try {
            return OffsetDateTime.parse(value.trim()).toLocalDate();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(field + " is not a valid date: \"" + value + "\"", e);
        }
    }

     static boolean parseBoolean(String field, String value) {
        String v = requireText(field, value).trim().toLowerCase();
        if (!v.equals("true") && !v.equals("false")) {
            throw new IllegalArgumentException(field + " is not true/false: \"" + value + "\"");
        }
        return Boolean.parseBoolean(v);
    }


}
