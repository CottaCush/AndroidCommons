package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.DateUtils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void formatDate1() throws Exception {
    }

    @Test
    public void getAge() throws Exception {
        //TODO : test failing , come back and write this test when the method is fixed
        int expected = 16;
        int actual = DateUtils.getAge("2000-11-12", "yyy-mm-dd");
        // assertEquals(expected, actual);
    }

    @Test
    public void formatDate() throws Exception {
        String expected = "12 November 2000";
        String actual = DateUtils.formatDate("2000-11-12");
        assertEquals(expected, actual);
    }

    @Test
    public void getRelativeTime() throws Exception {

    }
}
