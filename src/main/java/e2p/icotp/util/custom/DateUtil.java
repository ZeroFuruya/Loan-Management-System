package e2p.icotp.util.custom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateUtil {

    private static final String DATE_PATTERN = "MM/dd/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        return date == null ? "" : DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateStr) {
        try {
            return DATE_FORMATTER.parse(dateStr, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValid(String dateStr) {
        return parse(dateStr) != null;
    }

    public static String localizeDate(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public static StringProperty localizeDateProperty(LocalDate date) {
        return new SimpleStringProperty(date.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }
}
