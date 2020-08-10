package controller;

import model.ArithmeticOperation;
import model.RomanAlphabet;

/**
 * It is class of controller.
 */
public class Controller {

    private static final String ROMAN = "roman";
    private static final String ARABIC = "arabic";
    private String first;
    private String second;
    private String operation;

    /**
     * It is the empty constructor.
     */
    public Controller() {
    }

    /**
     * This class calculates the value of the expression in the Arabic or Roman alphabet.
     * @param currentArithmeticOperation
     * @return
     * @throws Exception
     */
    public String count(ArithmeticOperation currentArithmeticOperation) throws Exception {
        this.first = currentArithmeticOperation.getFirst();
        this.second = currentArithmeticOperation.getSecond();
        this.operation = currentArithmeticOperation.getOperation();

        String alphabetFirstNumber = defineAlphabet(first);
        String alphabetSecondNumber = defineAlphabet(second);
        if (!alphabetFirstNumber.equals(alphabetSecondNumber)) {
            throw new Exception("числа в разных алфавитах!");
        }
        if (alphabetFirstNumber == ROMAN) {
            first = convertToArabicAlphabet(first);
            second = convertToArabicAlphabet(second);
        }
        if (!checkOnInterval(first) || !checkOnInterval(second)) {
            throw new Exception("числа для вычисления должны быть в отрезке [1, 10]");
        }
        String result = calculating();
        if (alphabetFirstNumber == ROMAN) {
            result = convertToRomanAlphabet(result);
        }
        return result;
    }

    private String defineAlphabet(String currentNumber) throws Exception {
        if (isArabic(currentNumber)) {
            return ARABIC;
        }
        if (isRoman(currentNumber)) {
            return ROMAN;
        } else
            throw new Exception("введенное число не принадлежит ни арабскому, ни греческому алфавитам!");//ВОЗБУЖДАЕМ ИСКЛЮЧЕНИЕ
    }

    private boolean isArabic(String currentNumber) {
        try {
            Integer.parseInt(currentNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isRoman(String currentNumber) {
        for (Object currentSymbolRomanAlphabet : RomanAlphabet.values()) {
            if (currentNumber.equalsIgnoreCase(currentSymbolRomanAlphabet.toString())) {
                return true;
            }
        }
        return false;
    }

    private String convertToArabicAlphabet(String currentRomanNumber) {
        String outArabicNumber = "";
        for (RomanAlphabet currentSymbolRomanAlphabet : RomanAlphabet.values()) {
            if (currentRomanNumber.equals(currentSymbolRomanAlphabet.toString())) {
                outArabicNumber = String.valueOf(currentSymbolRomanAlphabet.getNumericAlphabet());
            }
        }
        return outArabicNumber;
    }

    private String convertToRomanAlphabet(String currentArabicNumber) {
        String outRomanNumber = "";
        int remains;
        int integerPartOfNumber;
        int currentArabicNumberTypeInt = Math.abs(Integer.parseInt(currentArabicNumber));
        if (Integer.parseInt(currentArabicNumber) < 0) {
            outRomanNumber += "-";
        }
        if (currentArabicNumberTypeInt == 0) {
            outRomanNumber += "невозможно отобразить в римском алфавите 0";
        }
        if (currentArabicNumberTypeInt > 10) {
            remains = currentArabicNumberTypeInt % 10;
            integerPartOfNumber = currentArabicNumberTypeInt / 10;

            if (integerPartOfNumber >= RomanAlphabet.L.getNumericAlphabet() / 10 && integerPartOfNumber < RomanAlphabet.C.getNumericAlphabet() / 10) {
                integerPartOfNumber = integerPartOfNumber - RomanAlphabet.L.getNumericAlphabet() / 10;
                outRomanNumber += RomanAlphabet.L.toString();
            }

            if (integerPartOfNumber < RomanAlphabet.L.getNumericAlphabet() / 10) {
                int i = 0;
                while (i < integerPartOfNumber) {
                    outRomanNumber += RomanAlphabet.X.toString();
                    i++;
                }
            }
            if (integerPartOfNumber >= RomanAlphabet.C.getNumericAlphabet() / 10) {
                outRomanNumber += RomanAlphabet.C.toString();
            }
            if (remains != 0) {
                outRomanNumber += createRomanNumber(String.valueOf(remains));
            }
        } else {
            outRomanNumber += createRomanNumber(String.valueOf(currentArabicNumberTypeInt));
        }
        return outRomanNumber;
    }

    private String createRomanNumber(String currentArabicNumber) {
        String outRomanNumber = "";
        for (RomanAlphabet currentSymbolRomanAlphabet : RomanAlphabet.values()) {
            if (currentArabicNumber.equals(String.valueOf(currentSymbolRomanAlphabet.getNumericAlphabet()))) {
                outRomanNumber = currentSymbolRomanAlphabet.toString();
            }
        }
        return outRomanNumber;
    }

    private boolean checkOnInterval(String currentNumber) {
        int number = Integer.parseInt(currentNumber);
        if (number >= 1 && number <= 10) {
            return true;
        }
        return false;
    }

    private String calculating() {
        int firstTypeInt = Integer.parseInt(first);
        int secondTypeInt = Integer.parseInt(second);
        int result = 0;
        switch (operation) {
            case "+":
                result = firstTypeInt + secondTypeInt;
                break;
            case "-":
                result = firstTypeInt - secondTypeInt;
                break;
            case "*":
                result = firstTypeInt * secondTypeInt;
                break;
            case "/":
                result = firstTypeInt / secondTypeInt;
                break;
        }
        return String.valueOf(result);
    }
}