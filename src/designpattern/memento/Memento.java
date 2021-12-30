package designpattern.memento;

import java.util.ArrayList;
import java.util.List;

// 备忘录对象，负责保存好记录，即Originator的内部状态
public class Memento {
    private String state;

    public Memento(String state) {
        super();
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

// 需要保存状态的对象
class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveStateMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

// 守护者对象，负责保存多个备忘录对象，使用集合管理，提高效率。需要保存不同时间的状态，使用 HashMap<String, 集合>
class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}

class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("状态#1 攻击力 100");
        // 保存当前状态
        caretaker.add(originator.saveStateMemento());

        originator.setState("状态#2 攻击力 80");
        caretaker.add(originator.saveStateMemento());

        originator.setState("状态#3 攻击力 50");


        System.out.println("当前的状态是 = " + originator.getState());
        // 恢复到状态1
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("恢复后的状态是 = " + originator.getState());
    }
}