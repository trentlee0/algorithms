package designpattern.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private Expression expression;

    public Calculator(String exp) {
        Stack<Expression> stack = new Stack<>();
        char[] charArray = exp.toCharArray();

        Expression left = null;
        Expression right = null;

        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[++i])));
                    break;
            }
        }
        this.expression = stack.pop();
    }

    public int run(HashMap<String, Integer> var) {
        return this.expression.interpreter(var);
    }
}

// 抽象类表达式
abstract class Expression {
    public abstract int interpreter(HashMap<String, Integer> var);
}

// 变量解释器
class VarExpression extends Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    // 根据变量的名称返回值，如：a=12
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}

class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}

// 加法解释器
class AddExpression extends SymbolExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}

// 减法解释器
class SubExpression extends SymbolExpression {
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}

class Client {
    public static void main(String[] args) throws IOException {
        String exp = getExp();
        HashMap<String, Integer> var = getValue(exp);
        Calculator calculator = new Calculator(exp);
        System.out.println("运算结果：" + exp + "=" + calculator.run(var));
    }

    public static String getExp() throws IOException {
        System.out.println("请输入表达式：");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }

    // 未完成！！
    public static HashMap<String, Integer> getValue(String exp) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();

        for (char ch : exp.toCharArray()) {
            if (ch != '+' && ch != '-') {
                if (!map.containsKey(String.valueOf(ch))) {
                    System.out.println("请输入" + String.valueOf(ch) + "的值：");
                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                } else {

                }
            }
        }

        return map;
    }
}