package e2p.icotp.util.custom;

import java.time.LocalDate;

import javafx.util.StringConverter;

public class LocalizeDateConverter extends StringConverter<LocalDate> {

    @Override
    public String toString(LocalDate date) {
        return date != null ? DateUtil.localizeDate(date) : null;
    }

    @Override
    public LocalDate fromString(String dateStr) {
        return dateStr != null && !dateStr.isEmpty() ? DateUtil.parse(dateStr) : null;
    }

}
