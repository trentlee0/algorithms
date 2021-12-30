package designpattern.bridge;

/**
 * 品牌
 */
public interface Brand {
    void call();
}

class Xiaomi implements Brand {
    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}

class Vivo implements Brand {
    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}

/**
 * 手机样式
 */
abstract class Phone {
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void call() {
        brand.call();
    }
}

class FoldedPhone extends Phone {
    @Override
    public void call() {
        super.call();
        System.out.println("折叠样式手机");
    }
}

class UprightPhone extends Phone {
    @Override
    public void call() {
        super.call();
        System.out.println("垂直样式手机");
    }
}

class Client {
    public static void main(String[] args) {
        Brand brand = new Vivo();
        Phone folded = new FoldedPhone();
        folded.setBrand(brand);
        folded.call();
    }
}
