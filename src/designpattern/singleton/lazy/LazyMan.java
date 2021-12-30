package designpattern.singleton.lazy;

/**
 * 双重检测锁模式的懒汉式单例（DCL懒汉式），用关键字 volatile 修饰对象。
 */
public class LazyMan {
    private static volatile LazyMan lazyMan;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (lazyMan != null) {
                throw new RuntimeException("不要使用反射破坏异常");
            }
        }
    }

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /*
                    创建对象，不是一个原子性操作：
                    1. 分配内存空间
                    2. 执行构造方法，初始化兑现
                    3. 把这个对象指向这个空间
                    所以，第一个线程可能步骤为 132，对于后面的线程获得的对象可能还没有完成构造
                     */
                }
            }
        }
        return lazyMan;
    }
}
