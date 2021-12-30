package designpattern.factory.simple;

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
 * 工厂类
 */
class CarFactory {
    public static Car getCar(String type) {
        if ("奥迪".equals(type)) return new Audi();
        else if ("比亚迪".equals(type)) return new BYD();

        return null;
    }
}

class Client {
    public static void main(String[] args) {
        Car car = CarFactory.getCar("奥迪");
        car.run();
    }
}
