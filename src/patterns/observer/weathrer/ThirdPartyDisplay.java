package patterns.observer.weathrer;

/**
 * Для реализации других визуальных элементов
 */
public class ThirdPartyDisplay implements Observer, DisplayElement{
    @Override
    public void display() {

    }

    @Override
    public void update(float temp, float humidity, float pressure) {

    }
}
