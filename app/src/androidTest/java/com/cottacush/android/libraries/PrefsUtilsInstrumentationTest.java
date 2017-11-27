package com.cottacush.android.libraries;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;
import com.cottacush.android.libraries.utils.PrefsUtils;
import com.cottacush.android.libraries.utils.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(AndroidJUnit4.class)
@SmallTest

public class PrefsUtilsInstrumentationTest {
    private final String prefName = "TEST_PREF";
    PrefsUtils prefsUtils;

    @Before
    public void setUp() throws Exception {
        Context mContext = InstrumentationRegistry.getContext();
        prefsUtils = new PrefsUtils(mContext);
    }

    @Test
    public void testPutIntAndGetInt() throws Exception {
         int expected = 2;
         String key = "intKey";
         prefsUtils.putInt(key , expected);
         int actual = prefsUtils.getInt(key , 0);
         assertEquals(expected , actual);
    }

    @Test
    public  void testDoesContain(){
        int expected = 2;
        String key = "doesContainKey";
        prefsUtils.putInt(key , expected);
        assertEquals(true , prefsUtils.doesContain(key));
        prefsUtils.remove(key);
        assertEquals(false , prefsUtils.doesContain(key));
    }

    @Test
    public void testPutBooleanAndGetBoolean() throws Exception {
         boolean expected = true;
         String key = "booleanKey";
        prefsUtils.putBoolean(key , expected);
        boolean actual = prefsUtils.getBoolean(key , false);
        assertEquals(expected , actual);
    }

    @Test
    public void testPutFloatAndGetFloat() throws Exception {
         float expected = 5.0f;
        String key = "floatKey";
        prefsUtils.putFloat(key , expected);
        float actual = prefsUtils.getFloat(key , 0.0f);
        assertEquals(expected , actual , 0.000000000000000000000001);
    }

    @Test
    public void testPutStringSetAndGetStringSet() throws Exception {
        HashSet<String> expectedSet = new HashSet<>();
        expectedSet.add("test");
        String key = "stringSetKey";
        prefsUtils.putStringSet(key , expectedSet);
        Set<String> actualSet = prefsUtils.getStringSet(key , null);
        assertEquals(true ,  actualSet.contains("test"));
    }

    @Test
    public void testPutLongAndGetLong() throws Exception {
        long expected = 2L;
        String key = "longKey";
        prefsUtils.putLong(key , expected);
        long actual = prefsUtils.getLong(key , 0);
        assertEquals(expected , actual);
    }

    @Test
    public void doesContain() throws Exception {
        String key = "intKey";
        prefsUtils.putInt(key , 2);
        boolean actual = prefsUtils.doesContain(key);
        assertEquals(true, actual);
    }

    @Test
    public void testPutStringAndGetString(){
        String prefsKey = "key";
        String  expected = "value1";
        prefsUtils.putString(prefsKey,expected);
        String actual = prefsUtils.getString(prefsKey , "");
        assertEquals(expected , actual);
    }
}