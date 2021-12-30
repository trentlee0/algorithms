package designpattern.proxy.stat;

public interface ClothFactory {
    void produceCloth();
}

// 代理类
class ProxyClothFactory implements ClothFactory {

    //用被代理类对象进行实例化
    private NikeClothFactory factory;

    public ProxyClothFactory(NikeClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}

// 被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}

class Client {
    public static void main(String[] args) {
        // 创建被代理类的对象
        NikeClothFactory nike = new NikeClothFactory();
        // 创建代理类对象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        proxyClothFactory.produceCloth();
    }
}