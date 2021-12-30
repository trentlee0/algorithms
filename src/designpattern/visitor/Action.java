package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

// 访问者
public abstract class Action {
    public abstract void evaluateMan(Man man);

    public abstract void evaluateWoman(Woman woman);
}

class Success extends Action {
    @Override
    public void evaluateMan(Man man) {
        System.out.println("男观众 '" + man.getName() + "' 给该歌手的评价是：很成功！");
    }

    @Override
    public void evaluateWoman(Woman woman) {
        System.out.println("女观众 '" + woman.getName() + "' 给该歌手的评价是：很成功！");
    }
}

class Fail extends Action {
    @Override
    public void evaluateMan(Man man) {
        System.out.println("男观众 '" + man.getName() + "' 给该歌手的评价是：很失败！");
    }

    @Override
    public void evaluateWoman(Woman woman) {
        System.out.println("女观众 '" + woman.getName() + "' 给该歌手的评价是：很失败！");
    }
}

class Wait extends Action {
    @Override
    public void evaluateMan(Man man) {
        System.out.println("男观众 '" + man.getName() + "' 给该歌手的评价是：待定。。。");
    }

    @Override
    public void evaluateWoman(Woman woman) {
        System.out.println("女观众 '" + woman.getName() + "' 给该歌手的评价是：待定。。。");
    }
}

abstract class Person {
    private String name;

    public abstract void accept(Action action);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Man extends Person {
    @Override
    public void accept(Action action) {
        action.evaluateMan(this);
    }
}

// 说明：
// 1. 这里使用到了双分派，即首先在客户端程序中，将具体状态作为参数传递 Woman 中（第一次分派）
// 2. 然后 Woman 类调用作为参数的“具体方法”中的 evaluateWoman，同时将自己（this）作为参数传入（第二次分派）
class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.evaluateWoman(this);
    }
}

// 数据结构，管理人（Man、Woman）
class ObjectStructure {
    private List<Person> elements;

    public ObjectStructure() {
        elements = new ArrayList<>();
    }

    public void attach(Person p) {
        elements.add(p);
    }

    public void detach(Person p) {
        elements.remove(p);
    }

    // 显示测评情况
    public void display(Action action) {
        for (Person person : elements) {
            person.accept(action);
        }
    }
}

class Client {
    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();

        Man man1 = new Man();
        man1.setName("John");
        Man man2 = new Man();
        man2.setName("Alan");

        structure.attach(man1);
        structure.attach(man2);

        Action action = new Wait();
        structure.display(action);
    }
}