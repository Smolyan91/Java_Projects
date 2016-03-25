package patterns.observer.weatherobserveble;


public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);


        weatherData.setMeasumentsChanged(80, 65, 30.4f);
        weatherData.setMeasumentsChanged(82, 70, 29.2f);
        weatherData.setMeasumentsChanged(78, 90, 29.2f);
    }
}
