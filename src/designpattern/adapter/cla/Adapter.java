package designpattern.adapter.cla;

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

class ConcreteAdapter extends Adaptee implements Adapter {

    @Override
    public void handleRequest() {
        super.request();
    }
}

class Laptop {
    public void net(Adapter adapter) {
        adapter.handleRequest();
    }
}

class Client {
    public static void main(String[] args) {
        Adapter adapter = new ConcreteAdapter();
        Laptop laptop = new Laptop();
        laptop.net(adapter);
    }
}
