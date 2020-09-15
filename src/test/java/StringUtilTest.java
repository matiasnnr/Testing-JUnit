import com.sun.deploy.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    //Estos son test sin JUnit
    @Test
    public void testRepeat(){

        //Con JUnit esta función existe por defecto y el orden es inverso (expected, actual)
        Assert.assertEquals("holahola", StringUtil.repeat("hola", 3));

        //Sin JUnit uso la función creada anteriormente
        assertEquals(StringUtil.repeat("hola", 1), "hola");

    }

    private static void assertEquals(String actual, String expected) {
        if (!actual.equals(expected)){
            throw new RuntimeException(actual + " isn't equal to expected " + expected);
        }
    }

}
