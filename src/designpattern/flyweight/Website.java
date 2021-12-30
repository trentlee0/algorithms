package designpattern.flyweight;

import java.util.HashMap;

public abstract class Website {
    public abstract void use(User user);
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ConcreteWebsite extends Website {
    private String type;

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println(this);
        System.out.println("网站的发布形式为：" + type + " 在使用中...  使用者为 ==> " + user.getName());
    }
}

class WebsiteFactory {
    private static WebsiteFactory instance;

    private HashMap<String, ConcreteWebsite> pool;

    private WebsiteFactory() {
        pool = new HashMap<>();
    }

    public static WebsiteFactory getInstance() {
        if (instance == null) {
            synchronized (WebsiteFactory.class) {
                if (instance == null)
                    instance = new WebsiteFactory();
            }
        }
        return instance;
    }

    /**
     * 根据网站的类型，返回一个网站
     */
    public Website getWebsiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebsite(type));
        }

        return pool.get(type);
    }

    /**
     * 获取池中网站类型总数
     */
    public int getWebsiteCount() {
        return pool.size();
    }
}


class Client {
    public static void main(String[] args) {
        // 网站类型为内部状态，用户为外部状态

        WebsiteFactory factory = WebsiteFactory.getInstance();

        Website news = factory.getWebsiteCategory("新闻");
        news.use(new User("Tom"));

        Website blog1 = factory.getWebsiteCategory("博客");
        blog1.use(new User("Jack"));

        Website blog2 = factory.getWebsiteCategory("博客");
        blog2.use(new User("Smith"));

        Website blog3 = factory.getWebsiteCategory("博客");
        blog3.use(new User("King"));

        System.out.println("网站的分类个数 = " + factory.getWebsiteCount());
    }
}
