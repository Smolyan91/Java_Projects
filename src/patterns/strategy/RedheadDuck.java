package patterns.strategy;

import patterns.strategy.fly.FlyWithWings;
import patterns.strategy.quack.Quack;

/**
 * Рыжая утка
 */
public class RedheadDuck extends Duck {

    public RedheadDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm real redhead duck!");
    }
}
