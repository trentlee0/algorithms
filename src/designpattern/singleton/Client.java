package designpattern.singleton;

import designpattern.singleton.lazy.LazyMan;

public class Client {
    public static void main(String[] args) {
        // 多线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(LazyMan.getInstance());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
