package view;

import controller.Controller;
import model.ArithmeticOperation;

import java.util.Scanner;

/**
 * It is class of view.
 */
public class View {

    private Controller controller;

    /**
     * It is the constructor.
     * @param controller
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * It is method which reads the entered string for calculation
     * @throws Exception
     */
    public void count() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArithmeticOperation arithmeticOperation = new ArithmeticOperation();
        System.out.println("КАЛЬКУЛЯТОР");
        System.out.println("ПРАВИЛА:");
        System.out.println("1) Калькулятор работает только с целыми числами");
        System.out.println("2) Кальтулятор работает с римскими и арабскими числами");
        System.out.println("3) Доступные операции: +, -, *, /");
        System.out.println("ПРИМЕР: 3 + 7");
        System.out.print("ПРИМЕР: III * V\n\n");
        System.out.print("Введите строку для вычисления: ");
        String resultString = scanner.nextLine().trim();
        String[] masFromResultString = resultString.split(" ");
        arithmeticOperation.setFirst(masFromResultString[0]);
        arithmeticOperation.setSecond(masFromResultString[2]);
        arithmeticOperation.setOperation(masFromResultString[1]);
        try {
            String result = controller.count(arithmeticOperation);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
