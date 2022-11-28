import java.util.Scanner;

class calculator {

    public static void main(String[] args) {
        String[] rawString = getNumeralsFromInput();
        String type = checkType(rawString);
        String calcResult = calculate(rawString, type);

        printResult(calcResult, type);
    }

    private static void printResult(String calcResult, String type) {
        switch (type) {
            case "rome":
                String result = convertNumbersToRome(calcResult);
                System.out.println(result);
                break;
            case "arab":
                System.out.println(calcResult);
                break;
            default:
                break;
        }
    }

    private static String convertNumbersToRome(String calcResult) {
        String result = "";
        String[] NumArray = calcResult.split("");

        if (Integer.valueOf(calcResult) <= 10) {
            for (number num : system) {
                if (calcResult.equals(num.arab)) {
                    result = num.rome;
                }
            }
        } else if (Integer.valueOf(calcResult) > 10 && Integer.valueOf(calcResult) <= 39) {
            switch (NumArray[0]) {
                case "1":
                    result = "X";
                    break;
                case "2":
                    result = "XX";
                    break;
                case "3":
                    result = "XXX";
                    break;
                default:
                    break;
            }
            for (number num : system) {
                if (NumArray[1].equals(num.arab)) {
                    result = result + num.rome;
                }
            }

        } else if (Integer.valueOf(calcResult) >= 40 && Integer.valueOf(calcResult) <= 49) {
            result = "XL";
            for (number num : system) {
                if (NumArray[1].equals(num.arab)) {
                    result = result + num.rome;
                }
            }
        } else if (Integer.valueOf(calcResult) >= 50 && Integer.valueOf(calcResult) <= 89) {
            switch (NumArray[0]) {
                case "5":
                    result = "L";
                    break;
                case "6":
                    result = "LX";
                    break;
                case "7":
                    result = "LXX";
                    break;
                case "8":
                    result = "LXXX";
                    break;
                default:
                    break;
            }
            for (number num : system) {
                if (NumArray[1].equals(num.arab)) {
                    result = result + num.rome;
                }
            }

        } else if (Integer.valueOf(calcResult) >= 90 && Integer.valueOf(calcResult) <= 99) {
            result = "XC";
            for (number num : system) {
                if (NumArray[1].equals(num.arab)) {
                    result = result + num.rome;
                }
            }

        } else if (Integer.valueOf(calcResult) == 100) {
            result = "C";
        }

        return result;
    }

    static String calculate(String[] rawString, String type) {
        String operator = rawString[1];
        int num1;
        int num2;

        if (type.equals("arab")) {
            num1 = Integer.valueOf(rawString[0]);
            num2 = Integer.valueOf(rawString[2]);
        } else if (type.equals("rome")) {
            String[] result = convertNumbersToArab(rawString[0], rawString[2]);
            num1 = Integer.valueOf(result[0]);
            num2 = Integer.valueOf(result[1]);
        } else {
            System.out.println("Вы используете разное сочетание систем счисления");
            num1 = (Integer) null;
            num2 = (Integer) null;
        }
        switch (operator) {
            case "+":
                return Integer.toString(num1 + num2);
            case "-":
                int result = num1 - num2;
                if (type == "rome" && Integer.valueOf(result) < 0) {
                    throw new Error("Римские цифры должны быть больше нуля");
                }
                return Integer.toString(result);
            case "*":
                return Integer.toString(num1 * num2);
            case "/":
                return Integer.toString(num1 / num2);
            default:
                return ("error");
        }
    }

    static String checkType(String[] rawString) {
        String firstNumberType = null;
        String secondNumberType = null;

        for (number num : system) {
            if (rawString[0].equals(num.arab)) {
                firstNumberType = "arab";
            }
            if (rawString[2].equals(num.arab)) {
                secondNumberType = "arab";
            }
            if (rawString[0].equals(num.rome)) {
                firstNumberType = "rome";
            }
            if (rawString[2].equals(num.rome)) {
                secondNumberType = "rome";
            }
        }

        if (firstNumberType == secondNumberType) {
            return firstNumberType;
        } else
            return "Вы используете разные системы счисления";
    }

    static String[] getNumeralsFromInput() {
        System.out.print("Введите операцию в формате a оператор b");
        Scanner in = new Scanner(System.in);
        String numString = in.nextLine();
        in.close();
        return numString.split(" ");
    }

    static String[] convertNumbersToArab(String num1, String num2) {
        String[] result = { "first", "second" };
        for (number num : system) {
            if (num1.equals(num.rome)) {
                result[0] = num.arab;
            }
            if (num2.equals(num.rome)) {
                result[1] = num.arab;
            }
        }
        return result;
    }

    static class number {
        number(String arab, String rome) {
            this.arab = arab;
            this.rome = rome;
        }

        String arab;
        String rome;
    }

    static number[] system = {
            new number("1", "I"),
            new number("2", "II"),
            new number("3", "III"),
            new number("4", "IV"),
            new number("5", "V"),
            new number("6", "VI"),
            new number("7", "VII"),
            new number("8", "VIII"),
            new number("9", "IX"),
            new number("10", "X"), };
}