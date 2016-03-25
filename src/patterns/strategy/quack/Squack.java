package patterns.strategy.quack;

/**
 * Утки пищат
 */
public class Squack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("Squeak");
        System.out.println();
    }
}
