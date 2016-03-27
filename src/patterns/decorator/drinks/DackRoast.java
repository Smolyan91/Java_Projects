package patterns.decorator.drinks;

public class DackRoast extends Beverage {

    public DackRoast(){
        description = "Dack Roast";
    }
    @Override
    public double cost() {
        return 1.67;
    }
}
