package patterns.decorator.additions;


import patterns.decorator.drinks.Beverage;

public abstract class CondimentDecorator extends Beverage {

    public abstract String getDescription();
}
