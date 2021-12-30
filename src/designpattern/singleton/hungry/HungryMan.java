package designpattern.singleton.hungry;

public class HungryMan {
    private static HungryMan hungry = new HungryMan();

    private HungryMan() {
    }

    public static HungryMan getInstance() {
        return hungry;
    }
}
