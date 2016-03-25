package patterns.strategy;

import patterns.strategy.fly.FlyNoWay;
import patterns.strategy.quack.Squack;

/**
 * Резиновая утка
 */
public class RubberDuck extends Duck {

    public RubberDuck(){
        quackBehavior = new Squack();
        flyBehavior = new FlyNoWay();
    }
    @Override
    public void display() {
        System.out.println("I'm rubber duck");
    }
}
