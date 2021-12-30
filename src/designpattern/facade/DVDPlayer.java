package designpattern.facade;


public class DVDPlayer {
    private static DVDPlayer instance = new DVDPlayer();

    private DVDPlayer() {
    }

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("DVD 打开");
    }

    public void off() {
        System.out.println("DVD 关闭");
    }

    public void play() {
        System.out.println("DVD 开始播放");
    }

    public void pause() {
        System.out.println("DVD 暂停播放");
    }
}

class Popcorn {
    private static Popcorn instance = new Popcorn();

    private Popcorn() {
    }

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("爆米花机打开");
    }

    public void off() {
        System.out.println("爆米花机关闭");
    }

    public void pop() {
        System.out.println("出爆米花");
    }
}

class Projector {
    private static Projector instance = new Projector();

    private Projector() {
    }

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("投影仪打开");
    }

    public void off() {
        System.out.println("投影仪关闭");
    }

    public void focus() {
        System.out.println("投影仪聚焦");
    }
}

class Screen {
    private static Screen instance = new Screen();

    private Screen() {
    }

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println("屏幕上升");
    }

    public void down() {
        System.out.println("屏幕下降");
    }
}

class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Popcorn popcorn;
    private Projector projector;
    private Screen screen;

    public HomeTheaterFacade() {
        dvdPlayer = DVDPlayer.getInstance();
        popcorn = Popcorn.getInstance();
        projector = Projector.getInstance();
        screen = Screen.getInstance();
    }

    public void ready() {
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        dvdPlayer.on();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        popcorn.off();
        popcorn.off();
        screen.up();
        projector.off();
        dvdPlayer.off();
    }
}

class Client {
    public static void main(String[] args) {
        HomeTheaterFacade theaterFacade = new HomeTheaterFacade();
        theaterFacade.ready();
        System.out.println("================");
        theaterFacade.play();
        System.out.println("================");
        theaterFacade.pause();
        System.out.println("================");
        theaterFacade.end();
    }
}
