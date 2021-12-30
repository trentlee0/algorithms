package designpattern.adapter.obj;

public interface Adapter {
    void handleRequest();
}

/**
 * 被适配的类
 */
class Adaptee {
    public void request() {
        System.out.println("连接网线上网");
    }
}

class ConcreteAdapter implements Adapter {
    private Adaptee adaptee;

    public ConcreteAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleRequest() {
        adaptee.request();
    }
}

class Laptop {
    public void net(Adapter adapter) {
        adapter.handleRequest();
    }
}

class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new ConcreteAdapter(adaptee);
        Laptop laptop = new Laptop();
        laptop.net(adapter);
    }
}
