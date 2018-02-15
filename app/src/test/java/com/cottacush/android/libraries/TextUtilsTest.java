package com.cottacush.android.libraries;

import android.content.Context;

import com.cottacush.android.libraries.utils.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TextUtilsTest {
    @Mock
    Context mMockContext;

    @Test
    public void checkAsteriskPhoneNumber() throws Exception {
        String expected = "081******545";
        String actual = TextUtils.asteriskPhoneNumber("081542698545", 3, 9);
        assertEquals(expected, actual);
    }

    @Test
    public void checkAddNairaToText() throws Exception {
        String expected = "₦200";
        String actual = TextUtils.addNairaToText("200");
        assertEquals(expected, actual);
    }

    @Test
    public void checkFormatTextToNaira() throws Exception {
        String value = "200";
        String expected = "₦200.00";
        String actual = TextUtils.formatTextToNaira(value);
        assertEquals(actual, expected);
    }

    @Test
    public void checkFormatTextToMoney() throws Exception {
        String value = "1000000";
        String expected = "₦1M";
        String actual = TextUtils.formatTextToMoney(value);
        assertEquals(expected, actual);
        expected = "₦1K";
        actual = TextUtils.formatTextToMoney("1000");
        assertEquals(expected, actual);
    }

    @Test
    public void checkSubtract() throws Exception {
        assertEquals(2.0, TextUtils.subtract("4", "2"), 0.1);
    }

    @Test
    public void checkIsValidPhoneNumber() throws Exception {
        String phoneNumber = "08168686869";
        assertEquals(true, TextUtils.isValidPhoneNumber(phoneNumber));
    }
}
