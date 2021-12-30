package designpattern.factory.method;

public interface Car {
    void run();
}

class Audi implements Car {
    @Override
    public void run() {
        System.out.println("奥迪在跑...");
    }
}

class BYD implements Car {
    @Override
    public void run() {
        System.out.println("比亚迪在跑...");
    }
}

/**
 * 工厂接口
 */
interface Factory {
    Car getCar();
}

class AudiFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}

class BydFactory implements Factory {
    @Override
    public Car getCar() {
        return new BYD();
    }
}

class Client {
    public static void main(String[] args) {
        Factory factory = new AudiFactory();
        Car car = factory.getCar();
        car.run();
    }
}
