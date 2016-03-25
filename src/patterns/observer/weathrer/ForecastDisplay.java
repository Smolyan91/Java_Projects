package patterns.observer.weathrer;

/**
 * Выводит прогноз погоды по показаниям барометра
 */
public class ForecastDisplay implements Observer, DisplayElement{
    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {

        System.out.print("Forecast: ");
        if (currentPressure > lastPressure){
            System.out.println("Weather is good day!");
        }else if (currentPressure == lastPressure){
            System.out.println("Weather is normally");
        }else {
            System.out.println("the weather is bed");
        }
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;

        display();
    }
}
