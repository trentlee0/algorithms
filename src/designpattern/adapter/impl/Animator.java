package designpattern.adapter.impl;

public interface Animator {
    void start();

    void during();

    void end();
}

abstract class AnimatorAdapter implements Animator {
    @Override
    public void start() {
    }

    @Override
    public void during() {
    }

    @Override
    public void end() {
    }
}

class Client {
    public static void main(String[] args) {
        Animator animator = new AnimatorAdapter() {
            @Override
            public void start() {
                System.out.println("动画开始……");
            }
        };

        animator.start();
    }
}