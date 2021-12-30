//package designpattern.proxy.cg;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
//public class Teacher {
//    public void teach() {
//        System.out.println("老师授课中...");
//    }
//}
//
//class ProxyFactory implements MethodInterceptor {
//
//    private Object target;
//
//    public ProxyFactory(Object target) {
//        this.target = target;
//    }
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        System.out.println("代理开始~~");
//        Object invoke = method.invoke(target, args);
//        System.out.println("代理提交~~");
//        return invoke;
//    }
//
//    public Object getProxyInstance() {
//        // 创建一个工具类
//        Enhancer enhancer = new Enhancer();
//        // 设置父类
//        enhancer.setSuperclass(target.getClass());
//        // 设置回调函数
//        enhancer.setCallback(this);
//        // 创建子类对象，即代理对象
//        return enhancer.create();
//    }
//}
//
//class Client {
//    public static void main(String[] args) {
//        Teacher teacher = new Teacher();
//        ProxyFactory proxyFactory = new ProxyFactory(teacher);
//        Teacher instance = (Teacher) proxyFactory.getProxyInstance();
//        instance.teach();
//    }
//}
