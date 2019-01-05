package org.litespring.test.v2;

import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomBooleanEditor;

import static org.junit.Assert.*;

/**
 * @author yunfy
 * @create 2018-1-5 23:22
 **/
public class CustomBooleanEditorTest {
    @Test
    public void testConvertStringToBoolean() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("true");
        assertTrue((Boolean) editor.getValue());
        editor.setAsText("false");
        assertFalse((Boolean) editor.getValue());

        editor.setAsText("on");
        assertTrue((Boolean) editor.getValue());
        editor.setAsText("off");
        assertFalse((Boolean) editor.getValue());


        editor.setAsText("yes");
        assertTrue((Boolean) editor.getValue());
        editor.setAsText("no");
        assertFalse((Boolean) editor.getValue());


        try {
            editor.setAsText("aabbcc");
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();


    }

}
