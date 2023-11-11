package org.example;

public class Animal extends Entity
{
    private String species;



    // /////////////////
    // Contructora
    public Animal(int id, /*String name,*/ String species)
    {
        super(id, "ANIMAL");
        this.species = species;
    }


    // ///////////////////
    // Getters and Setters
    public  String GetSpecies() {return species;}
    public   void SetSpecies(String species) {this.species = species;}



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
    public String GetCSV(){return GetId() + "," + name + "," + species;}

}
