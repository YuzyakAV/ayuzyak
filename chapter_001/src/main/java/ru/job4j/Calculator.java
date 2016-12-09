package ru.job4j;

/**
* Implementation matmaticheskih operations.
* @author ayuzyak.
* @version 1.
* @since 08.12.2016.
*/
public class Calculator {

/**
* Saving the result.
*/
    private double result;

/**
* The result of mathematical operations.
* @return result
*/
    public double getResult() {
        return this.result;
    }

/**
* the method of addition of the arguments.
* @param first argument.
* @param second argument.
*/
    public void add(double first, double second) {
        this.result = first + second;
    }

/**
* subtraction method arguments.
* @param first argument.
* @param second argument.
*/
    public void substract(double first, double second) {
        this.result = first - second;
    }

/**
* multiplication method arguments.
* @param first argument.
* @param second argument.
*/

    public void multiply(double first, double second) {
        this.result = first * second;
    }

/**
* the method of dividing the arguments.
* @param first argument.
* @param second argument.
*/
    public void div(double first, double second) {
        if (second != 0) {
            this.result = first / second;
        } else {
            throw new ArithmeticException("Itsn't possibly to divide by 0");
        }
    }

/**
* Partly zero result.
*/
    public void clear() {
        this.result = 0;
    }

/**
* the calculation of mathematical operations.
* @param operation possible operations + - * / ^.
* @param first first argument.
* @param second second argument.
*/
    public void calculate(String operation, double first, double second) {
        if ("+".equals(operation)) {
            this.add(first, second);
        } else if ("-".equals(operation)) {
            this.substract(first, second);
        } else if ("*".equals(operation)) {
            this.multiply(first, second);
        } else if ("/".equals(operation)) {
            this.div(first, second);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}