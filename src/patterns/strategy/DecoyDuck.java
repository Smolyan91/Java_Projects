package patterns.strategy;

import patterns.strategy.fly.FlyNoWay;
import patterns.strategy.quack.MuteQuack;

/**
 * Утка- приманка
 */
public class DecoyDuck extends Duck {

    public DecoyDuck(){
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }
    @Override
    public void display() {
        System.out.println("I'm decoy duck");
    }
}
