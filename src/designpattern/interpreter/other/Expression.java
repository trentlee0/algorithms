package designpattern.interpreter.other;

public interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

class AndExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

class Client {
    public static void main(String[] args) {
        //规则：Robert 和 John 是男性
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        Expression isMale = new OrExpression(robert, john);
        System.out.println("John is male? " + isMale.interpret("John"));

        //规则：Julie 是一个已婚的女性
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        Expression isMarriedWoman = new AndExpression(julie, married);
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
    }
}
