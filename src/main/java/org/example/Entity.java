package org.example;

public class Entity
{
    private int id;
    int nextID = -1;
    protected String name = "";



    // /////////////////
    // Contructora

    // Empty Constructor


    // Full Constructor
    public Entity(int id, String name, String theCrop, int theQuantity)
    {

        this.id = id;
        this.name = name;


    }




    public Entity() {

    }


    // ///////////////////
    // Getters and Setters

    // int id
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // String name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}



    // //////
    // Others

    public String GetDescription()
    {
        // TODO: gather info to return in string-format



        return "";
    }
    public String GetCSV(){return getId() + "," + name;}


    public int NextID()
    {
        this.nextID++;
        return this.nextID;
    }

}
