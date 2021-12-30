package designpattern.decorator;

public abstract class Drink {
    /**
     * 描述
     */
    public String des;

    /**
     * 价格
     */
    private float price = 0.0f;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();

    @Override
    public String toString() {
        return des + " : " + price;
    }
}

class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}

class Espresso extends Coffee {
    public Espresso() {
        setDes("意大利咖啡");
        setPrice(6.0f);
    }
}

class LongBlack extends Coffee {
    public LongBlack() {
        setDes("美式咖啡");
        setPrice(5.0f);
    }
}

class Decorator extends Drink {
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDes() {
        return String.format("%s + %s", drink.getDes(), super.getDes());
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String toString() {
        return getDes() + " : " + cost();
    }
}

class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setDes("巧克力");
        setPrice(3.0f);
    }
}

class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDes("牛奶");
        setPrice(2.0f);
    }
}

class CoffeeBar {
    public static void main(String[] args) {
        Drink order = new LongBlack();
        System.out.println(order);

        order = new Milk(order);
        System.out.println(order);

        order = new Chocolate(order);
        System.out.println(order);
    }
}