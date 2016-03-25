package patterns.strategy;


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
    }
}
