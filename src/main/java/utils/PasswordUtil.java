package utils;

public class PasswordUtil {

    public enum SecutiryLevel {
        WEAK, MEDIUM, STRONG
    }

    public static SecutiryLevel assessPassword(String password){

        if (password.length() < 8){
            return SecutiryLevel.WEAK;
        }

        if (password.matches("[a-zA-Z]+")) {
            //esto indica que mi password contiene letras de la a a la z y se pueden repetir debido al signo +
            return SecutiryLevel.WEAK;
        }

        if (password.matches("[a-zA-Z0-9]+")){
            return SecutiryLevel.MEDIUM;
        }

        return SecutiryLevel.STRONG;
    }
}

        /*
         * Expresiones regulares
         * ^                   start-of-string
         * ([0-9])             a digit must occur at least once
         * ([a-z])             a lower case letter must occur at least once
         * ([A-Z])             an upper case letter must occur at least once
         * ([@#$%^&+=])    a special character must occur at least once
         * (?=\S+$)            no whitespace allowed in the entire string
         * .{8,}               anything, at least eight places though
         * $                   end of string
         * +                   can be repeated several times
         * */