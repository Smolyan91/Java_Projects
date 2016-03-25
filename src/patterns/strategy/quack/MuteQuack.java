package patterns.strategy.quack;

/**
 * Утки, не издающие звуков
 */
public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("<<Silence>>");
        System.out.println();
    }
}
