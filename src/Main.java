
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Main {



    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }

    public static String calc(String input) throws Exception {
        int a;
        int b;
        String[] roman = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String operator = specifyOperator(input);
        String ret = null;
        String[] operands = input.split("[+\\-*/]");
        if (operator == null) {
            throw new Exception("Строка не является математической операцией");
        }

        if (operands.length != 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String a1 = operands[0];
        String b1 = operands[1];
        a1 = a1.trim();
        b1 = b1.trim();


        if (isInteger(a1) && isInteger(b1)) {
            a = Integer.parseInt(a1);
            b = Integer.parseInt(b1);
            if ( a > 10 || b > 10 ) throw new Exception("Числа должны быть от 1 до 10");
            int temp = count(a, b, operator);
            ret = temp + "";
        } else if (Arrays.asList(roman).contains(a1) && Arrays.asList(roman).contains(b1)) {
            a = convertRomanToInt(a1);
            b = convertRomanToInt(b1);
            int temp = count(a, b, operator);
            if (temp <= 0) throw new Exception("В римской системе нет отрицательных чисел и нуля");
            ret = temp + "";
        } else if ( (Arrays.asList(roman).contains(a1) && isInteger(b1)) ||
                (isInteger(a1) && Arrays.asList(roman).contains(b1)) ) throw new Exception("Используются одновременно разные системы счисления");


        return ret;
    }



    static int count(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }


    static String specifyOperator(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }







    public static int convertRomanToInt(String s)
    {
        Map<Character, Integer> map=new HashMap<Character, Integer>();

        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        s = s.replace("IV","IIII");
        s = s.replace("IX","VIIII");
        s = s.replace("XL","XXXX");
        s = s.replace("XC","LXXXX");
        s = s.replace("CD","CCCC");
        s = s.replace("CM","DCCCC");
        int number = 0;

        for (int i = 0; i < s.length(); i++) {
            number = number + (map.get(s.charAt(i)));
        }

        return number;
    }


}