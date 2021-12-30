package designpattern.singleton.enumerate;

public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }

    public void say() {
        System.out.println("Hello");
    }
}

/*
class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle enumSingle1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> constructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        EnumSingle enumSingle2 = constructor.newInstance();
        System.out.println(enumSingle1);
        System.out.println(enumSingle2);
    }
}
*/

class Client {
    public static void main(String[] args) {
        EnumSingle instance = EnumSingle.INSTANCE;
        instance.say();
        System.out.println(instance);
    }
}