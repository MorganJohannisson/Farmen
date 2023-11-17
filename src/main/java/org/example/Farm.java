package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm
{
    // ////////////////////////////////////////////
    // This is the text that goes into the menus.
    String mainMenu = "1 View crops\n2 Add crop\n3 Remove crop\n4 View animals\n5 Add animal\n6 Remove animal\n7 Feed animal\n8 Save\n0 Exit";
    boolean animalAdded = false;
    int nextID;

    // /////////////////////////////////////
    // Our crops and animals are stored here
    ArrayList <Crop> listOfCrops = new ArrayList<>();       //   Crops: listOfCrops
    ArrayList <Animal> listOfAnimals = new ArrayList<>();   // Animals: listOfAnimals

    // ///////////////////////
    // Variables dealing with I/O (paths & filenames).
    String fileFolder = "myLittleFolder";                               // This is the Save-folder
    File fileCrops = new File("myLittleFolder/Crops.txt");     // Filename of the Crops-file
    File fileAnimals = new File("myLittleFolder/Animals.txt"); // Filename of the Animals-file




    // /////////////////
    // Contructors
    public Farm()
    {
        // Check if file(s) exists
        boolean cropsExists = fileCrops.exists();
        boolean animalsExists = fileAnimals.exists();

        // If file(s) can't be found, then make something up to populate the arraylist(s)
        if(!cropsExists)
        {
            // There are no crops to read, so we'll just fill the farm with some stuff
            System.out.print("No crop-file detected - filling the farm with default crops...");
            AddCrop(1, "Grass", "Hay", 30);
            AddCrop(2, "Nuts", "Walnuts", 60);
            AddCrop(3, "Meat", "Dragon T-bone steak", 44);
            System.out.println("Done.");
        }

        if(!animalsExists)
        {
            // There are no animals to read, so we'll just fill the farm with some stuff
            System.out.print("No animal-file detected - filling the farm with default animals...");
            AddAnimal(4, "Workhorse", "Horse");
            AddAnimal(5, "Pest-control","Cat");
            AddAnimal(6, "Mount","Hippogriff");
            System.out.println("Done.");
        }

        /////////////////////////////////
        // File I/O and the setting of nextID to: the highest stored ID + 1

        Load(cropsExists, animalsExists); // The Load-function will fetch the existing file, indicated by the booleans.
        InitializeNextID(); // Search the whole collection of crops and animals and set nextID to the highest ID + 1


    } // Farm()     // (constructor)




    // ////
    // MainMenu
    public void MainMenu()
    {
        boolean keepLooping = true;
        int userInput = 0;


        // //////////////////////////
        // The Main-Loop starts Here!
        while(keepLooping)
        {
            printLine1();
            System.out.println(mainMenu); // Display the Main menu
            printLine1();

            userInput = AskForInt(""); // Ask for user input
            // System.out.println("\n\n");
            switch(userInput)
            {
                case 1: ViewCrops(); break;
                case 2: AddCropMenu(); break;
                case 3: RemoveCrop(); break;
                case 4: ViewAnimals(); break;
                case 5: AddAnimalMenu(); break;
                case 6: RemoveAnimal(); break;
                case 7: FeedAnimalMenu(); break;
                case 8: Save(); break;
                case 0: keepLooping = false; break;
                default: DoNothing(); break;
            } // switch(userInput)
        } // main while-loop

    } // MainMenu()


    private void DoNothing(){} // This place-holder function is basically a better alternative to a blank line.

    private void ViewCrops()
    {
        System.out.println("  View crops");
        for(Crop c : listOfCrops) {System.out.println(c.GetDescription());}

    }

    private void ViewAnimals()
    {
        System.out.println("  View animals");
        for(Animal a : listOfAnimals) {System.out.println(a.GetDescription());}

    }

/*I FeedAnimal skall användaren först välja en Crop och sedan en Animal. Skicka in denna Crop till den
valda Animals Feed-funktion, där quantity av hur många av den Crop som finns minskas med 1. Om
det inte finns några (crop quantity är 0) så bör ett meddelande skrivas ut att det inte gick att äta
några fler för det fanns inga.*/

    private void FeedAnimal(int listIndexCrop, int listIndexAnimal)
    {
        int amountOfCrop = listOfCrops.get(listIndexCrop).getQuantity(); // This variable will help declutter the code for decreasing the feed
        if(amountOfCrop < 1) {System.out.println("\nThat particular feed is out of stock.\n\n"); return;}

        System.out.println("\nThe " + listOfAnimals.get(listIndexAnimal).getSpecies() + " ate some " + listOfCrops.get(listIndexCrop).getCropType() + ".\n\n");
        listOfCrops.get(listIndexCrop).setQuantity(amountOfCrop - 1);
    }


    private void FeedAnimalMenu()
    {
        // This function gathers information from the user, which is used in a call to
        //  the FeedAnimal(int, int) function.

        System.out.println("  Feed animal");
        System.out.println("\n\nFeeding an animal:\n" +
                "You'll need to pick a crop and an animal using their ID.\n" +
                "(Make use of the View-functions in the main menu)");

        int listIndexCrop = -1;     // The index (in the arraylist) of the
        int listIndexAnimal = -1;   //  crop and animal respectively.
        int userInput = 0;          // User-input is being stored here


        // Let's find that crop - and exit if there's no crop left, or if the user is being silly
        userInput = AskForInt("Pick a crop by its ID: ");

        // Check if the ID exists
        for(int i = 0;i<listOfCrops.size();i++){if(listOfCrops.get(i).getId()==userInput){listIndexCrop = i;}}
        if(listIndexCrop == -1) {System.out.println("\nNo crop with that ID exists.\n\n"); return;}


        // Let's pick the animal - and exit if the user is not cooperating
        userInput = AskForInt("Pick an animal by its ID: ");

        // Check if the ID exists
        for(int i = 0;i<listOfAnimals.size();i++){if(listOfAnimals.get(i).getId()==userInput){listIndexAnimal = i;}}
        if(listIndexAnimal==-1){System.out.println("\nNo animal with that ID exists.\n\n"); return;}


        //If we're here, then we have everything we need to attempt to feed an animal
        FeedAnimal(listIndexCrop, listIndexAnimal);

    }
    private void RemoveAnimal()
    {
        System.out.println("  Remove animal");
        ViewAnimals();
        int userInput = AskForInt("Please enter the ID of the animal to remove: ");
        for(int i = 0;i<listOfAnimals.size();i++)
        {
            if(listOfAnimals.get(i).getId()==userInput)
            {
                System.out.print("Removing " + listOfAnimals.get(i).getSpecies() + "... ");
                listOfAnimals.remove(i);
                System.out.println("Done.\n");
                return;
            }
        }
        // If we've reached this far, then the crop doesn't exist.
        System.out.println("No such animal.\n");
    }
    private void RemoveCrop()
    {
        System.out.println("  Remove crop");
        ViewCrops();
        int userInput = AskForInt("Please enter the ID of the crop to remove: ");
        for(int i = 0;i<listOfCrops.size();i++)
        {
            if(listOfCrops.get(i).getId()==userInput)
            {
                System.out.print("Removing " + listOfCrops.get(i).getCropType() + "... ");
                listOfCrops.remove(i);
                System.out.println("Done.\n");
                return;
            }
        }
        // If we've reached this far, then the crop doesn't exist.
        System.out.println("No such crop.\n");
    }


    // Add Crop - The quick and easy versions
    private void AddCrop(int id, String name, String typeOfCrop, int amountOfCrop)
    {

        Crop newCrop = new Crop(id, name, typeOfCrop, amountOfCrop);
        listOfCrops.add(newCrop);

    }


    // Add Crop - The interactive version
    private void AddCropMenu()
    {
        int foundCrop = -1;
        int inputID = -1;
        String inputName = "";
        String inputCropType = "";
        int inputCropQuantity = 0;

        //ViewCrops();

        // CropMenu with user-input:
        // Step 1  - Ask user which crop and how much
        // Step 2  - Check if crop already exists
        // Step 3a - Fill up an existing crop with the given amount
        // Step 3b - Add a new crop into list, along with the amount
        // /////////

        System.out.println("  Add crop");

        // //////
        // Step 1 - Ask user which crop and how much
        inputCropType = AskForString("Name a new crop to be added or \nenter the ID of an existing crop to increase: ");     // Ask which crop
        if(inputCropType==""){return;}                         // Fail. User gave no answer.
        try{inputID=Integer.parseInt(inputCropType);}   // Let's try to convert this string to an int.
        catch(Exception e){inputID=-1;}                              // Fail. The string doesn't contain a number.

        inputCropQuantity= AskForInt("Enter amount: ");     // Ask how much
        if(inputCropQuantity < 1) {return;}                    // Fail. User is a comedian.


        // //////
        // Step 2 - Check if crop already exists
        if(inputID >- 1)
        {
            // user entered a number - Let's see if there´s an id that matches that number
            for (int i = 0; i < listOfCrops.size(); i++)
            {
                if (listOfCrops.get(i).getId() == inputID)
                {
                    foundCrop = i;  // A match was found - user entered the ID of an existing crop.
                    break;
                }
            }

            //If user entered an ID that doesn't exist, then we'll just inform the user and exit.
            if (inputID > -1 && foundCrop < 0) {
                System.out.println("\nThere is no crop with that ID."); //
                return;                                                 // Fail! Let's get out of here.
            }
        }


        // Step 3 (a and b)
        if(foundCrop > -1) // Step 3a - Fill up the existing crop with the given amount.
        {
            int newQuantity = listOfCrops.get(foundCrop).getQuantity() + inputCropQuantity;
            listOfCrops.get(foundCrop).setQuantity(newQuantity);
            System.out.println("The amount of " + inputCropType + " have been set to " + inputCropQuantity);
        }
        else // Step 3b - Add new crop into list, along with the amount.
        {
            inputName = AskForString("What type of crop is this?\n(seed, grass, peas, fruit ...)");
            if(inputName==""){ return;} // Fail. User gave no answer.

            IncrementNextID();
            Crop newCrop = new Crop(nextID, inputName, inputCropType, inputCropQuantity);
            listOfCrops.add(newCrop);
            System.out.println("A new crop, " + inputCropType + ", have been added.");
        }




    } // AddCropMenu()



    private void AddAnimalMenu()
    {
        System.out.println("  Add animal");

        int foundAnimal = -1;
        String inputName = "";
        String inputAnimalType = "";

        ViewAnimals();

        inputAnimalType= AskForString("Name the Animal: ");     // Ask which Animal
        if(inputAnimalType==""){return;}                         // Fail. User gave no answer.

        inputName = AskForString("What type of Animal is this?\n(cattle, bird, pet, pest ...)");
        if(inputName==""){return;} // Fail. User gave no answer.

        IncrementNextID();
        Animal newAnimal = new Animal(nextID, inputName, inputAnimalType);
        listOfAnimals.add(newAnimal);
        System.out.println("A new animal, " + inputAnimalType + ", have been added.");

    } // AddAnimalMenu()

    private boolean AddAnimal(int id, String name, String typeOfAnimal)
    {
        Animal newAnimal = new Animal(id, name, typeOfAnimal);
        listOfAnimals.add(newAnimal);

        return true;
    }

    private boolean AddAnimal(String name, String typeOfAnimal)
    {
        IncrementNextID();
        Animal newAnimal = new Animal(nextID, name, typeOfAnimal);
        listOfAnimals.add(newAnimal);
        return true;
    }






    // Functions for User input below

    private String AskForString(String prompt)
    {
        String returnValue = "";
        System.out.println("\n" + prompt);
        Scanner sc = new Scanner(System.in);
        returnValue = sc.nextLine().trim();

        if(returnValue==""){System.out.println("No answer was given.");}

        return returnValue;
    }

    private int AskForInt(String prompt)
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






    // ///////////////////////////////////////////
    // These functions are being used by the menus

    private void printLine1(){System.out.println("=======================================");}
    private void printLine2(){System.out.println("---------------------------------------");}



    public void InitializeNextID()
    {
        for(Crop c : listOfCrops){if(nextID <= c.getId()){nextID=c.getId() + 1;}}
        for(Animal a : listOfAnimals){if(nextID<= a.getId()){nextID=a.getId();} }
    }

    public void IncrementNextID()
    {
        nextID++;
    }


    public void Load(boolean readCrops, boolean readAnimals)
    {
        if(readCrops)   // Let's read the crops-file
        {
            System.out.print("\nAdding crops: ");
            try
            {
                String currentLine = "";

                // Preparing File I/O for Crops
                FileReader fileReaderCrops = new FileReader(fileCrops);
                BufferedReader brCrops = new BufferedReader(fileReaderCrops);

                currentLine = brCrops.readLine();   // Should be something like: "1,seed,Sunflower seeds,123"
                while(currentLine != null)          // Checking if we actually read something (as in not-null).
                {
                    if(currentLine != "")           // Checking if we read a non-empty line (as in not "").
                    {
                        String[] cropVariables = currentLine.split(",");
                        if(cropVariables.length == 4)   // Checking if we read four values, separated by commas ("1,2,3,4").
                        {
                            int cID = Integer.parseInt(cropVariables[0]);
                            //if(cID>=nextID){nextID=cID+1;}
                            String cName = cropVariables[1];
                            String cType = cropVariables[2];
                            int cQuantity = Integer.parseInt(cropVariables[3]);
                            AddCrop(cID, cName, cType, cQuantity );
                            System.out.print("'" + cType + "' ");
                        }

                    }
                    currentLine = brCrops.readLine();
                }
                System.out.println("");
                brCrops.close();
            }
            catch (IOException ioException)
            {
                System.out.println("The attempt to load the crops-file failed.");
                DoNothing();
            }

        }

        ///////////////////////
        if(readAnimals)
        {
            System.out.println("Adding animals: ");
            try
            {
                String currentLine = "";

                // Preparing File I/O for Animals
                FileReader fileReaderAnimals = new FileReader(fileAnimals);
                BufferedReader brAnimals = new BufferedReader(fileReaderAnimals);

                currentLine = brAnimals.readLine(); //Should be something like: "1,Cattle,Cow"
                while(currentLine != null)
                {
                    if(currentLine != "")
                    {
                        String[] animalVariables = currentLine.split(",");
                        if(animalVariables.length == 3)
                        {
                            int cID = Integer.parseInt(animalVariables[0]);
                            //if(cID>=nextID){nextID=cID+1;}                  // Keeps the NextID valid
                            String cName = animalVariables[1];
                            String cSpecies = animalVariables[2];
                            if(AddAnimal(cID, cName, cSpecies))
                            {
                                System.out.println("'" + cSpecies + "' ");
                            }
                        }

                    }
                    currentLine = brAnimals.readLine();
                }
                System.out.println("");
                brAnimals.close();
            }
            catch (IOException ioException)
            {
                System.out.println("The attempt to load the Animals-file failed.");
                DoNothing();
            }

        }

    }


    private void Save()
    {
        String currentLine = "";
        System.out.println("\nPreparing to save files 'Crop.txt' and 'Animals.txt'");

        try
        {
            // Making sure the folder exists.
            // If it doesn't - and if we can't create it, then we tell the user and return to main menu.
            File saveFolder = new File(fileFolder); // Local path
            if(!saveFolder.exists())
            {
                boolean success = false;
                System.out.print("Attempting to create folder " + fileFolder + "... ");
                success=saveFolder.mkdir();
                if(success) {System.out.println("OK");}
                else        {System.out.println("Failed"); return;}
            }

            // Preparing File I/O for Crops
            FileWriter fileWriterCrops = new FileWriter(fileCrops);
            BufferedWriter bwCrops = new BufferedWriter(fileWriterCrops);

            // Preparing File I/O for Animals
            FileWriter fileWriterAnimals = new FileWriter(fileAnimals);
            BufferedWriter bwAnimals = new BufferedWriter(fileWriterAnimals);

            // Saving files
            for(Crop crop : listOfCrops)
            {
                currentLine = crop.GetCSV();
                bwCrops.write(currentLine);
                bwCrops.newLine();
            }
            bwCrops.close();

            // Write 'Animals.txt
            for(Animal animal : listOfAnimals)
            {
                currentLine = animal.GetCSV();
                bwAnimals.write(currentLine);
                bwAnimals.newLine();
            }
            bwAnimals.close();
        }
        catch (IOException ioe)
        {
            DoNothing();
        }
    }

}
