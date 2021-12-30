package designpattern.factory.abs;

public interface ProductFactory {
    PhoneProduct phoneProduct();

    RouterProduct routerProduct();
}

/**
 * 手机接口
 */
interface PhoneProduct {
    void start();
    void shutdown();
    void callUp();
}

/**
 * 路由器接口
 */
interface RouterProduct {
    void start();
    void shutdown();
    void openWiFi();
}

class HuaweiPhone implements PhoneProduct {
    @Override
    public void start() {
        System.out.println("华为手机开启");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");
    }

    @Override
    public void callUp() {
        System.out.println("华为手机打电话");
    }
}

class XiaomiPhone implements PhoneProduct {
    @Override
    public void start() {
        System.out.println("小米手机开启");
    }

    @Override
    public void shutdown() {
        System.out.println("小米手机关机");
    }

    @Override
    public void callUp() {
        System.out.println("小米手机打电话");
    }
}

class HuaweiRouter implements RouterProduct {
    @Override
    public void start() {
        System.out.println("华为路由器开启");
    }

    @Override
    public void shutdown() {
        System.out.println("华为路由器关机");
    }

    @Override
    public void openWiFi() {
        System.out.println("华为路由器开WiFi");
    }
}

class XiaomiRouter implements RouterProduct {
    @Override
    public void start() {
        System.out.println("小米路由器开启");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由器关机");
    }

    @Override
    public void openWiFi() {
        System.out.println("小米路由器开WiFi");
    }
}

/**
 * 华为工厂
 */
class HuaweiFactory implements ProductFactory {
    @Override
    public PhoneProduct phoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public RouterProduct routerProduct() {
        return new HuaweiRouter();
    }
}

/**
 * 小米工厂
 */
class XiaomiFactory implements ProductFactory {
    @Override
    public PhoneProduct phoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public RouterProduct routerProduct() {
        return new XiaomiRouter();
    }
}

class Client {
    public static void main(String[] args) {
        ProductFactory factory = new XiaomiFactory();
        PhoneProduct phone = factory.phoneProduct();
        RouterProduct router = factory.routerProduct();
        phone.start();
        router.openWiFi();
    }
}
