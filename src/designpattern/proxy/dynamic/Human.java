package designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface Human {
    String getBelief();

    void eat(String food);
}

/**
 * 被代理类
 */
class SuperMan implements Human {
    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

/**
 * 动态代理工厂
 */
class ProxyFactory implements InvocationHandler {
    /**
     * 被代理类的对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        bind(target);
    }

    /**
     * 绑定被代理类的对象
     */
    public void bind(Object target) {
        this.target = target;
    }

    /**
     * 当通过代理类的对象，调用被代理类的方法时，会自动调用该方法
     *
     * @param proxy  代理类的对象
     * @param method 被代理类的方法
     * @param args   被代理类的方法参数
     * @return 被代理类方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在被代理类的方法前，执行的代码...");
        Object result = method.invoke(target, args);
        System.out.println("在被代理类的方法后，执行的代码...");
        return result;
    }

    /**
     * 返回一个代理类的对象
     */
    public Object getProxyInstance() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }
}

class Client {
    public static void main(String[] args) {
        // 目标对象
        Human man = new SuperMan();
        ProxyFactory handler = new ProxyFactory(man);
        // 代理对象
        Human instance = (Human) handler.getProxyInstance();
        instance.eat("西瓜");
    }
}