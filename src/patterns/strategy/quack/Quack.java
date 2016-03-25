package patterns.strategy.quack;

/**
 * Утки крякают
 */
public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("quack quack");
        System.out.println();
    }
}
