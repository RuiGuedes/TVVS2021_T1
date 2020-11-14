/**
 * 
 */

package org.apache.commons.lang.time;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.commons.lang.SystemUtils;

public class StudentTest extends TestCase {

    private static final long MILLIS_TEST;
    static {
        GregorianCalendar cal = new GregorianCalendar(2000, 6, 5, 4, 3, 2);
        cal.set(Calendar.MILLISECOND, 1);
        MILLIS_TEST = cal.getTime().getTime();
    }

    DateFormat dateParser = null;
    DateFormat dateTimeParser = null;
    DateFormat timeZoneDateParser = null;
    Date dateAmPm1 = null;
    Date dateAmPm2 = null;
    Date dateAmPm3 = null;
    Date dateAmPm4 = null;
    Date date0 = null;
    Date date1 = null;
    Date date2 = null;
    Date date3 = null;
    Date date4 = null;
    Date date5 = null;
    Date date6 = null;
    Date date7 = null;
    Date date8 = null;
    Calendar calAmPm1 = null;
    Calendar calAmPm2 = null;
    Calendar calAmPm3 = null;
    Calendar calAmPm4 = null;
    Calendar cal1 = null;
    Calendar cal2 = null;
    Calendar cal3 = null;
    Calendar cal4 = null;
    Calendar cal5 = null;
    Calendar cal6 = null;
    Calendar cal7 = null;
    Calendar cal8 = null;
    TimeZone zone = null;
    TimeZone defaultZone = null;

    public StudentTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(StudentTest.class);
        suite.setName("Student Tests");
        return suite;
    }

    public void testIsSameDay_Cal() {
        GregorianCalendar cal1 = new GregorianCalendar(2004, 6, 9, 13, 45);
        GregorianCalendar cal2 = new GregorianCalendar(2004, 6, 9, 13, 45);
        assertEquals(true, DateUtils.isSameDay(cal1, cal2));
        cal2.add(Calendar.DAY_OF_YEAR, 1);
        assertEquals(false, DateUtils.isSameDay(cal1, cal2));
        cal1.add(Calendar.DAY_OF_YEAR, 1);
        assertEquals(true, DateUtils.isSameDay(cal1, cal2));
        cal2.add(Calendar.YEAR, 1);
        assertEquals(false, DateUtils.isSameDay(cal1, cal2));
        try {
            DateUtils.isSameDay((Calendar) null, (Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}