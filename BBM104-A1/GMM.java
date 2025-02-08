import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents the slots in the gym meal machine.
 * It includes attributes such as name, price, protein, carbohydrates, fat, calorie, and quantity.
 */

class Slots {  
    String name;
    double price;
    double protein;
    double karbonhidrat;
    double fat;
    double calorie;
    int quantity;
   /**
     * Constructor to initialize the slot with the given attributes.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param protein The protein content of the item.
     * @param carbohydrates The carbohydrate content of the item.
     * @param fat The fat content of the item.
     * @param quantity The quantity content of item
     */
   
    public Slots(String name, double price, double protein, double karbonhidrat, double fat) { 
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.karbonhidrat = karbonhidrat;
        this.fat = fat;
        this.calorie = (9 * fat) + (4 * protein) + (4 * karbonhidrat);
        this.quantity = 0;
        
    }
}
/**
 * This class represents the Gym Meal Machine and provides methods to interact with it.
 */

public class GMM  
{
    public static void main(String[] args) { 
        String outputfile=args[2];
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(outputfile))){ 
            if (args.length < 3) {
                System.out.println("Usage: java GMM inputfile1 inputfile2 outputfile"); 
                return;
            }
            String Product_List_Name = args[0];  
            BufferedReader reader = new BufferedReader(new FileReader(Product_List_Name));

            String Purchase_list_name = args[1];
            BufferedReader reader2 = new BufferedReader(new FileReader(Purchase_list_name));

            Slots[][] slotMachine = new Slots[6][4]; 

          
            LoadingSlotMachine(slotMachine, reader,writer); 

         
            WriteSlotMachine(slotMachine,writer);

            PurchaseItemsFromSlotMachine(slotMachine,reader2,writer);

            WriteSlotMachine(slotMachine,writer);

            reader2.close();
            reader.close();
    

        } catch (Exception e) { // ıf you cath any exceğtıon, it will work
            e.printStackTrace();
        }
    }


     /**
     * Loads the Gym Meal Machine with items from the input file.
     * @param slotMachine The array representing the slot machine.
     * @param reader The BufferedReader to read the input file.
     * @param writer The BufferedWriter to write output messages.
     */

    public static int LoadingSlotMachine(Slots[][] slotMachine, BufferedReader reader,BufferedWriter writer) {
        try {
            String line;
             // Read each line from the input file
            while ((line = reader.readLine()) != null) {  
                // Split the line into different attributes
                String[] data = line.split("\t"); // Fiyat ve ismi ayır
                String[] otherData = data[2].split(" "); // Diğer özellikleri ayır
                // Create a new slot item
                Slots newItem = new Slots(data[0], Double.parseDouble(data[1]),  // ı fetch data from input file
                        Double.parseDouble(otherData[0]), Double.parseDouble(otherData[1]),
                        Double.parseDouble(otherData[2]));
                int totaluzunluk=0;
                for(Slots[] slots:slotMachine){ // for loop to travel on slotMachine
                    for(Slots slot:slots){
                        if(slot!=null){
                            totaluzunluk=totaluzunluk+slot.quantity;

                        }

                    }
                }
                boolean slotFound = false; // Find an empty slot to place the new item

                for (int row = 0; row < slotMachine.length; row++) {
                    for (int col = 0; col < slotMachine[row].length; col++)
                    {
                        if (slotMachine[row][col] == null)
                        {
                            slotMachine[row][col] = newItem;
                            newItem.quantity++;
                            slotFound = true;
                            break;
                        }
                        if ((slotMachine[row][col].name.equals(newItem.name) && slotMachine[row][col].quantity < 10))
                        {
                            slotMachine[row][col].quantity++;
                            slotFound = true;
                            break;
                        }

                    }

                    if (slotFound) {
                        break;
                    }
                }
                if(!slotFound){                                                                     // If no slot is available, write an info message
                    writer.write("INFO: There is no available place to put "+data[0]);  
                    writer.newLine();
                    if(totaluzunluk==240){
                        fill(writer);
                        break;
                    }

                }


            }



        } catch (Exception e) {   //if you catch any exception ıt will work
            e.printStackTrace();
        }
        return 0;
    }

/**
 * 
 * @param writer The writer represent ownselfs.
 */

    public static int fill(BufferedWriter writer){ //if total capacity reacher its own maxcapacıty , it will work.
        try {
            writer.write("INFO: The machine is full!");
            writer.newLine();
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }
     /**
     * Writes the current state of the Gym Meal Machine to the output file.
     * @param slotMachine The array representing the slot machine.
     * @param writer The BufferedWriter to write the output file.
     */

    public static void WriteSlotMachine(Slots[][] slotMachine,BufferedWriter writer) { 
        try {
            writer.write("-----Gym Meal Machine-----");
            for (int al = 0; al < 6; al++) {
                writer.newLine();
                for (int fer = 0; fer < 4; fer++) {

                    if (slotMachine[al][fer] != null&&slotMachine[al][fer].quantity != 0) {

                        writer.write(slotMachine[al][fer].name + "(" + Math.round(slotMachine[al][fer].calorie) + ", "
                                + slotMachine[al][fer].quantity + ")___");

                    } else {
                        writer.write("___(0, 0)___");
                    }

                }
            }
            writer.newLine();
            writer.write("----------");
            writer.newLine();


        } catch (IOException e) {

        }
    }

    /**
 * Handles the purchase of items from the Gym Meal Machine based on user input.
 * @param slotMachine The array representing the slot machine.
 * @param reader The BufferedReader to read user input.
 * @param writer The BufferedWriter to write output messages.
 */


    public static void PurchaseItemsFromSlotMachine(Slots[][] slotMachine, BufferedReader reader2,BufferedWriter writer) { // I will Purchase Items from my Slotmachines
        try {
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                String[] data2 = line2.split("\t"); // Split the input line by tab.
                // Separate the elements from the input line.
                String choice = data2[2];
                int value = Integer.parseInt(data2[3]);
                String[] moneys = data2[1].split(" ");
                // Calculate the total money provided by the user.
                int totalmoney = 0;
                for (String money : moneys) {
                    totalmoney = totalmoney + Integer.parseInt(money);
                }
                boolean found = false; // Flag to track if the item is found in the slot
                //Determine the purchase type and process accordingly.
                if (choice.equals("PROTEIN")) { 
                    for (Slots[] slots : slotMachine) {

                        for (Slots slot : slots) {

                            if ((slot != null) && ((slot.protein >= value - 5) && (slot.protein <= value + 5)) && slot.quantity >= 1 && (totalmoney >= slot.price)) {
                                slot.quantity--;
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("PURCHASE: You have bought one "+slot.name);
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney-slot.price)+" TL");
                                writer.newLine();
                                break;
                            }
                            else if ((slot != null) && ((slot.protein >= value - 5) && (slot.protein <= value + 5)) && slot.quantity >= 1 && (totalmoney < slot.price)) {
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("INFO: Insufficient money, try again with more money.");
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                                writer.newLine();
                                break;
                            }
                            if(found){
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if(found==false){
                        writer.write("INPUT: "+line2);
                        writer.newLine();
                        writer.write("INFO: Product not found, your money will be returned.");
                        writer.newLine();
                        writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                        writer.newLine();

                    }

                } else if (choice.equals("CALORIE")) { //if he want to buy according to CALORİE
                    for (Slots[] slots : slotMachine) {

                        for (Slots slot : slots) {

                            if ((slot != null) && ((slot.calorie >= value - 5) && (slot.calorie <= value + 5)) && slot.quantity >= 1 && (totalmoney >= slot.price)) {
                                slot.quantity--;
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("PURCHASE: You have bought one "+slot.name);
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney-slot.price)+" TL");
                                writer.newLine();
                                break;
                            }
                            else if ((slot != null) && ((slot.calorie >= value - 5) && (slot.calorie <= value + 5)) && slot.quantity >= 1 && (totalmoney < slot.price)) {
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("INFO: Insufficient money, try again with more money.");
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                                writer.newLine();
                                break;
                            }
                            if(found){
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }}
                    if(found==false){
                        writer.write("INPUT: "+line2);
                        writer.newLine();
                        writer.write("INFO: Product not found, your money will be returned.");
                        writer.newLine();
                        writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                        writer.newLine();

                    }

                } else if (choice.equals("FAT")) { //if he want to buy according to FAT
                    for (Slots[] slots : slotMachine) {

                        for (Slots slot : slots) {

                            
                            if ((slot != null) && ((slot.fat >= value - 5) && (slot.fat <= value + 5)) && slot.quantity >= 1 && (totalmoney >= slot.price)) {
                                slot.quantity--;
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("PURCHASE: You have bought one "+slot.name);
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney-slot.price)+" TL");
                                writer.newLine();
                                break;
                            }
                            else if ((slot != null) && ((slot.fat >= value - 5) && (slot.fat <= value + 5)) && slot.quantity >= 1 && (totalmoney < slot.price)) {
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("INFO: Insufficient money, try again with more money.");
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                                writer.newLine();
                                break;
                            }
                            if(found){
                                break;
                            }
                        }

                        if (found) {
                            break;
                        }}
                    if(found==false){
                        writer.write("INPUT: "+line2);
                        writer.newLine();
                        writer.write("INFO: Product not found, your money will be returned.");
                        writer.newLine();
                        writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                        writer.newLine();


                    }

                } else if (choice.equals("CARB")) { //if he want to buy according to CARBONHİDRAT
                    for (Slots[] slots : slotMachine)
                    {

                        for (Slots slot : slots)
                        {


                            if ((slot != null) && ((slot.karbonhidrat >= value - 5) && (slot.karbonhidrat <= value + 5)) && slot.quantity >= 1 && (totalmoney >= slot.price))
                            {
                                slot.quantity--;
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("PURCHASE: You have bought one "+slot.name);
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney-slot.price)+" TL");
                                writer.newLine();
                                break;
                            }
                            else if ((slot != null) && ((slot.karbonhidrat >= value - 5) && (slot.karbonhidrat <= value + 5)) && slot.quantity >= 1 && (totalmoney < slot.price))
                            {
                                found = true;
                                writer.write("INPUT: "+line2);
                                writer.newLine();
                                writer.write("INFO: Insufficient money, try again with more money.");
                                writer.newLine();
                                writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                                writer.newLine();
                                break;
                            }

                            if (found)
                            {
                                break;
                            }
                        }
                        if(found){
                            break;
                        }
                    }

                    if(found==false){
                        writer.write("INPUT: "+line2);
                        writer.newLine();
                        writer.write("INFO: Product not found, your money will be returned.");
                        writer.newLine();
                        writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                        writer.newLine();


                    }

                } else if (choice.equals("NUMBER")) { //if he want to buy according to NUMBERS
                    if (value < 24) {
                        int row = value / 4;
                        int column = value % 4;
                        Slots myItem = slotMachine[row][column];
                        if(myItem==null){
                            writer.write("INPUT: "+line2);
                            writer.newLine();
                            writer.write("INFO: This slot is empty, your money will be returned.");
                            writer.newLine();
                            writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                            writer.newLine();

                        }
                        else if(myItem.quantity==0){
                            writer.write("INPUT: "+line2);
                            writer.newLine();
                            writer.write("INFO: This slot is empty, your money will be returned.");
                            writer.newLine();
                            writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                            writer.newLine();

                        }
                        else if (myItem != null && totalmoney > myItem.price&&myItem.quantity>0) {
                            myItem.quantity--;
                            writer.write("INPUT: "+line2);
                            writer.newLine();
                            writer.write("PURCHASE: You have bought one "+slotMachine[row][column].name);
                            writer.newLine();
                            writer.write("RETURN: Returning your change: "+Math.round(totalmoney-slotMachine[row][column].price)+" TL");
                            writer.newLine();
                        } else {
                            writer.write("INPUT: "+line2);
                            writer.newLine();
                            writer.write("INFO: Insufficient money, try again with more money.");
                            writer.newLine();
                            writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                            writer.newLine();
                        }
                    } else {
                        writer.write("INPUT: "+line2);
                        writer.newLine();
                        writer.write("INFO: Number cannot be accepted. Please try again with another number.");
                        writer.newLine();
                        writer.write("RETURN: Returning your change: "+Math.round(totalmoney)+" TL");
                        writer.newLine();
                    }
                }
            }
        } catch (Exception e) {  // İF ANY EXCEPTİON OCCURS, İT WİLL WORK ON MY PROJECT
            e.printStackTrace();
        }
    }
}