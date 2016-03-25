package patterns.strategy;


import patterns.strategy.fly.FlyRocketPowered;

public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();

        Duck redheadDuck = new RedheadDuck();
        redheadDuck.performFly();
        redheadDuck.performQuack();

        Duck decoy = new DecoyDuck();
        decoy.performFly();
        decoy.performQuack();

        Duck rubber = new RubberDuck();
        rubber.performFly();
        rubber.performQuack();

        Duck modelDuckDuck = new ModelDuck();
        modelDuckDuck.performFly();
        modelDuckDuck.performQuack();

        Duck rocketDuck = new ModelDuck();
        rocketDuck.performFly();
        rocketDuck.setFlyBehavior(new FlyRocketPowered());
        rocketDuck.performFly();
        rocketDuck.performQuack();
    }
}
