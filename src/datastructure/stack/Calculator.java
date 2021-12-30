package datastructure.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 解析字符串计算
 */
public class Calculator {
    public int priority(char operator) {
        if (operator == '*' || operator == '/') return 1;
        else if (operator == '+' || operator == '-') return 0;
        else return -1;
    }

    public boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

    public int cal(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
        }
        return 0;
    }

    public int parseCal(String exp) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (isOperator(ch)) {
                // 优先级大的先运算
                if (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(ch)) {
                    int num1 = numStack.pop();
                    int num2 = numStack.pop();
                    int res = cal(num1, num2, operatorStack.pop());
                    numStack.push(res);
                }
                operatorStack.push(ch);
            } else if (isBracket(ch)) {
                if (ch == ')') {
                    while (operatorStack.peek() != '(') {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int res = cal(num1, num2, operatorStack.pop());
                        numStack.push(res);
                    }
                    operatorStack.pop();
                } else {
                    operatorStack.push(ch);
                }
            } else {
                // 解析多位数字字符
                int j = i + 1;
                StringBuilder builder = new StringBuilder(ch + "");
                while (j < exp.length()) {
                    char t = exp.charAt(j);
                    if ('0' <= t && t <= '9') {
                        builder.append(t);
                        j++;
                        i++;
                    } else {
                        break;
                    }
                }
                numStack.push(Integer.parseInt(builder.toString()));
            }
        }

        while (!operatorStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int res = cal(num1, num2, operatorStack.pop());
            numStack.push(res);
        }

        return numStack.pop();
    }

    public String toSuffixExp(String infixExp) {
        StringBuilder builder = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('*', 1);
        priorities.put('/', 1);
        priorities.put('+', 0);
        priorities.put('-', 0);

        for (int i = 0; i < infixExp.length(); i++) {
            char ch = infixExp.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!stack.isEmpty() &&
                        stack.peek() != '(' &&
                        priorities.get(stack.peek()) >= priorities.get(ch)) {
                    builder.append(stack.pop());
                }
                stack.push(ch);
            } else if (ch == ')') {
                while (stack.peek() != '(') {
                    builder.append(stack.pop());
                }
                stack.pop();
            } else if (ch == '(') {
                stack.push(ch);
            } else if ('0' <= ch && ch <= '9') {
                int j = i + 1;
                while (j < infixExp.length()) {
                    char t = infixExp.charAt(j);
                    if ('0' <= t && t <= '9') j++;
                    else break;
                }
                builder.append(infixExp, i, j).append(" ");
                i = j - 1;
            }
        }

        while (!stack.isEmpty()) builder.append(stack.pop());

        return builder.toString();
    }

    public int parseCalSuffix(String suffixExp) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < suffixExp.length(); i++) {
            char ch = suffixExp.charAt(i);
            if (isOperator(ch)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int res = cal(num1, num2, ch);
                stack.push(res);
            } else if ('0' <= ch && ch <= '9') {
                int j = i + 1;
                while (j < suffixExp.length()) {
                    char t = suffixExp.charAt(j);
                    if ('0' <= t && t <= '9') j++;
                    else break;
                }
                stack.push(Integer.parseInt(suffixExp.substring(i, j)));
                i = j - 1;
            }
        }
        return stack.pop();
    }
}

class CalculatorDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        String exp = "(3+21)*6-2";
        System.out.println(calculator.parseCal(exp));

        System.out.println(calculator.parseCalSuffix("3 4+51*6-"));

        System.out.println(calculator.toSuffixExp("(3+4)*51-6"));
    }
}
