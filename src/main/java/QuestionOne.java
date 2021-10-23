import java.util.HashMap;
import java.util.Map;

public class QuestionOne {
    private static final Map<Character, Integer> VALUES = new HashMap<>();

    static{
        for(int count = 48; count <= 57; count++){
            VALUES.put((char) count, count - 48);
        }
    }

    public static void main(String[] args) {
        System.out.println(calculate("12", "24", "+")); //36
        System.out.println(calculate("12", "24", "-")); //-12
        System.out.println(calculate("12", "24", "/")); //0.5
        System.out.println(calculate("12", "24", "*")); //288

        try {
            System.out.println(calculate("12", "24-", "*"));
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    public static String calculate(String operand1, String operand2, String operator){
        switch(operator){
            case "+" : return extractIntegerValue(operand1) + extractIntegerValue(operand2) + "";
            case "-" : return extractIntegerValue(operand1) - extractIntegerValue(operand2) + "";
            case "*" : return extractIntegerValue(operand1) * extractIntegerValue(operand2) + "";
            case "/" : return extractIntegerValue(operand1) * 1.0 / extractIntegerValue(operand2) + "";
            default : throw new IllegalArgumentException("Operation cannot be performed " +
                    "because operator is not recognized");
        }
    }

    private static int extractIntegerValue(String operand){
        int result = 0;
        int length = operand.length();
        if(operand.charAt(0) == '-' || operand.charAt(0) == '+'){
            length--;
        }
        int placedValue = (int) Math.pow(10, length-1);

        for(int i = 0; i < operand.length(); i++){
            boolean prefix = operand.charAt(i) == '-' || operand.charAt(i) == '+';
            if(i == 0 && prefix){
                continue;
            }
            if (!VALUES.containsKey(operand.charAt(i)) && !prefix) {
                throw new IllegalArgumentException("can not perform any operation on this value, " +
                        "try with a valid integer value");
            }

            if (i > 0 && prefix) {
                throw new IllegalArgumentException("can not perform operation");
            }
            result += (VALUES.get(operand.charAt(i)) * placedValue);
            placedValue = placedValue / 10;
        }


        return operand.charAt(0) == '-' ? -result : result;
    }
}


//    Write a simple algorithm to perform basic operations (add, subtraction, multiplication and division)
//    on a number as String without converting them to Number/Integer
//    a. Input ‘2’, ‘2’, ‘+’ Output 4’