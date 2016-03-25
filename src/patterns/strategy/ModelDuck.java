package patterns.strategy;


import patterns.strategy.fly.FlyNoWay;
import patterns.strategy.quack.Quack;

public class ModelDuck extends Duck{
    public  ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }
    @Override
    public void display() {
        System.out.println("I'm model duck");
    }
}
