package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;

import static org.junit.Assert.*;


/**
 * @author yunfy
 * @create 2018-1-5 23:22
 **/
public class CustomNumberEditorTest {

    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);

        editor.setAsText("3");
        //转换
        Object value = editor.getValue();
        assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer) editor.getValue()).intValue());


        editor.setAsText("");
        assertNull(editor.getValue());


        try {
            editor.setAsText("3.1");

        } catch (IllegalArgumentException e) {
            return;
        }
        fail();

    }

}
