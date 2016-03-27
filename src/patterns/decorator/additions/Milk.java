package patterns.decorator.additions;


import patterns.decorator.drinks.Beverage;

public class Milk extends CondimentDecorator{

    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return .3 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }
}
