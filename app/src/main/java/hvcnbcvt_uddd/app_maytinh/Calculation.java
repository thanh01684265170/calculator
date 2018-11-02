package hvcnbcvt_uddd.app_maytinh;

import java.util.Stack;
import java.util.StringTokenizer;

public class Calculation {

    public static float main(String s) {
            float result = (float) cal(s);
            return result;
        }

    public static int priority(String s) {
        if (s.equals("x") || s.equals("/")) {
            return 2;
        }
        if (s.equals("+") || s.equals("-")) {
            return 1;
        }
        return 0;
    }

    public static boolean isOperator(String c) {
        if (c.equals("x") || c.equals("/") || c.equals("+") || c.equals("-")) {
            return true;
        }
        return false;
    }

    public static boolean isOpenrand(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public static String[] standarMath(String math) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < math.length(); i++) {
            char c = math.charAt(i);
            if (isOpenrand(c)) {
                builder.append(c);
            } else {
                builder.append(" " + c + " ");
            }
        }
        StringTokenizer st = new StringTokenizer(builder.toString());
        int i = 0;
        String[] element = new String[st.countTokens()];
        while (st.hasMoreTokens()) {
            element[i] = st.nextToken();
            i++;
        }
        return element;
    }

    public static String toPostfix(String[] elements) {
        Stack<String> stack = new Stack<>();
        StringBuilder buidler = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            String s = elements[i];
            if (isOperator(s)) {
                while (!stack.isEmpty() && priority(s) <= priority(stack.peek())) {
                    buidler.append(stack.pop())
                            .append(" ");
                }
                stack.push(s);
            } else if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!stack.peek().equals("(")) {
                    buidler.append(stack.pop())
                            .append(" ");
                }
                stack.pop();
            } else {    //isOperand
                buidler.append(s)
                        .append(" ");
            }
        }
        while (!stack.isEmpty()) {
            buidler.append(stack.pop())
                    .append(" ");
        }
        return buidler.toString();
    }

    public static double cal(String math) {
        String[] elements = toPostfix(standarMath(math)).split(" ");
        Stack<Float> stack = new Stack<>();
        for (int i = 0; i < elements.length; i++) {
            String element = elements[i];
            if (isOperator(element)) {
                float a = stack.pop();
                float b = stack.pop();
                switch (element) {
                    case "+":
                        b += a;
                        break;
                    case "-":
                        b -= a;
                        break;
                    case "x":
                        b *= a;
                        break;
                    case "/":
                        b /= a;
                        break;
                }
                stack.push(b);
            } else {
                stack.push(Float.parseFloat(element));
            }
        }
        return stack.pop();
    }
}
