package patterns.decorator.drinks;


public abstract class Beverage {
     String description = "Unknown Beverage"; // Описание напитка

    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
