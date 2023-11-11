package org.example;

public class Entity
{
    private int id;

    protected String name = "";



    // /////////////////
    // Contructora

    public Entity(int id, String name)
    {
        this.id = id;
        this.name = name;

    }


    // ///////////////////
    // Getters and Setters

    // int id
    public int GetId() {return id;}
    public void SetId(int id) {this.id = id;}

    // String name
    public String GetName() {return name;}
    public void SetName(String name) {this.name = name;}



    // //////
    // Others

    public String GetDescription()
    {
        // TODO: gather info to return in string-format



        return "";
    }
    public String GetCSV(){return GetId() + "," + name;}


}
