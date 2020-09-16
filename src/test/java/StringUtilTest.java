import org.junit.Assert;
import org.junit.Test;
import utils.StringUtil;
import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void repeat_string_once(){
        assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeat_string_multiple_times(){
        assertEquals("holaholahola", StringUtil.repeat("hola", 3));
    }

    @Test
    public void repeat_string_zero_times(){
        assertEquals("", StringUtil.repeat("hola", 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeat_string_negative_times(){
        StringUtil.repeat("hola", -1);
    }

    //Test String Empty
    @Test
    public void string_is_not_empty(){
        assertFalse(StringUtil.isEmpty("Matías"));
    }

    @Test
    public void string_is_empty(){
        assertTrue(StringUtil.isEmpty(""));
    }

    @Test
    public void null_is_empty_string(){
        assertTrue(StringUtil.isEmpty(null));
    }

    @Test
    public void space_is_empty_string(){
        assertTrue(StringUtil.isEmpty("   "));
    }
}
