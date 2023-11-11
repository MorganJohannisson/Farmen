package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm
{
    // ////////////////////////////////////////////
    // This is the text that goes into the menus.
    String[] mainMenu = {"View crops", "Remove crop", "Add crop", "View animals", "Add animal", "Feed animal", "Remove animal", "Save", "Exit"};
    String[] cropMenu1 = {"Add crop", "Exit"};


    boolean animalAdded = false;
    int nextID;


    // /////////////////////////////////////
    // Our crops and animals are stored here
    ArrayList <Crop> listOfCrops = new ArrayList<>();       //   Crops: listOfCrops
    ArrayList <Animal> listOfAnimals = new ArrayList<>();   // Animals: listOfAnimals




    // ///////////////////////
    // Variables dealing with I/O (paths & filenames).
    File sourceFolder = new File("myLittleFolder"); // The source-folder from where the files will be Saved/Loaded
    File sourceFileCrops = new File("myLittleFolder/Crops.txt"); // Filename of the Crops-file
    File sourceFileAnimals = new File("myLittleFolder/Animals.txt"); // Filename of the Animals-file




    // /////////////////
    // Contructors

    // Empty Constructor
    public Farm()
    {
        // Check if file exists - if not, then make something up to populate the arraylist(s)
        if(!sourceFileCrops.exists())
        {
            DebugFillCrops();
            DebugFillAnimals();
        }
    }




    // ////
    // Menu
    public void MainMenu()
    {
        boolean keepLooping = true;
        boolean animalAdded = false;
        int userInput = 0;



        // //////////////////////////
        // The Main-Loop starts Here!
        while(keepLooping)
        {
            menuPrintItems(mainMenu);   // Display the Main menu
            userInput = GetInt(""); // Ask for user input


            switch(userInput)
            {
                case 1: ViewCrops(); break;
                case 2: RemoveCrop(); break;
                case 3: AddCrop(); break;
                case 4: ViewAnimals(); break;
                case 5: //animalAdded = AddAnimal(); break;
                case 6: FeedAnimal(); break;
                case 7: RemoveAnimal(); break;
                case 8: Save(); break;
                case 9: keepLooping = false; break;
                default: DoNothing(); break;

            } // switch(userInput)

            menuPrintLine2();           // ---------------------
        } // main while-loop

    } // MainMenu()


    private void ViewCrops(){}

    private void RemoveCrop(){}


    // Add Crop - The quick and easy version
    private void AddCrop(String typeOfCrop, int amountOfCrop)
    {
        NextID();
        Crop newCrop = new Crop(nextID, "CROP", typeOfCrop, amountOfCrop);
        listOfCrops.add(newCrop);

    }


    // Add Crop - The interactive version
        private void AddCrop()
    {
        int foundCrop = -1;
        int userInput = 0;
        String inputCropType = "";
        int inputCropQuantity = 0;



        // /////////
        // Crop menu                       // 1 Add crop, 2 Exit
        menuPrintItems(cropMenu1);         // Display the Crop menu
        userInput = GetInt("");     // Ask for user input
        if(userInput!=1){return;}          // Invalid answer or user chose Exit


        // Ask which crop
        inputCropType=GetString("Name the crop: ");
        if(inputCropType==""){return;}                       // Fail. User gave no answer.


        // Ask how much
        inputCropQuantity=GetInt("Enter amount: ");
        if(inputCropQuantity < 1) {return;}                    // Fail. User is a comedian.


        // Does the crop already exist?
        for(int i=0;i<listOfCrops.size();i++)
        {
            if(listOfCrops.get(i).getCropType().toLowerCase().equals(inputCropType)){foundCrop = i; break;}
        }


        if(foundCrop < 0) // Add new crop
        {
            NextID();
            Crop newCrop = new Crop(nextID, "CROP", inputCropType, inputCropQuantity);
            listOfCrops.add(newCrop);
        }
        else // Update crop quantity
        {
            int revisedQuantity = listOfCrops.get(foundCrop).getQuantity();
            listOfCrops.get(foundCrop).setQuantity(revisedQuantity + inputCropQuantity);
        }


        // Add to list
    }

    private void ViewAnimals(){}

    private boolean AddAnimal(String typeOfAnimal)
    {
        NextID();
        Animal newAnimal = new Animal(nextID, typeOfAnimal);
        listOfAnimals.add(newAnimal);
        return true;
    }


    private void FeedAnimal(){}

    private void RemoveAnimal(){}

    private void Save()
    {
        String thisLine="";


        try
        {
            FileWriter filewriter = new FileWriter(sourceFileCrops);
            BufferedWriter bf = new BufferedWriter(filewriter);

            File saveFolder = new File("MyLittleFolder"); // Local path
            if(!saveFolder.exists()){saveFolder.mkdir();} // Making sure the folder exists.

            {
                //TODO write two files - Crops and Animals
            }

            for(Entity ent : listOfAnimals)
            {
            }


        }
        catch (IOException ioe)
        {
            //oopsie
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



    // ///////////////////////////////////////////
    // These functions are being used by the menus

    private void menuPrintLine1(){System.out.println("=======================================");}
    private void menuPrintLine2(){System.out.println("---------------------------------------");}

    private void menuPrintItems(String[] menuItems)
    {
        int menuIndex = 1;
        menuPrintLine1();           // =====================
        for(String item : menuItems)
        {
            System.out.println(" " + menuIndex + " " + item);
            menuIndex++;
        }
        menuPrintLine1();           // =====================
    }



    // If there are no files to read, then we'll fill the barn with stuff
    private void DebugFillCrops()
    {
        AddCrop("Hay", 30);
        AddCrop("Walnuts", 60);
        AddCrop("Rye", 54);
        AddCrop("Seeds", 46);
    }

    private void DebugFillAnimals()
    {
        AddAnimal("Horse");
        AddAnimal("Squirrel");
        AddAnimal("Chicken");
        AddAnimal("Giraffe");
    }





    public void InitializeNextID()
    {
        int theID = 0;

        try{theID = listOfCrops.size() + listOfAnimals.size();}
        catch (Exception e) {DoNothing();}
        this.nextID = theID;
    }

    public int NextID()
    {
        return this.nextID++;
    }



}
