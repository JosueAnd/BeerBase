package com.example.beerrecycler;

public class Beer {

    // Class Variables
    private String  name,
                    brewery,
                    flavor,
                    style,
                    taste,
                    garnish,
                    foodPair;
    private int     image;

    public Beer(
            String name,
            String brewery,
            String flavor,
            String style,
            String taste,
            String garnish,
            String foodPair,
            int image
    ) {
        setName(name);
        setBrewery(brewery);
        setFlavor(flavor);
        setStyle(style);
        setTaste(taste);
        setGarnish(garnish);
        setFoodPair(foodPair);
        setImage(image);
    } // end public constructor

    public String getName() {
        return name;
    } // end getName

    public void setName(String name) { this.name = name; } // end setName

    public String getBrewery() {
        return brewery;
    } // end getBrewery

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    } // end setBrewery

    public String getFlavor() {
        return flavor;
    } // end getFlavor

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    } // end setFlavor

    public String getStyle() {
        return style;
    } // end getStyle

    public void setStyle(String style) {
        this.style = style;
    } // end setStyle

    public String getTaste() {
        return taste;
    } // end getTaste

    public void setTaste(String taste) {
        this.taste = taste;
    } // end setTaste

    public String getGarnish() {
        return garnish;
    } // end getGarnish

    public void setGarnish(String garnish) {
        this.garnish = garnish;
    } // end setGarnish

    public String getFoodPair() {
        return foodPair;
    } // end getFoodPair

    public void setFoodPair(String foodPair) {
        this.foodPair = foodPair;
    } // end setFoodPair

    public int getImage() {
        return image;
    } // end getImage

    public void setImage(int image) {
        this.image = image;
    } // end setImage

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", brewery='" + brewery + '\'' +
                ", flavor='" + flavor + '\'' +
                ", style='" + style + '\'' +
                ", taste='" + taste + '\'' +
                ", garnish='" + garnish + '\'' +
                ", foodPair='" + foodPair + '\'' +
                ", image=" + image +
                '}';
    } // end toString
} // end class Beer
