package org.example;

public class Entity
{
    private int id;

    protected String name = "";



    // /////////////////
    // Contructors

    public Entity(int id, String name)
    {
        this.id = id;
        this.name = name;
    }


    // ///////////////////
    // Getters and Setters

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    // String name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}



    // //////
    // Others


    public String GetDescription()
    {
        return "ID: " + getId() + ", '" + getName() + "'";

    }
    public String GetCSV(){return getId() + "," + getName();}


}
