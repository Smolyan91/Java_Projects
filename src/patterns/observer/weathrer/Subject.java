package patterns.observer.weathrer;

/**
 * Интерфейс субъекта
 */
public interface Subject  {
    void registerObserver(Observer observer); //регистрация наблюдателя
    void removeObserver(Observer observer);  // удаление наблюдателя
    void notifyObservers(); // оповещение наблюдателей
}
