package org.example;

public class Animal extends Entity
{
    private String species;



    // /////////////////
    // Contructora


    public Animal(String species) {
        super();
        this.species = species;
    }

    // Full Constructor
    public Animal(String species, int id, String name)
    {
        super(id, name);
        this.species = species;
    }

    public Animal(int id, String name, String species)
    {
        super(id, name);
        this.species = species;
    }


    // ///////////////////
    // Getters and Setters


    // String species
    public  String getSpecies() {return species;}
    public   void setSpecies(String species) {this.species = species;}



    // //////
    // Others

    public void feed(Crop crop)
    {
        // TODO: inform user that the animals are being/have been fed

    }

    @Override
    public String GetDescription()
    {
        return super.GetDescription();
    }
    @Override
    public String GetCSV(){return getId() + "," + name + "," + species;}

}
