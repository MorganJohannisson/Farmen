package org.example;

public class Crop extends Entity
{
    private String cropType;
    private int quantity;


    // /////////////////
    // Contructora

    // Empty Constructor
    // public Crop() {}

    // Full Constructor(s)
    public Crop(int id, String name, String cropType, int quantity)
    {
        super(id,"FEED");
        this.cropType = cropType;
        this.quantity = quantity;
    }

    public Crop(int id, /*String name,*/ String cropType, int quantity)
    {
        super(id, "FEED");
        this.cropType = cropType;
        this.quantity = quantity;
    }




    // ///////////////////
    // Getters and Setters

    // String cropType
    public String getCropType() {return cropType;}
    public void setCropType(String cropType) {this.cropType = cropType;}

    // int quantity
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}




    // //////
    // Others

    @Override
    public String GetDescription()
    {
        return super.GetDescription();
    }
    @Override
    public String GetCSV(){return GetId() + "," + name + "," + cropType + "," + quantity;}

}
