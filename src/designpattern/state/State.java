package designpattern.state;

import java.util.Random;

public abstract class State {
    protected Activity activity;

    public State(Activity activity) {
        this.activity = activity;
    }

    public abstract void deductMoney();

    public abstract boolean raffle();

    public abstract void dispensePrize();
}

class NoRaffleState extends State {
    public NoRaffleState(Activity activity) {
        super(activity);
    }

    @Override
    public void deductMoney() {
        System.out.println("扣除50积分成功，您可以抽奖了");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("扣了积分才能抽奖喔！");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}

class CanRaffleState extends State {
    public CanRaffleState(Activity activity) {
        super(activity);
    }

    @Override
    public void deductMoney() {
        System.out.println("已经扣取过了积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖，请稍等！");
        Random r = new Random();
        int num = r.nextInt(10);
        if (num == 0) {
            activity.setState(activity.getDispenseState());
            return true;
        }
        System.out.println("很遗憾没有抽中奖品！");
        activity.setState(activity.getNoRaffleState());
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("没中奖，不能发放奖品");
    }
}

class DispenseState extends State {
    public DispenseState(Activity activity) {
        super(activity);
    }

    @Override
    public void deductMoney() {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0) {
            System.out.println("恭喜中奖了");
            activity.setState(activity.getNoRaffleState());
        } else {
            System.out.println("很遗憾，奖品发完了");
            activity.setState(activity.getDispenseOutState());
        }
    }
}

class DispenseOutState extends State {
    public DispenseOutState(Activity activity) {
        super(activity);
    }

    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了，请下次在参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了，请下次在参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发送完了，请下次在参加");
    }
}

class Activity {
    private State state;
    private int count;

    private State noRaffleState = new NoRaffleState(this);
    private State canRaffleState = new CanRaffleState(this);
    private State dispenseState = new DispenseState(this);
    private State dispenseOutState = new DispenseOutState(this);

    public Activity(int count) {
        this.state = getNoRaffleState();
        this.count = count;
    }

    public void deductMoney() {
        state.deductMoney();
    }

    public void raffle() {
        if (state.raffle())
            state.dispensePrize();
    }

    public void dispensePrize() {
        state.dispensePrize();
    }

    public int getCount() {
        int curCount = count;
        count--;
        return curCount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(State noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(State dispenseState) {
        this.dispenseState = dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(State dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }
}

class Client {
    public static void main(String[] args) {
        Activity activity = new Activity(1);
        for (int i = 0; i < 300; i++) {
            System.out.println("------------第" + (i + 1) + "次抽奖------------");
            activity.deductMoney();
            activity.raffle();
        }
    }
}