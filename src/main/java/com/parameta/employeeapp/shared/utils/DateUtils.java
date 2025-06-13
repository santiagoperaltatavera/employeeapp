package com.parameta.employeeapp.shared.utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public final class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
        try {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (Exception e) {
            throw new RuntimeException("Error converting LocalDate to XMLGregorianCalendar", e);
        }
    }

    public static LocalDate toLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar.toGregorianCalendar()
                .toZonedDateTime()
                .toLocalDate();
    }
    public static Period calculatePeriod(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    public static String formatPeriod(Period period) {
        return String.format("%d years, %d months, %d days",
                period.getYears(), period.getMonths(), period.getDays());
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
