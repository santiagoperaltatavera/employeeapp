package com.parameta.employeeapp.shared;

import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void testToXMLGregorianCalendarSuccess() {
        LocalDate localDate = LocalDate.of(2023, 10, 1);
        XMLGregorianCalendar xmlGregorianCalendar = DateUtils.toXMLGregorianCalendar(localDate);

        assertNotNull(xmlGregorianCalendar);
        assertEquals(localDate.getYear(), xmlGregorianCalendar.getYear());
        assertEquals(localDate.getMonthValue(), xmlGregorianCalendar.getMonth());
        assertEquals(localDate.getDayOfMonth(), xmlGregorianCalendar.getDay());
    }

    @Test
    void testToLocalDateSuccess() {
        LocalDate localDate = LocalDate.of(2023, 10, 1);
        XMLGregorianCalendar xmlGregorianCalendar = DateUtils.toXMLGregorianCalendar(localDate);
        LocalDate convertedDate = DateUtils.toLocalDate(xmlGregorianCalendar);

        assertEquals(localDate, convertedDate);
    }

    @Test
    void testCalculatePeriodSuccess() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 1);
        Period period = DateUtils.calculatePeriod(startDate, endDate);

        assertEquals(3, period.getYears());
        assertEquals(9, period.getMonths());
        assertEquals(0, period.getDays());
    }

    @Test
    void testFormatPeriodSuccess() {
        Period period = Period.of(3, 9, 0);
        String formattedPeriod = DateUtils.formatPeriod(period);

        assertEquals("3 years, 9 months, 0 days", formattedPeriod);
    }

    @Test
    void testFormatDateSuccess() {
        LocalDate localDate = LocalDate.of(2023, 10, 1);
        String formattedDate = DateUtils.formatDate(localDate);

        assertEquals("2023-10-01", formattedDate);
    }

    @Test
    void testToXMLGregorianCalendarFailure() {
        assertThrows(RuntimeException.class, () -> DateUtils.toXMLGregorianCalendar(null));
    }
}
