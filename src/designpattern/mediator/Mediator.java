package designpattern.mediator;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Mediator {
    void showMessage(Colleague colleague, String message);
}

class UserMediator implements Mediator {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void showMessage(Colleague colleague, String message) {
        System.out.println(dateFormat.format(new Date())
                + " [" + colleague.getName() + "] : " + message);
    }
}

interface Colleague {
    void sendMessage(String message);

    String getName();
}

class User implements Colleague {
    private String name;
    private Mediator mediator;

    public User(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        mediator.showMessage(this, message);
    }
}

class Client {
    public static void main(String[] args) {
        Mediator mediator = new UserMediator();
        User robert = new User("Robert", mediator);
        User john = new User("John", mediator);

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}