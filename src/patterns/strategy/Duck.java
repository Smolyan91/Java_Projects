package patterns.strategy;


import patterns.strategy.fly.FlyBehavior;
import patterns.strategy.quack.QuackBehavior;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public abstract void display();
    public Duck(){

    }
    // Метод заменяет поведение quack()
    public void performQuack(){
        quackBehavior.quack();
    }

    // Метод заменяет поведение fly()
    public void performFly(){
        flyBehavior.fly();
    }

    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }
}
