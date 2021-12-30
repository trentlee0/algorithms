package designpattern.command;

public interface Command {
    // 执行
    void execute();

    // 撤销
    void undo();
}

class LightOnCommand implements Command {
    private LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 调用接收者的方法
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class LightReceiver {
    public void on() {
        System.out.println("电灯打开");
    }

    public void off() {
        System.out.println("电灯关闭");
    }
}

/**
 * 没有任何命令，即空执行：用于初始化每个按钮，当调用空命令时，对象什么都不做。
 * 这样也是一种设计模式，可以省略掉空判断
 */
class NoCommand implements Command {
    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}

// 遥控器
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControl(int pair) {
        onCommands = new Command[pair];
        offCommands = new Command[pair];

        for (int i = 0; i < pair; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    // 给遥控器对应按钮设置命令
    public void setCommand(int i, Command onCommand, Command offCommand) {
        onCommands[i] = onCommand;
        offCommands[i] = offCommand;
    }

    // 打开的按钮
    public void onButtonPushed(int i) {
        // 找到按下的按钮，并执行命令
        onCommands[i].execute();
        // 记录这次的操作，用于撤销
        undoCommand = onCommands[i];
    }

    // 关闭的按钮
    public void offButtonPushed(int i) {
        offCommands[i].execute();
        undoCommand = offCommands[i];
    }

    // 撤销的按钮
    public void undoButtonPushed() {
        undoCommand.undo();
    }
}

class Client {
    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();
        Command lightOnCommand = new LightOnCommand(lightReceiver);
        Command lightOffCommand = new LightOffCommand(lightReceiver);

        RemoteControl remoteControl = new RemoteControl(5);
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);

        int lightButton = 0;
        System.out.println("----打开按钮----");
        remoteControl.onButtonPushed(lightButton);
        System.out.println("----关闭按钮----");
        remoteControl.offButtonPushed(lightButton);
        System.out.println("----撤销按钮----");
        remoteControl.undoButtonPushed();
    }
}