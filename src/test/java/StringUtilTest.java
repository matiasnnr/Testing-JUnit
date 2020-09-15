import com.sun.deploy.util.StringUtils;

public class StringUtilTest {

    //Estos son test sin JUnit
    public static void main(String[] args){

        assertEquals(StringUtil.repeat("hola", 3), "holaholahola");

        String result2 = StringUtil.repeat("hola", 1);
        if (!result2.equals("hola")){
            throw new RuntimeException("ERROR");
        }

    }

    private static void assertEquals(String actual, String expected) {
        if (!actual.equals(expected)){
            throw new RuntimeException(actual + " isn't equal to expected " + expected);
        }
    }

}
