import exeptions.MyException;
import myutil.RomanUtils;

import java.util.*;

public class Main {


    public static void main(String[] args) throws MyException {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();

        System.out.println(calc(input));
    }

    public static String calc(String input) throws MyException {
        //Разбиваем строку на отдельные компоненты
        String[] inputArray = input.split(" ");
        String operator = inputArray[1];

        if (inputArray.length != 3) {
            //Пробрасываем исключение, если количество компонентов не равно 3-ем
            throw new MyException("Неверное колличество элементов: " + inputArray.length + " из 3");
        }
        //Триггер римских чисел
        boolean isRoman = RomanUtils.isRoman(inputArray);

        int firstValue;
        int secondValue;
        String firstInput = inputArray[0];
        String secondInput = inputArray[2];

        // Проверяем римские числа
        if (isRoman) {
            firstValue = RomanUtils.ROME.get(firstInput);
            secondValue = RomanUtils.ROME.get(secondInput);
            // Проверяем, попадает число в диапозон от 0 до 10
        } else if (isValidInput(firstInput, secondInput)) {
            firstValue = Integer.parseInt(firstInput);
            secondValue = Integer.parseInt(secondInput);
        } else {
            // Пробрасываем исключение, если число не входит в диапозон
            throw new MyException("Числа слишком большие");
        }

        int result = switch (operator) {
            case "+" -> firstValue + secondValue;
            case "-" -> firstValue - secondValue;
            case "*" -> firstValue * secondValue;
            case "/" -> firstValue / secondValue;
            default -> throw new IllegalStateException("Неверный оператор: " + operator);
        };

        if (isRoman) {
            if (result <= 0) {
                throw new MyException("Ответ не может быть меньше или равен нулю");
            }
            return RomanUtils.romanNumeralConvertor(result);
        }
        return String.valueOf(result);

    }

    private static boolean isValidInput(String value1, String value2) {
        return Integer.parseInt(value1) > 0 && Integer.parseInt(value1) < 11
                && Integer.parseInt(value2) > 0 && Integer.parseInt(value2) < 11;
    }

}