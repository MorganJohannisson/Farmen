package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm
{
    // This is the text that makes up the main menu.
    String[] mainMenu = {"View crops", "Remove crop", "Add crop", "View animals", "Add animal", "Feed animal", "Remove animal", "Save", "Exit"};

    boolean animalAdded = false;

    // /////////////////////////////////////
    // Our crops and animals are stored here
    ArrayList <Crop> listOfCrops = new ArrayList<>();
    ArrayList <Animal> listOfAnimals = new ArrayList<>();
//    ArrayList <Entity> listOfEntities = new ArrayList<>();



    // ///////////////////////
    // Files and tribulations.
    File sourceFolder = new File("myLittleFolder");
    File sourceFileCrops = new File("myLittleFolder/Crops.txt");
    File sourceFileAnimals = new File("myLittleFolder/Animals.txt");
    File sourceFileEntities = new File("myLittleFolder/FarmContent.txt");




    // /////////////////
    // Contructora

    // Empty Constructor
    public Farm()
    {
        // TODO - check if file exists - if not, then make something up to populate the arraylist(s)
        if(!sourceFileCrops.exists()){}
        //listOfEntities.add(new Crop().set);
    }



    // //////
    // Others


    // ////
    // Menu
    public void MainMenu()
    {
        boolean keepLooping = true;
        boolean animalAdded = false;
        int userInput = 0;



        while(keepLooping)
        {

            // Display the Main menu
            menuPrintLine1();
            menuPrintItems(mainMenu);
            menuPrintLine1();

            // Ask for user input
            userInput = GetInt("");
            //if(userInput < 1 || userInput > mainMenu.length){ continue; }
            menuPrintLine2();


            switch(userInput)
            {
                case 1: ViewCrops(); break;
                case 2: RemoveCrop(); break;
                case 3: //AddCrop(); break;
                case 4: ViewAnimals(); break;
                case 5: //animalAdded = AddAnimal(); break;
                case 6: FeedAnimal(); break;
                case 7: RemoveAnimal(); break;
                case 8: Save(); break;
                case 9: keepLooping = false; break;
                default: DoNothing(); break;

            } // switch(userInput)

            menuPrintLine2();
        } // main while-loop

    }


    private void ViewCrops(){}

    private void RemoveCrop(){}

    //private void AddCrop(String typeOfCrop, int amountOfCrop){listOfEntities.add(new Crop(NextID(),"FEED", typeOfCrop, amountOfCrop));}
    private void AddCrop(String typeOfCrop, int amountOfCrop){listOfEntities.add(new Entity(NextID(),"FEED", typeOfCrop, amountOfCrop));}
    private void ViewAnimals(){}

    private boolean AddAnimal(String typeOfAnimal)
    {
        listOfEntities.add(new Animal(NextID(),"ANIMAL", typeOfAnimal ));
        return true;
    }


    private void FeedAnimal(){}

    private void RemoveAnimal(){}

    private void Save()
    {
        String thisLine="";
        File saveFolder = new File("MyLittleFolder"); // Local path
        if(!saveFolder.exists()){saveFolder.mkdir();} // Making sure the folder exists.


        try
        {
            FileWriter filewriter = new FileWriter(sourceFileCrops);
            BufferedWriter bf = new BufferedWriter(filewriter);
            {
                //TODO write two files - Crops and Animals
            }

            for(Entity ent : listOfAnimals)
            {
            }


        }
        catch (IOException ioe)
        {
            //oops
        }






    }



    // Functions for User input below

    private String GetString(String prompt)
    {
        System.out.println("\n" + prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    private int GetInt(String prompt)
    {
        int returnValue = 0; // default-value set to 0 - just in case the try-block below fails.
        if(prompt != ""){System.out.print("\n" + prompt);}

        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine().trim();

        if(!inputString.equals("")) {
            try {
                returnValue = Math.abs(Integer.parseInt(inputString));
            } // only positive numbers, please
            catch (Exception e) { DoNothing(); } // (returnValue remains unchanged)
            }


        return returnValue;
    }










    private void DoNothing(){}



    // //////////////////////////////////////////
    // These functions are being used by the menu

    private void menuPrintLine1(){System.out.println("=======================================");}
    private void menuPrintLine2(){System.out.println("---------------------------------------");}

    private void menuPrintItems(String[] menuItems)
    {
        int menuIndex = 1;
        for(String item : menuItems)
        {
            System.out.println(" " + menuIndex + " " + item);
            menuIndex++;
        }
    }



    // If there are no files to read, then we'll fill the barn with stuff
    private void debugFillCrops()
    {
        AddCrop("Crop 1", 3);
        AddCrop("Crop 2", 6);
        AddCrop("Crop 3", 5);
        AddCrop("Crop 4", 4);
        AddCrop("Crop 5", 7);
        AddCrop("Crop 6", 2);
    }


    public void InitializeNextID()
    {
        int theID = 0;

        try{theID += listOfEntities.size();}
        catch (Exception e) {DoNothing();}

        this.nextID = theID;
    }



    private void debugFillAnimals()
    {
        AddAnimal("Hippogriff");
    }


}
