package designpattern.builder;

public abstract class Builder {
    public abstract void buildA();

    public abstract void buildB();

    public abstract Product getProduct();
}

class Worker extends Builder {
    private Product product;

    public Worker() {
        product = new Product();
    }

    @Override
    public void buildA() {
        product.setBuildA("地基");
        System.out.println("地基");
    }

    @Override
    public void buildB() {
        product.setBuildB("钢筋");
        System.out.println("钢筋");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}


class Product {
    private String buildA;
    private String buildB;

    public String getBuildA() {
        return buildA;
    }

    public void setBuildA(String buildA) {
        this.buildA = buildA;
    }

    public String getBuildB() {
        return buildB;
    }

    public void setBuildB(String buildB) {
        this.buildB = buildB;
    }
}

/**
 * 负责指挥对象如何创建
 */
class Director {
    public Product build(Builder builder) {
        builder.buildA();
        builder.buildB();
        return builder.getProduct();
    }
}

class Client {
    public static void main(String[] args) {
        Builder builder = new Worker();
        Director director = new Director();
        Product build = director.build(builder);
        System.out.println(build);
    }
}
