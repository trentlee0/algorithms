package designpattern.template;

public abstract class Template {
    public void spendTime() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "ms");
    }

    public abstract void code();
}

class SubTemplate extends Template {

    @Override
    public void code() {
        int num = 0;
        for (int i = 2; i <= 1000; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                num++;
                System.out.printf("%-4d", i);
            }
        }
        System.out.println();
    }
}

class Client {
    public static void main(String[] args) {
        Template template = new SubTemplate();
        template.spendTime();
    }
}
