/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

/**
 * Unit tests {@link org.apache.commons.lang.time.DateUtils}.
 *
 * @author <a href="mailto:sergek@lokitech.com">Serge Knystautas</a>
 * @author <a href="mailto:steve@mungoknotwise.com">Steven Caswell</a>
 */
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
        suite.setName("DateUtils Tests");
        return suite;
    }

    protected void setUp() throws Exception {
        super.setUp();

        dateParser = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);

        dateAmPm1 = dateTimeParser.parse("February 3, 2002 01:10:00.000");
        dateAmPm2 = dateTimeParser.parse("February 3, 2002 11:10:00.000");
        dateAmPm3 = dateTimeParser.parse("February 3, 2002 13:10:00.000");
        dateAmPm4 = dateTimeParser.parse("February 3, 2002 19:10:00.000");
        date0 = dateTimeParser.parse("February 3, 2002 12:34:56.789");
        date1 = dateTimeParser.parse("February 12, 2002 12:34:56.789");
        date2 = dateTimeParser.parse("November 18, 2001 1:23:11.321");
        defaultZone = TimeZone.getDefault();
        zone = TimeZone.getTimeZone("MET");
        TimeZone.setDefault(zone);
        dateTimeParser.setTimeZone(zone);
        date3 = dateTimeParser.parse("March 30, 2003 05:30:45.000");
        date4 = dateTimeParser.parse("March 30, 2003 01:10:00.000");
        date5 = dateTimeParser.parse("March 30, 2003 01:40:00.000");
        date6 = dateTimeParser.parse("March 30, 2003 02:10:00.000");
        date7 = dateTimeParser.parse("March 30, 2003 02:40:00.000");
        date8 = dateTimeParser.parse("October 26, 2003 05:30:45.000");
        dateTimeParser.setTimeZone(defaultZone);
        TimeZone.setDefault(defaultZone);
        calAmPm1 = Calendar.getInstance();
        calAmPm1.setTime(dateAmPm1);
        calAmPm2 = Calendar.getInstance();
        calAmPm2.setTime(dateAmPm2);
        calAmPm3 = Calendar.getInstance();
        calAmPm3.setTime(dateAmPm3);
        calAmPm4 = Calendar.getInstance();
        calAmPm4.setTime(dateAmPm4);
        cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        TimeZone.setDefault(zone);
        cal3 = Calendar.getInstance();
        cal3.setTime(date3);
        cal4 = Calendar.getInstance();
        cal4.setTime(date4);
        cal5 = Calendar.getInstance();
        cal5.setTime(date5);
        cal6 = Calendar.getInstance();
        cal6.setTime(date6);
        cal7 = Calendar.getInstance();
        cal7.setTime(date7);
        cal8 = Calendar.getInstance();
        cal8.setTime(date8);
        TimeZone.setDefault(defaultZone);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 169
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that the following
     * mutants should be killed with the current test.
     * 
     * Operator -> JIR_Ifgt | JIR_Iflt
     * Lines    -> 169      | 169
     * 
     */
    public void testIsSameDayEraGreater() {
            assertTrue(DateUtils.isSameDay(new GregorianCalendar(2020, 12, 01, 00, 00),
                                           new GregorianCalendar(2020, 12, 01, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 170
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 170
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameDayYearLess() {
        assertFalse(DateUtils.isSameDay(new GregorianCalendar(2019, 12, 01, 00, 00), 
                                        new GregorianCalendar(2020, 12, 01, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 170
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 170
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameDayYearGreater() {        
        assertFalse(DateUtils.isSameDay(new GregorianCalendar(2020, 12, 01, 00, 00),
                                        new GregorianCalendar(2019, 12, 01, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 171
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameDayDayOfYearLess() {
        assertFalse(DateUtils.isSameDay(new GregorianCalendar(2020, 12, 01, 00, 00),
                                        new GregorianCalendar(2020, 12, 02, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 190
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameInstantDateLess() {        
        assertFalse(DateUtils.isSameInstant(new GregorianCalendar(2003, 6, 9, 13, 45).getTime(),
                                            new GregorianCalendar(2004, 6, 9, 13, 45).getTime()));
    }

   /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 208
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameInstantCalendarLess() {
        Calendar c1 = new GregorianCalendar(2020, 12, 01, 00, 00);
        Calendar c2 = new GregorianCalendar(2020, 12, 01, 00, 00);

        c1.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.MILLISECOND, 1);

        assertFalse(DateUtils.isSameInstant(c1, c2));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 228
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 228
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeMilliLess() {        
        Calendar c1 = new GregorianCalendar(2020, 12, 01, 00, 00);
        Calendar c2 = new GregorianCalendar(2020, 12, 01, 00, 00);

        c1.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.MILLISECOND, 1);

        assertFalse(DateUtils.isSameLocalTime(c1, c2));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 228
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 228
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeMilliGreater() {        
        Calendar c1 = new GregorianCalendar(2020, 12, 01, 00, 00);
        Calendar c2 = new GregorianCalendar(2020, 12, 01, 00, 00);

        c1.set(Calendar.MILLISECOND, 1);
        c2.set(Calendar.MILLISECOND, 0);

        assertFalse(DateUtils.isSameLocalTime(c1, c2));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 229
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 229
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeSecondLess() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 00),
                                              new GregorianCalendar(2020, 12, 01, 00, 00, 01)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 229
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 229
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeSecondGreater() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 01),
                                              new GregorianCalendar(2020, 12, 01, 00, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 230
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 230
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeMinuteLess() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 00), 
                                              new GregorianCalendar(2020, 12, 01, 00, 01, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 230
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 230
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeMinuteGreater() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 01, 00), 
                                              new GregorianCalendar(2020, 12, 01, 00, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 231
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeHourLess() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 00),
                                              new GregorianCalendar(2020, 12, 01, 01, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 232
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 232
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeDayOfYearLess() {        
        Calendar c1 = new GregorianCalendar(2020, 12, 01, 00, 00);
        Calendar c2 = new GregorianCalendar(2020, 12, 01, 00, 00);

        c1.set(Calendar.DAY_OF_YEAR, 1);
        c2.set(Calendar.DAY_OF_YEAR, 2);

        assertFalse(DateUtils.isSameLocalTime(c1, c2));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 232
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 232
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeDayOfYearGreater() {
        Calendar c1 = new GregorianCalendar(2020, 12, 01, 00, 00);
        Calendar c2 = new GregorianCalendar(2020, 12, 01, 00, 00);

        c1.set(Calendar.DAY_OF_YEAR, 2);
        c2.set(Calendar.DAY_OF_YEAR, 1);

        assertFalse(DateUtils.isSameLocalTime(c1, c2));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 233
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 233
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeYearLess() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2019, 12, 01, 00, 00, 00),
                                              new GregorianCalendar(2020, 12, 01, 00, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 233
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 233
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testIsSameLocalTimeYearGreater() {
        assertFalse(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 00),
                                              new GregorianCalendar(2019, 12, 01, 00, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 234
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that the following
     * mutants should be killed with the current test.
     * 
     * Operator -> JIR_Ifgt | JIR_Iflt
     * Lines    -> 234      | 234
     * 
     */
    public void testIsSameLocalTimeEraGreater() {
        assertTrue(DateUtils.isSameLocalTime(new GregorianCalendar(2020, 12, 01, 00, 00, 00),
                                             new GregorianCalendar(2020, 12, 01, 00, 00, 00)));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 260
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testParseDateSimpleDateFormatGreater() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1972, Calendar.NOVEMBER, 3);
        String dateStr = "1972-11-03";
        String[] patterns = new String[] {"yyyy'-'DDD", "yyyy'-'MM'-'dd", "yyyyMMdd"};

        try {
            Date date = DateUtils.parseDate(dateStr, patterns);
            assertEquals(date, cal.getTime());
        } catch (NullPointerException e) {
            fail();
        }
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> EGE
     * Lines    -> 399
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> EGE
     * Lines    -> 399
     * 
     */
    public void testAddIllegalArgException() throws Exception {
        try { DateUtils.addSeconds(null, 0); } 
        catch (IllegalArgumentException e) { assertEquals("The date must not be null", e.getMessage()); }
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 641
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 641
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyMilliLessOrEqual() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:23:12.000"),
                DateUtils.round(dateParse.parse("November 18, 2001 1:23:11.500"), Calendar.SECOND));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 641
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyMilliGreater() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:23:12.000"),
                     DateUtils.round(dateParse.parse("November 18, 2001 1:23:11.600"), Calendar.SECOND));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 644
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifySecondDone() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:23:31.000"), 
                     DateUtils.round(dateParse.parse("November 18, 2001 1:23:30.600"), Calendar.SECOND));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 650
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 650
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifySecondLessOrEqual() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:24:00.000"), 
                     DateUtils.round(dateParse.parse("November 18, 2001 1:23:30.000"), Calendar.MINUTE));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 650
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifySecondGreater() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:24:00.000"), 
                     DateUtils.round(dateParse.parse("November 18, 2001 1:23:35.000"), Calendar.MINUTE));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 653
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyMinuteDone() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 1:20:00.000"), 
                     DateUtils.round(dateParse.parse("November 18, 2001 1:20:20.000"), Calendar.MINUTE));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 659
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 659
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyHourOfDayLessOrEqual() throws ParseException { 
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 2:00:00.000"),
                     DateUtils.round(dateParse.parse("November 18, 2001 1:30:00.000"), Calendar.HOUR_OF_DAY));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 659
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyHourOfDayGreater() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 18, 2001 2:00:00.000"),
                     DateUtils.round(dateParse.parse("November 18, 2001 1:35:00.000"), Calendar.HOUR_OF_DAY));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 676
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifySemiMonthSpecialCase() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 16, 2001 0:00:00.000"),
                     DateUtils.round(dateParse.parse("November 9, 2001 0:00:00.000"), DateUtils.SEMI_MONTH));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 680
     * 
     * -----------------------------------------------
     * 
     * This test should have killed the following 
     * mutant(s):
     * 
     * Operator -> JIR_Iflt | JIR_Ifgt | JIR_Ifgt
     * Lines    -> 680      | 680      | 701
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that these 
     * mutants should be killed with the current test.
     * 
     */
    public void testModifySemiMonthSpecialCaseDayOne() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 16, 2001 0:00:00.000"),
                     DateUtils.round(dateParse.parse("November 15, 2001 0:00:00.000"), DateUtils.SEMI_MONTH));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 708
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifySemiMonthOffsetComparison() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("December 16, 2001 0:00:00.000"),
                     DateUtils.truncate(dateParse.parse("December 19, 2001 0:00:00.000"), DateUtils.SEMI_MONTH));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 712
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 712
     * 
     */
    public void testModifySemiMonthOffsetGreater() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 1, 2001 0:00:00.000"),
                     DateUtils.round(dateParse.parse("November 8, 2001 0:00:00.000"), DateUtils.SEMI_MONTH));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 717
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 721
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyAmPmHourOfDayField() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 8, 2001 12:00:00.000"),
                     DateUtils.round(dateParse.parse("November 8, 2001 12:30:30.500"), Calendar.AM_PM));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Ifle
     * Lines    -> 721
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyAmPmHourOfDayOffsetComparison() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 8, 2001 12:00:00.000"),
                     DateUtils.round(dateParse.parse("November 8, 2001 13:30:30.500"), Calendar.AM_PM));
    }

    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> JIR_Iflt
     * Lines    -> 724
     * 
     * -----------------------------------------------
     * 
     * This test does not kill any mutant. However, by 
     * manually testing, we know for sure that this 
     * mutant should be killed with the current test.
     * 
     */
    public void testModifyAmPmHourOfDayOffsetGreater() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("November 9, 2001 0:00:00.000"),
                     DateUtils.round(dateParse.parse("November 8, 2001 7:00:00.000"), Calendar.AM_PM));
    }


    /**
     * This test aims to kill the following mutant(s):
     * 
     * Operator -> AIR_Add 
     * Lines    -> 735
     * 
     * -----------------------------------------------
     * 
     * This test kills the following mutant(s):
     * 
     * Operator -> AIR_Add | AIR_LeftOperand
     * Lines    -> 735     | 735
     * 
     * -----------------------------------------------
     * 
     * This test should also have killed the following 
     * mutant(s):
     * 
     * Operator -> JIR_Iflt | JIR_Ifle
     * Lines    -> 735      | 738
     * 
     * -----------------------------------------------
     * 
     * By manually testing, we know for sure that these 
     * mutants should be killed with the current test.
     * 
     */
    public void testModifyMonthOffsetArithmetic() throws ParseException {
        DateFormat dateParse = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        
        assertEquals(dateParse.parse("December 01, 2001 0:00:00.000"),
                     DateUtils.round(dateParse.parse("November 16, 2001 11:00:00.000"), Calendar.MONTH));
    }

























    //-----------------------------------------------------------------------
    public void testConstructor() {
        assertNotNull(new DateUtils());
        Constructor[] cons = DateUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertEquals(true, Modifier.isPublic(cons[0].getModifiers()));
        assertEquals(true, Modifier.isPublic(DateUtils.class.getModifiers()));
        assertEquals(false, Modifier.isFinal(DateUtils.class.getModifiers()));
    }
    
    //-----------------------------------------------------------------------
    public void testIsSameDay_Date() {
        Date date1 = new GregorianCalendar(2004, 6, 9, 13, 45).getTime();
        Date date2 = new GregorianCalendar(2004, 6, 9, 13, 45).getTime();
        assertEquals(true, DateUtils.isSameDay(date1, date2));
        date2 = new GregorianCalendar(2004, 6, 10, 13, 45).getTime();
        assertEquals(false, DateUtils.isSameDay(date1, date2));
        date1 = new GregorianCalendar(2004, 6, 10, 13, 45).getTime();
        assertEquals(true, DateUtils.isSameDay(date1, date2));
        date2 = new GregorianCalendar(2005, 6, 10, 13, 45).getTime();
        assertEquals(false, DateUtils.isSameDay(date1, date2));
        try {
            DateUtils.isSameDay((Date) null, (Date) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
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
    
    //-----------------------------------------------------------------------
    public void testIsSameInstant_Date() {
        Date date1 = new GregorianCalendar(2004, 6, 9, 13, 45).getTime();
        Date date2 = new GregorianCalendar(2004, 6, 9, 13, 45).getTime();
        assertEquals(true, DateUtils.isSameInstant(date1, date2));
        date2 = new GregorianCalendar(2004, 6, 10, 13, 45).getTime();
        assertEquals(false, DateUtils.isSameInstant(date1, date2));
        date1 = new GregorianCalendar(2004, 6, 10, 13, 45).getTime();
        assertEquals(true, DateUtils.isSameInstant(date1, date2));
        date2 = new GregorianCalendar(2005, 6, 10, 13, 45).getTime();
        assertEquals(false, DateUtils.isSameInstant(date1, date2));
        try {
            DateUtils.isSameInstant((Date) null, (Date) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testIsSameInstant_Cal() {
        GregorianCalendar cal1 = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
        GregorianCalendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("GMT-1"));
        cal1.set(2004, 6, 9, 13, 45, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        cal2.set(2004, 6, 9, 13, 45, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        assertEquals(false, DateUtils.isSameInstant(cal1, cal2));
        
        cal2.set(2004, 6, 9, 11, 45, 0);
        assertEquals(true, DateUtils.isSameInstant(cal1, cal2));
        try {
            DateUtils.isSameInstant((Calendar) null, (Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testIsSameLocalTime_Cal() {
        GregorianCalendar cal1 = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
        GregorianCalendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("GMT-1"));
        cal1.set(2004, 6, 9, 13, 45, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        cal2.set(2004, 6, 9, 13, 45, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        assertEquals(true, DateUtils.isSameLocalTime(cal1, cal2));
        
        cal2.set(2004, 6, 9, 11, 45, 0);
        assertEquals(false, DateUtils.isSameLocalTime(cal1, cal2));
        try {
            DateUtils.isSameLocalTime((Calendar) null, (Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testParseDate() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1972, 11, 3);
        String dateStr = "1972-12-03";
        String[] parsers = new String[] {"yyyy'-'DDD", "yyyy'-'MM'-'dd", "yyyyMMdd"};
        Date date = DateUtils.parseDate(dateStr, parsers);
        assertEquals(cal.getTime(), date);
        
        dateStr = "1972-338";
        date = DateUtils.parseDate(dateStr, parsers);
        assertEquals(cal.getTime(), date);
        
        dateStr = "19721203";
        date = DateUtils.parseDate(dateStr, parsers);
        assertEquals(cal.getTime(), date);
        
        try {
            DateUtils.parseDate("PURPLE", parsers);
            fail();
        } catch (ParseException ex) {}
        try {
            DateUtils.parseDate("197212AB", parsers);
            fail();
        } catch (ParseException ex) {}
        try {
            DateUtils.parseDate(null, parsers);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.parseDate(dateStr, null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testAddYears() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addYears(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addYears(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2001, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addYears(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 1999, 6, 5, 4, 3, 2, 1);
    }

    //-----------------------------------------------------------------------
    public void testAddMonths() {}
// Defects4J: flaky method
//     public void testAddMonths() throws Exception {
//         Date base = new Date(MILLIS_TEST);
//         Date result = DateUtils.addMonths(base, 0);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
//         
//         result = DateUtils.addMonths(base, 1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 7, 5, 4, 3, 2, 1);
//         
//         result = DateUtils.addMonths(base, -1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 5, 5, 4, 3, 2, 1);
//     }

    //-----------------------------------------------------------------------
    public void testAddWeeks() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addWeeks(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addWeeks(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 12, 4, 3, 2, 1);
        
        result = DateUtils.addWeeks(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);      // july
        assertDate(result, 2000, 5, 28, 4, 3, 2, 1);   // june
    }

    //-----------------------------------------------------------------------
    public void testAddDays() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addDays(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addDays(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 6, 4, 3, 2, 1);
        
        result = DateUtils.addDays(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 4, 4, 3, 2, 1);
    }

    //-----------------------------------------------------------------------
    public void testAddHours() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addHours(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addHours(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 5, 3, 2, 1);
        
        result = DateUtils.addHours(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 3, 3, 2, 1);
    }

    //-----------------------------------------------------------------------
    public void testAddMinutes() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addMinutes(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addMinutes(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 4, 2, 1);
        
        result = DateUtils.addMinutes(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 2, 2, 1);
    }

    //-----------------------------------------------------------------------
    public void testAddSeconds() throws Exception {
        Date base = new Date(MILLIS_TEST);
        Date result = DateUtils.addSeconds(base, 0);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
        
        result = DateUtils.addSeconds(base, 1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 3, 1);
        
        result = DateUtils.addSeconds(base, -1);
        assertNotSame(base, result);
        assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
        assertDate(result, 2000, 6, 5, 4, 3, 1, 1);
    }

    //-----------------------------------------------------------------------
    public void testAddMilliseconds() {}
// Defects4J: flaky method
//     public void testAddMilliseconds() throws Exception {
//         Date base = new Date(MILLIS_TEST);
//         Date result = DateUtils.addMilliseconds(base, 0);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
//         
//         result = DateUtils.addMilliseconds(base, 1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 6, 5, 4, 3, 2, 2);
//         
//         result = DateUtils.addMilliseconds(base, -1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 6, 5, 4, 3, 2, 0);
//     }

    //-----------------------------------------------------------------------
    public void testAddByField() {}
// Defects4J: flaky method
//     public void testAddByField() throws Exception {
//         Date base = new Date(MILLIS_TEST);
//         Date result = DateUtils.add(base, Calendar.YEAR, 0);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2000, 6, 5, 4, 3, 2, 1);
//         
//         result = DateUtils.add(base, Calendar.YEAR, 1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 2001, 6, 5, 4, 3, 2, 1);
//         
//         result = DateUtils.add(base, Calendar.YEAR, -1);
//         assertNotSame(base, result);
//         assertDate(base, 2000, 6, 5, 4, 3, 2, 1);
//         assertDate(result, 1999, 6, 5, 4, 3, 2, 1);
//     }

    //-----------------------------------------------------------------------
    private void assertDate(Date date, int year, int month, int day, int hour, int min, int sec, int mil) throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        assertEquals(year, cal.get(Calendar.YEAR));
        assertEquals(month, cal.get(Calendar.MONTH));
        assertEquals(day, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(hour, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(min, cal.get(Calendar.MINUTE));
        assertEquals(sec, cal.get(Calendar.SECOND));
        assertEquals(mil, cal.get(Calendar.MILLISECOND));
    }

    //-----------------------------------------------------------------------
    /**
     * Tests various values with the round method
     */
    public void testRound() throws Exception {
        // tests for public static Date round(Date date, int field)
        assertEquals("round year-1 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.round(date1, Calendar.YEAR));
        assertEquals("round year-2 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.round(date2, Calendar.YEAR));
        assertEquals("round month-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.round(date1, Calendar.MONTH));
        assertEquals("round month-2 failed",
                dateParser.parse("December 1, 2001"),
                DateUtils.round(date2, Calendar.MONTH));
        assertEquals("round semimonth-0 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.round(date0, DateUtils.SEMI_MONTH));
        assertEquals("round semimonth-1 failed",
                dateParser.parse("February 16, 2002"),
                DateUtils.round(date1, DateUtils.SEMI_MONTH));
        assertEquals("round semimonth-2 failed",
                dateParser.parse("November 16, 2001"),
                DateUtils.round(date2, DateUtils.SEMI_MONTH));
        
        
        assertEquals("round date-1 failed",
                dateParser.parse("February 13, 2002"),
                DateUtils.round(date1, Calendar.DATE));
        assertEquals("round date-2 failed",
                dateParser.parse("November 18, 2001"),
                DateUtils.round(date2, Calendar.DATE));
        assertEquals("round hour-1 failed",
                dateTimeParser.parse("February 12, 2002 13:00:00.000"),
                DateUtils.round(date1, Calendar.HOUR));
        assertEquals("round hour-2 failed",
                dateTimeParser.parse("November 18, 2001 1:00:00.000"),
                DateUtils.round(date2, Calendar.HOUR));
        assertEquals("round minute-1 failed",
                dateTimeParser.parse("February 12, 2002 12:35:00.000"),
                DateUtils.round(date1, Calendar.MINUTE));
        assertEquals("round minute-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:00.000"),
                DateUtils.round(date2, Calendar.MINUTE));
        assertEquals("round second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:57.000"),
                DateUtils.round(date1, Calendar.SECOND));
        assertEquals("round second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.round(date2, Calendar.SECOND));
        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.round(dateAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 4, 2002 00:00:00.000"),
                DateUtils.round(dateAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.round(dateAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 4, 2002 12:00:00.000"),
                DateUtils.round(dateAmPm4, Calendar.AM_PM));

        // tests for public static Date round(Object date, int field)
        assertEquals("round year-1 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.round((Object) date1, Calendar.YEAR));
        assertEquals("round year-2 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.round((Object) date2, Calendar.YEAR));
        assertEquals("round month-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.round((Object) date1, Calendar.MONTH));
        assertEquals("round month-2 failed",
                dateParser.parse("December 1, 2001"),
                DateUtils.round((Object) date2, Calendar.MONTH));
        assertEquals("round semimonth-1 failed",
                dateParser.parse("February 16, 2002"),
                DateUtils.round((Object) date1, DateUtils.SEMI_MONTH));
        assertEquals("round semimonth-2 failed",
                dateParser.parse("November 16, 2001"),
                DateUtils.round((Object) date2, DateUtils.SEMI_MONTH));
        assertEquals("round date-1 failed",
                dateParser.parse("February 13, 2002"),
                DateUtils.round((Object) date1, Calendar.DATE));
        assertEquals("round date-2 failed",
                dateParser.parse("November 18, 2001"),
                DateUtils.round((Object) date2, Calendar.DATE));
        assertEquals("round hour-1 failed",
                dateTimeParser.parse("February 12, 2002 13:00:00.000"),
                DateUtils.round((Object) date1, Calendar.HOUR));
        assertEquals("round hour-2 failed",
                dateTimeParser.parse("November 18, 2001 1:00:00.000"),
                DateUtils.round((Object) date2, Calendar.HOUR));
        assertEquals("round minute-1 failed",
                dateTimeParser.parse("February 12, 2002 12:35:00.000"),
                DateUtils.round((Object) date1, Calendar.MINUTE));
        assertEquals("round minute-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:00.000"),
                DateUtils.round((Object) date2, Calendar.MINUTE));
        assertEquals("round second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:57.000"),
                DateUtils.round((Object) date1, Calendar.SECOND));
        assertEquals("round second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.round((Object) date2, Calendar.SECOND));
        assertEquals("round calendar second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:57.000"),
                DateUtils.round((Object) cal1, Calendar.SECOND));
        assertEquals("round calendar second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.round((Object) cal2, Calendar.SECOND));
        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.round((Object) dateAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 4, 2002 00:00:00.000"),
                DateUtils.round((Object) dateAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.round((Object) dateAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 4, 2002 12:00:00.000"),
                DateUtils.round((Object) dateAmPm4, Calendar.AM_PM));

        try {
            DateUtils.round((Date) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.round((Calendar) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.round((Object) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.round("", Calendar.SECOND);
            fail();
        } catch (ClassCastException ex) {}
        try {
            DateUtils.round(date1, -9999);
            fail();
        } catch(IllegalArgumentException ex) {}

        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.round((Object) calAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 4, 2002 00:00:00.000"),
                DateUtils.round((Object) calAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.round((Object) calAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 4, 2002 12:00:00.000"),
                DateUtils.round((Object) calAmPm4, Calendar.AM_PM));
        
        // Fix for http://issues.apache.org/bugzilla/show_bug.cgi?id=25560
        // Test rounding across the beginning of daylight saving time
        TimeZone.setDefault(zone);
        dateTimeParser.setTimeZone(zone);
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round(date4, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round((Object) cal4, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round(date5, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round((Object) cal5, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round(date6, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round((Object) cal6, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round(date7, Calendar.DATE));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.round((Object) cal7, Calendar.DATE));
        
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 01:00:00.000"),
                DateUtils.round(date4, Calendar.HOUR_OF_DAY));
        assertEquals("round MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 01:00:00.000"),
                DateUtils.round((Object) cal4, Calendar.HOUR_OF_DAY));
        if (SystemUtils.isJavaVersionAtLeast(1.4f)) {
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 03:00:00.000"),
                    DateUtils.round(date5, Calendar.HOUR_OF_DAY));
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 03:00:00.000"),
                    DateUtils.round((Object) cal5, Calendar.HOUR_OF_DAY));
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 03:00:00.000"),
                    DateUtils.round(date6, Calendar.HOUR_OF_DAY));
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 03:00:00.000"),
                    DateUtils.round((Object) cal6, Calendar.HOUR_OF_DAY));
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 04:00:00.000"),
                    DateUtils.round(date7, Calendar.HOUR_OF_DAY));
            assertEquals("round MET date across DST change-over",
                    dateTimeParser.parse("March 30, 2003 04:00:00.000"),
                    DateUtils.round((Object) cal7, Calendar.HOUR_OF_DAY));
        } else {
            this.warn("WARNING: Some date rounding tests not run since the current version is " + SystemUtils.JAVA_VERSION);
        }
        TimeZone.setDefault(defaultZone);
        dateTimeParser.setTimeZone(defaultZone);
    }

    /**
     * Tests the Changes Made by LANG-346 to the DateUtils.modify() private method invoked
     * by DateUtils.round().
     */
    public void testRoundLang346() throws Exception
    {
        TimeZone.setDefault(defaultZone);
        dateTimeParser.setTimeZone(defaultZone);
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.set(2007, 6, 2, 8, 8, 50);
        Date date = testCalendar.getTime();
        assertEquals("Minute Round Up Failed",
                     dateTimeParser.parse("July 2, 2007 08:09:00.000"),
                     DateUtils.round(date, Calendar.MINUTE));

        testCalendar.set(2007, 6, 2, 8, 8, 20);
        date = testCalendar.getTime();
        assertEquals("Minute No Round Failed",
                     dateTimeParser.parse("July 2, 2007 08:08:00.000"),
                     DateUtils.round(date, Calendar.MINUTE));

        testCalendar.set(2007, 6, 2, 8, 8, 50);
        testCalendar.set(Calendar.MILLISECOND, 600);
        date = testCalendar.getTime();

        assertEquals("Second Round Up with 600 Milli Seconds Failed",
                     dateTimeParser.parse("July 2, 2007 08:08:51.000"),
                     DateUtils.round(date, Calendar.SECOND));

        testCalendar.set(2007, 6, 2, 8, 8, 50);
        testCalendar.set(Calendar.MILLISECOND, 200);
        date = testCalendar.getTime();
        assertEquals("Second Round Down with 200 Milli Seconds Failed",
                     dateTimeParser.parse("July 2, 2007 08:08:50.000"),
                     DateUtils.round(date, Calendar.SECOND));

        testCalendar.set(2007, 6, 2, 8, 8, 20);
        testCalendar.set(Calendar.MILLISECOND, 600);
        date = testCalendar.getTime();
        assertEquals("Second Round Up with 200 Milli Seconds Failed",
                     dateTimeParser.parse("July 2, 2007 08:08:21.000"),
                     DateUtils.round(date, Calendar.SECOND));

        testCalendar.set(2007, 6, 2, 8, 8, 20);
        testCalendar.set(Calendar.MILLISECOND, 200);
        date = testCalendar.getTime();
        assertEquals("Second Round Down with 200 Milli Seconds Failed",
                     dateTimeParser.parse("July 2, 2007 08:08:20.000"),
                     DateUtils.round(date, Calendar.SECOND));

        testCalendar.set(2007, 6, 2, 8, 8, 50);
        date = testCalendar.getTime();
        assertEquals("Hour Round Down Failed",
                     dateTimeParser.parse("July 2, 2007 08:00:00.000"),
                     DateUtils.round(date, Calendar.HOUR));

        testCalendar.set(2007, 6, 2, 8, 31, 50);
        date = testCalendar.getTime();
        assertEquals("Hour Round Up Failed",
                     dateTimeParser.parse("July 2, 2007 09:00:00.000"),
                     DateUtils.round(date, Calendar.HOUR));
    }

    /**
     * Tests various values with the trunc method
     */
    public void testTruncate() throws Exception {
        // tests public static Date truncate(Date date, int field)
        assertEquals("truncate year-1 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.truncate(date1, Calendar.YEAR));
        assertEquals("truncate year-2 failed",
                dateParser.parse("January 1, 2001"),
                DateUtils.truncate(date2, Calendar.YEAR));
        assertEquals("truncate month-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.truncate(date1, Calendar.MONTH));
        assertEquals("truncate month-2 failed",
                dateParser.parse("November 1, 2001"),
                DateUtils.truncate(date2, Calendar.MONTH));
        assertEquals("truncate semimonth-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.truncate(date1, DateUtils.SEMI_MONTH));
        assertEquals("truncate semimonth-2 failed",
                dateParser.parse("November 16, 2001"),
                DateUtils.truncate(date2, DateUtils.SEMI_MONTH));
        assertEquals("truncate date-1 failed",
                dateParser.parse("February 12, 2002"),
                DateUtils.truncate(date1, Calendar.DATE));
        assertEquals("truncate date-2 failed",
                dateParser.parse("November 18, 2001"),
                DateUtils.truncate(date2, Calendar.DATE));
        assertEquals("truncate hour-1 failed",
                dateTimeParser.parse("February 12, 2002 12:00:00.000"),
                DateUtils.truncate(date1, Calendar.HOUR));
        assertEquals("truncate hour-2 failed",
                dateTimeParser.parse("November 18, 2001 1:00:00.000"),
                DateUtils.truncate(date2, Calendar.HOUR));
        assertEquals("truncate minute-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:00.000"),
                DateUtils.truncate(date1, Calendar.MINUTE));
        assertEquals("truncate minute-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:00.000"),
                DateUtils.truncate(date2, Calendar.MINUTE));
        assertEquals("truncate second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:56.000"),
                DateUtils.truncate(date1, Calendar.SECOND));
        assertEquals("truncate second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.truncate(date2, Calendar.SECOND));
        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate(dateAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate(dateAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate(dateAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate(dateAmPm4, Calendar.AM_PM));

        // tests public static Date truncate(Object date, int field)
        assertEquals("truncate year-1 failed",
                dateParser.parse("January 1, 2002"),
                DateUtils.truncate((Object) date1, Calendar.YEAR));
        assertEquals("truncate year-2 failed",
                dateParser.parse("January 1, 2001"),
                DateUtils.truncate((Object) date2, Calendar.YEAR));
        assertEquals("truncate month-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.truncate((Object) date1, Calendar.MONTH));
        assertEquals("truncate month-2 failed",
                dateParser.parse("November 1, 2001"),
                DateUtils.truncate((Object) date2, Calendar.MONTH));
        assertEquals("truncate semimonth-1 failed",
                dateParser.parse("February 1, 2002"),
                DateUtils.truncate((Object) date1, DateUtils.SEMI_MONTH));
        assertEquals("truncate semimonth-2 failed",
                dateParser.parse("November 16, 2001"),
                DateUtils.truncate((Object) date2, DateUtils.SEMI_MONTH));
        assertEquals("truncate date-1 failed",
                dateParser.parse("February 12, 2002"),
                DateUtils.truncate((Object) date1, Calendar.DATE));
        assertEquals("truncate date-2 failed",
                dateParser.parse("November 18, 2001"),
                DateUtils.truncate((Object) date2, Calendar.DATE));
        assertEquals("truncate hour-1 failed",
                dateTimeParser.parse("February 12, 2002 12:00:00.000"),
                DateUtils.truncate((Object) date1, Calendar.HOUR));
        assertEquals("truncate hour-2 failed",
                dateTimeParser.parse("November 18, 2001 1:00:00.000"),
                DateUtils.truncate((Object) date2, Calendar.HOUR));
        assertEquals("truncate minute-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:00.000"),
                DateUtils.truncate((Object) date1, Calendar.MINUTE));
        assertEquals("truncate minute-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:00.000"),
                DateUtils.truncate((Object) date2, Calendar.MINUTE));
        assertEquals("truncate second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:56.000"),
                DateUtils.truncate((Object) date1, Calendar.SECOND));
        assertEquals("truncate second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.truncate((Object) date2, Calendar.SECOND));
        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate((Object) dateAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate((Object) dateAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate((Object) dateAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate((Object) dateAmPm4, Calendar.AM_PM));
        
        assertEquals("truncate calendar second-1 failed",
                dateTimeParser.parse("February 12, 2002 12:34:56.000"),
                DateUtils.truncate((Object) cal1, Calendar.SECOND));
        assertEquals("truncate calendar second-2 failed",
                dateTimeParser.parse("November 18, 2001 1:23:11.000"),
                DateUtils.truncate((Object) cal2, Calendar.SECOND));
        
        assertEquals("truncate ampm-1 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate((Object) calAmPm1, Calendar.AM_PM));
        assertEquals("truncate ampm-2 failed",
                dateTimeParser.parse("February 3, 2002 00:00:00.000"),
                DateUtils.truncate((Object) calAmPm2, Calendar.AM_PM));
        assertEquals("truncate ampm-3 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate((Object) calAmPm3, Calendar.AM_PM));
        assertEquals("truncate ampm-4 failed",
                dateTimeParser.parse("February 3, 2002 12:00:00.000"),
                DateUtils.truncate((Object) calAmPm4, Calendar.AM_PM));
        
        try {
            DateUtils.truncate((Date) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.truncate((Calendar) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.truncate((Object) null, Calendar.SECOND);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.truncate("", Calendar.SECOND);
            fail();
        } catch (ClassCastException ex) {}

        // Fix for http://issues.apache.org/bugzilla/show_bug.cgi?id=25560
        // Test truncate across beginning of daylight saving time
        TimeZone.setDefault(zone);
        dateTimeParser.setTimeZone(zone);
        assertEquals("truncate MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.truncate(date3, Calendar.DATE));
        assertEquals("truncate MET date across DST change-over",
                dateTimeParser.parse("March 30, 2003 00:00:00.000"),
                DateUtils.truncate((Object) cal3, Calendar.DATE));
        // Test truncate across end of daylight saving time
        assertEquals("truncate MET date across DST change-over",
                dateTimeParser.parse("October 26, 2003 00:00:00.000"),
                DateUtils.truncate(date8, Calendar.DATE));
        assertEquals("truncate MET date across DST change-over",
                dateTimeParser.parse("October 26, 2003 00:00:00.000"),
                DateUtils.truncate((Object) cal8, Calendar.DATE));
        TimeZone.setDefault(defaultZone);
        dateTimeParser.setTimeZone(defaultZone);
        
        // Bug 31395, large dates
        Date endOfTime = new Date(Long.MAX_VALUE); // fyi: Sun Aug 17 07:12:55 CET 292278994 -- 807 millis
        GregorianCalendar endCal = new GregorianCalendar();
        endCal.setTime(endOfTime);
        try {
            DateUtils.truncate(endCal, Calendar.DATE);
            fail();
        } catch (ArithmeticException ex) {}
        endCal.set(Calendar.YEAR, 280000001);
        try {
            DateUtils.truncate(endCal, Calendar.DATE);
            fail();
        } catch (ArithmeticException ex) {}
        endCal.set(Calendar.YEAR, 280000000);
        Calendar cal = DateUtils.truncate(endCal, Calendar.DATE);
        assertEquals(0, cal.get(Calendar.HOUR));
    }

    /**
     * Tests for LANG-59
     *
     * see http://issues.apache.org/jira/browse/LANG-59
     */
    public void testTruncateLang59() throws Exception {
        if (!SystemUtils.isJavaVersionAtLeast(1.4f)) {
            this.warn("WARNING: Test for LANG-59 not run since the current version is " + SystemUtils.JAVA_VERSION);
            return;
        }

        // Set TimeZone to Mountain Time
        TimeZone MST_MDT = TimeZone.getTimeZone("MST7MDT");
        TimeZone.setDefault(MST_MDT);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        format.setTimeZone(MST_MDT);

        Date oct31_01MDT = new Date(1099206000000L); 

        Date oct31MDT             = new Date(oct31_01MDT.getTime()       - 3600000L); // - 1 hour
        Date oct31_01_02MDT       = new Date(oct31_01MDT.getTime()       + 120000L);  // + 2 minutes
        Date oct31_01_02_03MDT    = new Date(oct31_01_02MDT.getTime()    + 3000L);    // + 3 seconds
        Date oct31_01_02_03_04MDT = new Date(oct31_01_02_03MDT.getTime() + 4L);       // + 4 milliseconds

        assertEquals("Check 00:00:00.000", "2004-10-31 00:00:00.000 MDT", format.format(oct31MDT));
        assertEquals("Check 01:00:00.000", "2004-10-31 01:00:00.000 MDT", format.format(oct31_01MDT));
        assertEquals("Check 01:02:00.000", "2004-10-31 01:02:00.000 MDT", format.format(oct31_01_02MDT));
        assertEquals("Check 01:02:03.000", "2004-10-31 01:02:03.000 MDT", format.format(oct31_01_02_03MDT));
        assertEquals("Check 01:02:03.004", "2004-10-31 01:02:03.004 MDT", format.format(oct31_01_02_03_04MDT));

        // ------- Demonstrate Problem -------
        Calendar gval = Calendar.getInstance();
        gval.setTime(new Date(oct31_01MDT.getTime()));
        gval.set(Calendar.MINUTE, gval.get(Calendar.MINUTE)); // set minutes to the same value
        assertEquals("Demonstrate Problem", gval.getTime().getTime(), oct31_01MDT.getTime() + 3600000L);

        // ---------- Test Truncate ----------
        assertEquals("Truncate Calendar.MILLISECOND",
                oct31_01_02_03_04MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.MILLISECOND));

        assertEquals("Truncate Calendar.SECOND",
                   oct31_01_02_03MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.SECOND));

        assertEquals("Truncate Calendar.MINUTE",
                      oct31_01_02MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.MINUTE));

        assertEquals("Truncate Calendar.HOUR_OF_DAY",
                         oct31_01MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.HOUR_OF_DAY));

        assertEquals("Truncate Calendar.HOUR",
                         oct31_01MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.HOUR));

        assertEquals("Truncate Calendar.DATE",
                            oct31MDT, DateUtils.truncate(oct31_01_02_03_04MDT, Calendar.DATE));


        // ---------- Test Round (down) ----------
        assertEquals("Round Calendar.MILLISECOND",
                oct31_01_02_03_04MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.MILLISECOND));

        assertEquals("Round Calendar.SECOND",
                   oct31_01_02_03MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.SECOND));

        assertEquals("Round Calendar.MINUTE",
                      oct31_01_02MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.MINUTE));

        assertEquals("Round Calendar.HOUR_OF_DAY",
                         oct31_01MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.HOUR_OF_DAY));

        assertEquals("Round Calendar.HOUR",
                         oct31_01MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.HOUR));

        assertEquals("Round Calendar.DATE",
                            oct31MDT, DateUtils.round(oct31_01_02_03_04MDT, Calendar.DATE));

        // restore default time zone
        TimeZone.setDefault(defaultZone);
    }

    /**
     * Tests the iterator exceptions
     */
    public void testIteratorEx() throws Exception {
        try {
            DateUtils.iterator(Calendar.getInstance(), -9999);
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.iterator((Date) null, DateUtils.RANGE_WEEK_CENTER);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.iterator((Calendar) null, DateUtils.RANGE_WEEK_CENTER);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.iterator((Object) null, DateUtils.RANGE_WEEK_CENTER);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateUtils.iterator("", DateUtils.RANGE_WEEK_CENTER);
            fail();
        } catch (ClassCastException ex) {}
    }

    /**
     * Tests the calendar iterator for week ranges
     */
    public void testWeekIterator() throws Exception {
        Calendar now = Calendar.getInstance();
        for (int i = 0; i< 7; i++) {
            Calendar today = DateUtils.truncate(now, Calendar.DATE);
            Calendar sunday = DateUtils.truncate(now, Calendar.DATE);
            sunday.add(Calendar.DATE, 1 - sunday.get(Calendar.DAY_OF_WEEK));
            Calendar monday = DateUtils.truncate(now, Calendar.DATE);
            if (monday.get(Calendar.DAY_OF_WEEK) == 1) {
                //This is sunday... roll back 6 days
                monday.add(Calendar.DATE, -6);
            } else {
                monday.add(Calendar.DATE, 2 - monday.get(Calendar.DAY_OF_WEEK));
            }
            Calendar centered = DateUtils.truncate(now, Calendar.DATE);
            centered.add(Calendar.DATE, -3);
            
            Iterator it = DateUtils.iterator(now, DateUtils.RANGE_WEEK_SUNDAY);
            assertWeekIterator(it, sunday);
            it = DateUtils.iterator(now, DateUtils.RANGE_WEEK_MONDAY);
            assertWeekIterator(it, monday);
            it = DateUtils.iterator(now, DateUtils.RANGE_WEEK_RELATIVE);
            assertWeekIterator(it, today);
            it = DateUtils.iterator(now, DateUtils.RANGE_WEEK_CENTER);
            assertWeekIterator(it, centered);
            
            it = DateUtils.iterator((Object) now, DateUtils.RANGE_WEEK_CENTER);
            assertWeekIterator(it, centered);
            it = DateUtils.iterator((Object) now.getTime(), DateUtils.RANGE_WEEK_CENTER);
            assertWeekIterator(it, centered);
            try {
                it.next();
                fail();
            } catch (NoSuchElementException ex) {}
            it = DateUtils.iterator(now, DateUtils.RANGE_WEEK_CENTER);
            it.next();
            try {
                it.remove();
            } catch( UnsupportedOperationException ex) {}
            
            now.add(Calendar.DATE,1);
        }
    }
            
    /**
     * Tests the calendar iterator for month-based ranges
     */
    public void testMonthIterator() throws Exception {
        Iterator it = DateUtils.iterator(date1, DateUtils.RANGE_MONTH_SUNDAY);
        assertWeekIterator(it,
                dateParser.parse("January 27, 2002"),
                dateParser.parse("March 2, 2002"));

        it = DateUtils.iterator(date1, DateUtils.RANGE_MONTH_MONDAY);
        assertWeekIterator(it,
                dateParser.parse("January 28, 2002"),
                dateParser.parse("March 3, 2002"));

        it = DateUtils.iterator(date2, DateUtils.RANGE_MONTH_SUNDAY);
        assertWeekIterator(it,
                dateParser.parse("October 28, 2001"),
                dateParser.parse("December 1, 2001"));

        it = DateUtils.iterator(date2, DateUtils.RANGE_MONTH_MONDAY);
        assertWeekIterator(it,
                dateParser.parse("October 29, 2001"),
                dateParser.parse("December 2, 2001"));
    }

    /**
     * This checks that this is a 7 element iterator of Calendar objects
     * that are dates (no time), and exactly 1 day spaced after each other.
     */
    private static void assertWeekIterator(Iterator it, Calendar start) {
        Calendar end = (Calendar) start.clone();
        end.add(Calendar.DATE, 6);

        assertWeekIterator(it, start, end);
    }

    /**
     * Convenience method for when working with Date objects
     */
    private static void assertWeekIterator(Iterator it, Date start, Date end) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(start);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);

        assertWeekIterator(it, calStart, calEnd);
    }

    /**
     * This checks that this is a 7 divisble iterator of Calendar objects
     * that are dates (no time), and exactly 1 day spaced after each other
     * (in addition to the proper start and stop dates)
     */
    private static void assertWeekIterator(Iterator it, Calendar start, Calendar end) {
        Calendar cal = (Calendar) it.next();
        assertEquals("", start, cal, 0);
        Calendar last = null;
        int count = 1;
        while (it.hasNext()) {
            //Check this is just a date (no time component)
            assertEquals("", cal, DateUtils.truncate(cal, Calendar.DATE), 0);

            last = cal;
            cal = (Calendar) it.next();
            count++;

            //Check that this is one day more than the last date
            last.add(Calendar.DATE, 1);
            assertEquals("", last, cal, 0);
        }
        if (count % 7 != 0) {
            throw new AssertionFailedError("There were " + count + " days in this iterator");
        }
        assertEquals("", end, cal, 0);
    }

    /**
     * Used to check that Calendar objects are close enough
     * delta is in milliseconds
     */
    private static void assertEquals(String message, Calendar cal1, Calendar cal2, long delta) {
        if (Math.abs(cal1.getTime().getTime() - cal2.getTime().getTime()) > delta) {
            throw new AssertionFailedError(
                    message + " expected " + cal1.getTime() + " but got " + cal2.getTime());
        }
    }

    void warn(String msg) {
        System.err.println(msg);
    }
}

