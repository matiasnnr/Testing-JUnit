package utils;

public class FizzBuzzUtil {

    public static String fizzBuzz(int number){
        if (number % 3 == 0 && number % 5 == 0){
            return"FizzBuzz";
        }
        else if (number % 3 == 0){
            return"Fizz";
        } else if (number % 5 == 0){
            return"Buzz";
        }
        return String.valueOf(number);
    }

}
