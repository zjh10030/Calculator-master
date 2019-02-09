package com.example.calculator;

/**
 * Created by jeremy on 1/29/17.
 */

/**
 * Calculator model for app
 */
public class Calculator {
    /** What is currently displayed on the screen */
    private String display;

    /** Stored value used in calculation */
    private double register;

    /** The last operation entered */
    private char lastOperation;

    /** If the screen should be reset on the next digit press */
    private boolean resetOnNextDigitPress;

    /**
     * Initializes the calculator
     */
    public Calculator() {
        allClear();
    }

    /**
     * Clears the register and any computations in progress
     */
    public void allClear() {
        display = "0";
        register = 0;
        lastOperation = '=';
        resetOnNextDigitPress = true;
    }

    /**
     * Clears the current input only
     */
    public void clear() {
        display = "0";
    }

    /**
     * @return the String representation of the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * Sets display equal to a specific value. Note scope as private - this
     * helps maintain encapsulation
     *
     * @param value
     */
    private void setDisplay(double value) {
        display = Double.toString(value);
    }

    /**
     * Handles a key press on the calculator
     *
     * @param key
     */
    public void keyPress(char key) {
        switch (key) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                enterDigit(key);
                break;
            case '.':
                enterDecimal();
                break;
            case '+':
            case '-':
            case '*':
            case 'x':
            case '/':
            case '=':
                operation(key);
                break;
            default:
                break;

        }
    }

    /**
     * Enters a digit
     */
    private void enterDigit(char digit) {
        if (resetOnNextDigitPress) {
            display = Character.toString(digit);
            resetOnNextDigitPress = false;
        } else {
            display = display + digit;
        }
    }

    /**
     * Handles entering a decimal place
     */
    private void enterDecimal() {
        final char dot = '.';
        if (display.indexOf(dot) != -1) {
            // This was the second decimal press, do nothing
            return;
        } else {
            // Just append the decimal to the display
            display = display + dot;
        }
    }

    /**
     * Handles an operation
     * @param operation - the operation pressed
     */
    private void operation(char operation) {
        // First - perform whatever calculation the last operation asks for
        switch (lastOperation) {
            case '+':
                register = register + readDisplay();
                break;
            case '-':
                register = register - readDisplay();
                break;
            case '*':
            case 'x':
                register = register * readDisplay();
                break;
            case '/':
                register = register / readDisplay();
                break;
            case '=':
                register = readDisplay();
                break;
            default:
                // Something went wrong, just do nothing
                return;
        }

        // Set the display equal to the answer
        setDisplay(register);

        // The next digit press will reset the display
        resetOnNextDigitPress = true;

        // Now reset the value of the last operation to that input from the
        // current keypress
        lastOperation = operation;
    }

    /**
     * Reads the value from the display
     * @return the number on the display as a double
     */
    private double readDisplay() {
        return Double.parseDouble(display);
    }
}
