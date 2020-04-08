package com.example.beerrecycler;

import android.database.Cursor;

public class Beer {

    // Class Variables
    private String      name,
                        brewery,
                        category,
                        style,
                        description;
    private int         id,
                        image               = 0;
    private double      abv;
    private final int   COLUMN_ID           = 0,
                        COLUMN_BREWERY      = 1,
                        COLUMN_NAME         = 2,
                        COLUMN_CATEGORY     = 3,
                        COLUMN_STYLE        = 4,
                        COLUMN_ABV          = 5,
                        COLUMN_DESCRIPTION  = 6,
                        COLUMN_IMAGE        = 7;

    // Static Variables
    private static int  idCounter           = 0;

    Beer(Cursor beer) {
        setId(beer.getInt(COLUMN_ID));
        setName(beer.getString(COLUMN_NAME));
        setBrewery(beer.getString(COLUMN_BREWERY));
        setCategory(beer.getString(COLUMN_CATEGORY));
        setStyle(beer.getString(COLUMN_STYLE));
        setAbv(beer.getDouble(COLUMN_ABV));
        setDescription(!beer.getString(COLUMN_DESCRIPTION).equals("NULL") ?
                beer.getString(COLUMN_DESCRIPTION) : "No description available.");
        setImage(beer.getInt(COLUMN_IMAGE));
    } // end public constructor

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.equals(""))
            this.name = "Name Not Available";
        else
            this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    private void setBrewery(String brewery) {
        if(brewery == null || brewery.equals(""))
            this.brewery = "Brewery Not Available";
        else
            this.brewery = brewery;
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        if(category == null || category.equals(""))
            this.category = "Category Not Available";
        else
            this.category = category;
    }

    public String getStyle() {
        return style;
    }

    private void setStyle(String style) {
        if(style == null || style.equals(""))
            this.style = "Style Not Available";
        else
            this.style = style;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if(description == null || description.equals(""))
            this.description = "Description Not Available";
        else
            this.description = description;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if(id == 0) {
            idCounter += 1;
            this.id = idCounter;
        } else {
            this.id = id;
            idCounter = id;
        }
    }

    public double getAbv() {
        return abv;
    }

    private void setAbv(double abv) {
        this.abv = abv;
    }

    public int getImage() {
        return image;
    }

    private void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", brewery='" + brewery + '\'' +
                ", category='" + category + '\'' +
                ", style='" + style + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", abv=" + abv +
                ", image=" + image +
                '}';
    }

} // end class Beer
