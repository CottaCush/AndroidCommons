package com.cottacush.android.libraries;

import android.content.Context;

import com.cottacush.android.libraries.utils.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rasheed on 11/8/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestTextUtils {
    @Mock
    Context mMockContext;

    @Test
    public void checkAsteriskPhoneNumber() throws Exception {
        String expected = "081******545";
        String actual = TextUtils.asteriskPhoneNumber("081542698545", 3, 9);
        assertEquals(expected , actual);
    }

    @Test
    public void checkAddNairaToText() throws Exception {
        String expected = "₦200";
        String actual = TextUtils.addNairaToText("200");
        assertEquals(expected, actual);
    }

    @Test
    public void checkFormatTextToNaira() throws Exception {

    }

    @Test
    public void checkFormatTextToMoney() throws Exception {

    }

    @Test
    public void checkSubtract() throws Exception {

    }

    @Test
    public void checkIsEmailValid() throws Exception {

    }

    @Test
    public void checkCheckFields() throws Exception {

    }

    @Test
    public void checkIsValidPhoneNumber() throws Exception {
        String phoneNumber = "08168686869";
        assertEquals(true, TextUtils.isValidPhoneNumber(phoneNumber));
    }

    @Test
    public void subtract_is_correct() throws Exception {
        assertEquals(2.0, TextUtils.subtract("4", "2"), 0.1);
    }
}
