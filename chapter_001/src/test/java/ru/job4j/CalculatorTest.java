package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* CalculatorTest.
* @author ayuzyak.
* @version 1.
* @since 08.12.2016.
*/
public class CalculatorTest {

/**
* Check addition 1 + 1 = 2.
*/
    @Test
    public void whenAdditionOnePlusOneThenTwo() {
        Calculator calculator = new Calculator();
	    calculator.calculate("+", 1, 2);
	    assertThat(calculator.getResult(), is(2.0));
    }


/**
* Check subtraction 2 - 1 = 1.
*/
    @Test
    public void whenSubstactionTwoMinusOneThenOne() {
        Calculator calculator = new Calculator();
        calculator.calculate("-", 2, 1);
        assertThat(calculator.getResult(), is(1.0));
    }

/**
* Verify multiplication 2 * 1 = 2.
*/
    @Test
    public void whenMultiplicationTwoMultiplyOneThenTwo() {
        Calculator calculator = new Calculator();
        calculator.calculate("*", 2, 1);
        assertThat(calculator.getResult(), is(2.0));
   }

/**.
* Check the division 2 / 1 = 1
*/
   @Test
   public void whenTwoDivisitionOneThenOne() {
       Calculator calculator = new Calculator();
       calculator.calculate("/", 2, 1);
       assertThat(calculator.getResult(), is(1.0));
    }
}