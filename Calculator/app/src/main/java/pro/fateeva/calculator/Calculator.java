package pro.fateeva.calculator;

import android.util.Log;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator implements Serializable {

    private String rawInput = "";
    private String textForView = "";
    private Operation operation = null;
    private boolean isNegative = false;

    private double number1 = 0;
    private double number2 = 0;
    private double result = 0;

    public void clickOnButton(int number){
        rawInput = rawInput + number;
        textForView = textForView + number;
    }

    public void clickOnButtonDot() {
        textForView = textForView + getDecimalSeparator();
        rawInput = rawInput + getDecimalSeparator();
    }

    public char getDecimalSeparator() {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        return symbols.getDecimalSeparator();
    }

    public void clickOnButtonPlus() {
        textForView = textForView + " + ";
        operation = Operation.PLUS;
        clickOnOperationButton();
    }

    public void clickOnButtonMinus() {
        if (!rawInput.equals("")) {
            operation = Operation.MINUS;
            clickOnOperationButton();
        }
        isNegative = true;
        textForView = textForView + " - ";
    }

    public void clickOnButtonDivision() {
        textForView = textForView + " / ";
        operation = Operation.DIVISION;
        clickOnOperationButton();
    }

    public void clickOnButtonMultiplication() {
        textForView = textForView + " * ";
        operation = Operation.MULTIPLICATION;
        clickOnOperationButton();
    }

    public void clickOnButtonPercent() {
        operation = Operation.PERCENT;
        clickOnOperationButton();
        clickOnEqual();
    }

    public void clickOnButtonDeleteAll() {
        isNegative = false;
        textForView = "";
        rawInput = "";
        number1 = 0;
        operation = null;
        result = 0;
    }

    public void clickOnButtonDeleteLast() {
        textForView = textForView.substring(0, textForView.length() - 1);
        rawInput = textForView;
    }

    public void clickOnEqual() {
        boolean isDivisionByZero = false;
        number2 = 0;

        if (operation == Operation.PERCENT) {
            result = number1 / 100;
        } else {
            number2 = parseNumber(rawInput);

            switch (operation) {
                case PLUS:
                    result = number1 + number2;
                    break;
                case MINUS:
                    result = number1 - number2;
                    break;
                case DIVISION:
                    if (number2 == 0) {
                        isDivisionByZero = true;
                        result = 0;
                        break;
                    }
                    result = number1 / number2;
                    break;
                case MULTIPLICATION:
                    result = number1 * number2;
                    break;
            }
        }
        viewResult(isDivisionByZero);
    }

    private void clickOnOperationButton() {
        if (number1 == 0) {
            number1 = parseNumber(rawInput);
            if (isNegative) {
                number1 = number1 * -1;
                isNegative = false;
            }
        }
        rawInput = "";
    }

    private Double parseNumber(String textNumber) {
        try {
            return NumberFormat.getInstance().parse(textNumber).doubleValue();
        } catch (Exception exc) {
            Log.e(null, exc.toString());
            return Double.POSITIVE_INFINITY;
        }
    }

    private void viewResult(boolean isDivisionByZero) {
        if (!isDivisionByZero){
            textForView = String.format(Locale.getDefault(), "%f", result);
        } else {
            textForView = "Деление на НОЛЬ!";
        }
        number1 = result;
    }

    public String getTextForView() {
        return textForView;
    }

    public void setTextForView(String textForView) {
        this.textForView = textForView;
    }

    public String getRawInput() {
        return rawInput;
    }

    public void setRawInput(String rawInput) {
        this.rawInput = rawInput;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public boolean getIsNegative() {
        return isNegative;
    }

    public void setIsNegative(boolean negative) {
        isNegative = negative;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public enum Operation {
        PLUS, MINUS, PERCENT, DIVISION, MULTIPLICATION
    }
}
