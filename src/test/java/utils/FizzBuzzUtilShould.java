package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class FizzBuzzUtilShould {

    /*
     * Si el número es divisible por 3, retorna “Fizz”
     * Si el número es divisible por 5, retorna “Buzz”
     * Si el número es divisible por 3 y por 5, retorna “FizzBuzz”
     * En otro caso, retorna el mismo número
     * */

    @Test
    public void return_fizz_when_divisible_by_three(){
        assertEquals("Fizz", FizzBuzzUtil.fizzBuzz(6));
    }

    @Test
    public void return_buzz_when_divisible_by_five(){
        assertEquals("Buzz", FizzBuzzUtil.fizzBuzz(10));
    }

    @Test
    public void return_fizzbuzz_when_divisible_by_three_and_five(){
        assertEquals("FizzBuzz", FizzBuzzUtil.fizzBuzz(15));
    }

    @Test
    public void return_the_same_number_when_not_divisible_by_three_and_five(){
        assertEquals("2", FizzBuzzUtil.fizzBuzz(2));
    }
}