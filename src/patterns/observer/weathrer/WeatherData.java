package patterns.observer.weathrer;

import java.util.ArrayList;

/**
 * Приложение, оповещающее о изменения погодных условий:
  - temperature - температура
  - humidity - влажность
  - pressure - давление
 */
public class WeatherData implements Subject{

    private ArrayList observers; //для хранения наблюдателей
    private float temperature;
    private float humidity;
    private  float pressure;

    public WeatherData(){
        observers = new ArrayList();
    }

    /**
     * Метод вызывается при каждом обновлении показаний датчиков
     * Оповещение всех наблюдателей о появлении новых данных
     */
    public void measurementsChanged(){
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0){
            observers.remove(i);
        }
    }

    // Оповещаем свех о событии. Реализуется всеми наблюдателями
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature, humidity, pressure);
        }

    }

    public void setMeasuments(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getTemperature() {
        return temperature;
    }
}
