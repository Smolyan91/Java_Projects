package patterns.decorator.drinks;


import patterns.decorator.additions.Mocha;
import patterns.decorator.additions.Soy;
import patterns.decorator.additions.Whip;

public class StarbuzzCoffe {

    public static void main(String[] args) {
        //Просто кофе
        Beverage espresso = new Espresso();
        System.out.println(String.format("Espresso hasn't additions : %s $ %.2f ", espresso.getDescription(),espresso.cost()));

        //С двойным шоколадом и сливками
        Beverage dackRoast  = new DackRoast();
        dackRoast = new Mocha(dackRoast);
        dackRoast = new Mocha(dackRoast);
        dackRoast = new Whip(dackRoast);
        System.out.println(String.format("Dark Roast with additions : %s $ %.2f",dackRoast.getDescription(),dackRoast.cost()));

        // С соей, шоколадом и сливками
        Beverage houseBlend = new HouseBlend();
        houseBlend = new Soy(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(String.format("House Blend with additions : %s $ %.2f",houseBlend.getDescription(),houseBlend.cost()));

    }
}
