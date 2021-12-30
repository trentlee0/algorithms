package designpattern.template.hook;

public abstract class SoyaMilk {
    // 模板方法，加 final 不让子类覆盖
    public final void make() {
        select();
        if (customCondiments())
            addCondiments();
        soak();
        beat();
    }

    public void select() {
        System.out.println("选黄豆");
    }

    public abstract void addCondiments();

    public void soak() {
        System.out.println("浸泡");
    }

    public void beat() {
        System.out.println("搅拌");
    }

    // 钩子方法
    public boolean customCondiments() {
        return true;
    }
}

class PeanutSoyaMilk extends SoyaMilk {
    @Override
    public void addCondiments() {
        System.out.println("加入花生");
    }
}

class RedBeanSoyaMilk extends SoyaMilk {
    @Override
    public void addCondiments() {
        System.out.println("加入红豆");
    }
}

class PureSoyaMilk extends SoyaMilk {
    @Override
    public void addCondiments() {
        // 空实现
    }

    @Override
    public boolean customCondiments() {
        return false;
    }
}

class Client {
    public static void main(String[] args) {
        System.out.println("----红豆豆浆----");
        SoyaMilk peanutSoyaMilk = new RedBeanSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("----纯豆浆----");
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
