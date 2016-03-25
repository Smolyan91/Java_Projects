package patterns.observer.weathrer;

/**
 * Все погодные элементы реализуют этот интерфейс. С ним взаимодействует субъект, когда
   приходит время обновления наблюдателей
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
