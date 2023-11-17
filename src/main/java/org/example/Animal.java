package org.example;

public class Animal extends Entity
{
    private String species;

    // /////////////////
    // Contructora

    public Animal(int id, String name, String species) {
        super(id, name);
        setSpecies(species);
    }


    // ///////////////////
    // Getters and Setters
    public  String getSpecies() {return species;}
    public   void setSpecies(String species) {this.species = species;}



    // /////////
    // Functions

    public void feed(Crop crop)
    {
        // TODO: inform user that the animals are being/have been fed
        if(crop.getQuantity()>0)
        {
            crop.setQuantity(crop.getQuantity() - 1);
            System.out.println("The animal successfully ate some food.");
        }
        else
        {
            System.out.println("Your animal is disappointed, because there was no food left to feed it with.");
        }
    }


    public String GetDescription()
    {
        return super.GetDescription() + ", '" + getSpecies() + "'";
        //return super.GetDescription() + ", species: '" + getSpecies() + "'";
        //return super.GetDescription() + " " + getSpecies();
    }

    // + animal.getSpecies().toString()
    @Override
    public String GetCSV(){return getId() + "," + getName() + "," + getSpecies();}

}
