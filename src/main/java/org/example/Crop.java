package org.example;

public class Crop extends Entity
{
    private String cropType;
    private int quantity;



    // /////////////////
    // Contructora

    public Crop(int id, String name, String cropType, int quantity)
    {
        super(id, name);
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




    // /////////
    // Functions

    public String GetDescription()
    {
        return super.GetDescription() + ", '" + getCropType() + "', " + getQuantity();
    }
    @Override
    public String GetCSV(){return getId() + "," + getName() + "," + getCropType() + "," + getQuantity();}


}
