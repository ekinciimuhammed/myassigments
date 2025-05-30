import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Library class represents an item in the library inventory.
 * Each item can be of type Book, Toy, or Stationery.
 */
class Library<T>  extends ArrayList<T>{
    private T  Type;
    private T Name;
    private T authorName;
    private T barcode;
    private T price;
    private T toyColor;
    private T stationeryType;

    public Library(){
       super();
        
    }



public String getType() {
    return Type.toString(); 
}
public String getName() {
    return Name.toString();
}

public String getAuthorName() {
    return authorName.toString();
}

public String getBarcode() {
    return barcode.toString();
}

public String getPrice() {
    return price.toString();
}

public String getToyColor() {
    return toyColor.toString();
}

public String getStationeryType() {
    return stationeryType.toString();
}

    /**
     * Constructs a new Library item of type Book.
     *
     * @param Type       the type of the item (Book, Toy, or Stationery)
     * @param Name       the name of the item
     * @param authorName the author name of the book
     * @param barcode    the barcode of the item
     * @param price      the price of the item
     */
    public Library(T type, T name, T authorName, T barcode, T price) {
        this.Type = type;
        this.Name = name;
        this.authorName = authorName;
        this.barcode = barcode;
        this.price = price;
    }
    
    /**
     * Constructs a new Library item of type Toy.
     *
     * @param Type      the type of the item (Book, Toy, or Stationery)
     * @param Name      the name of the item
     * @param toyColor  the color of the toy
     * @param barcode   the barcode of the item
     * @param price     the price of the item
     * @param x         an additional parameter for distinguishing constructors
     */
    public Library(T Type, T Name, T toyColor, T barcode, T price, T x) {
        this.Type = Type;
        this.Name = Name;
        this.toyColor = toyColor;
        this.barcode = barcode;
        this.price = price;
    }
    /**
     * Constructs a new Library item of type Stationery.
     *
     * @param Type          the type of the item (Book, Toy, or Stationery)
     * @param Name          the name of the item
     * @param stationeryType the type of stationery
     * @param barcode       the barcode of the item
     * @param price         the price of the item
     * @param x             an additional parameter for distinguishing constructors
     * @param y             another additional parameter for distinguishing constructors
     */
    public Library(T Type, T Name, T stationeryType, T barcode, T price, T x, T y) {
        this.Type = Type;
        this.Name = Name;
        this.stationeryType = stationeryType;
        this.barcode = barcode;
        this.price = price;
    }
}

public class classroomdecorationcalculator

 {
   
    static Library<Library<String>> myDepo = new Library<>();

    public static void main(String[] args) {
        try {
            String inputname = args[0];
            String outputfile = args[1];
            BufferedReader reader = new BufferedReader(new FileReader(inputname));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile));
            DoItEverything(reader, writer);
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Performs various operations based on the input data read from the BufferedReader.
     * Processes each command line by line and writes results to the BufferedWriter.
     *
     * @param reader the BufferedReader used to read input data
     * @param writer the BufferedWriter used to write output data
     */

   private static void DoItEverything(BufferedReader reader,BufferedWriter writer){
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] mydata = line.split("\t");
                if (mydata[0].equals("ADD")) {
                    if (mydata[1].equals("Toy")) {
                       
                        Library<String> library = new Library<>(mydata[1], mydata[2], mydata[3], mydata[4], mydata[5], "3");
                        myDepo.add(library);
                        sortByType(myDepo);
                    } else if (mydata[1].equals("Book")) {
                        Library<String> library = new Library<>(mydata[1], mydata[2], mydata[3], mydata[4], mydata[5]);
                        myDepo.add(library);
                        sortByType(myDepo);
                    } else if (mydata[1].equals("Stationery")) {
                        Library<String> library = new Library<>(mydata[1], mydata[2], mydata[3], mydata[4], mydata[5], "3","4");
                        myDepo.add(library);
                        sortByType(myDepo);
                    }
                } else if (mydata[0].equals("DISPLAY")) {
                    display(myDepo, writer);
                } else if (mydata[0].equals("REMOVE")) {
                    removeBarcode(myDepo, mydata[1], writer);
                }
                else if(mydata[0].equals("SEARCHBYBARCODE")){
                    searchByBarcode(myDepo, mydata[1], writer);
                }
                else if (mydata[0].equals("SEARCHBYNAME")) {
                    searchByName(myDepo, mydata[1], writer);
                }
            }
          
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    /**
     * Displays the inventory items with their details.
     *
     * @param myDepo the ArrayList containing the inventory items
     * @param writer the BufferedWriter used to write the inventory details to an output file
     */
   private static void display(ArrayList<Library<String>> myDepo, BufferedWriter writer) {
        try {
            writer.write("INVENTORY:\n");
            for (Library<String> item : myDepo) {
                if (item.getType().equals("Book")) {
                    writer.write("Author of the " + item.getName() + " is " + item.getAuthorName() + ". Its barcode is " + item.getBarcode() + " and its price is " + formatPrice(item.getPrice()));
                    writer.write("\n");
                } else if (item.getType().equals("Toy")) {
                    writer.write("Color of the " + item.getName() + " is " + item.getToyColor() + ". Its barcode is " + item.getBarcode() + " and its price is " + formatPrice(item.getPrice()));
                    writer.write("\n");
                } else if (item.getType().equals("Stationery")) {
                    writer.write("Kind of the " + item.getName() + " is " + item.getStationeryType() + ". Its barcode is " + item.getBarcode() + " and its price is " + formatPrice(item.getPrice()));
                    writer.write("\n");
                }
                writer.flush();
            }
            writer.write("------------------------------\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes an item from the inventory based on its barcode.
     * If the item with the given barcode is found, it is removed from the inventory.
     *
     * @param myDepo  the ArrayList containing the inventory items
     * @param barcode the barcode of the item to be removed
     * @param writer  the BufferedWriter used to write the removal results to an output file
     */
   private  static void removeBarcode(ArrayList<Library<String>> myDepo, String barcode, BufferedWriter writer) {
        boolean found = false;
        for (int k = 0; k < myDepo.size(); k++) {
            if (myDepo.get(k).getBarcode().equals(barcode)) {
                found = true;
                myDepo.remove(k);
                break;
            }
        }
        try {
            if (found) {
                writer.write("REMOVE RESULTS:\nItem is removed.\n------------------------------\n");
            } else {
                writer.write("REMOVE RESULTS:\nItem is not found.\n------------------------------\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Searches for an item in the inventory based on its barcode.
     * If an item with the given barcode is found, its details are written to the output file.
     *
     * @param myDepo  the ArrayList containing the inventory items
     * @param barcode the barcode of the item to search for
     * @param writer  the BufferedWriter used to write the search results to an output file
     */
   private static void searchByBarcode(ArrayList<Library<String>> myDepo, String barcode, BufferedWriter writer){
        boolean found = false;
        int index=0;
        for (int k = 0; k < myDepo.size(); k++) {
            if (myDepo.get(k).getBarcode().equals(barcode)) {
                found = true;
                index=k;
                break;
            }
        }
        try {
            if (found) {
                writer.write("SEARCH RESULTS:\n");
                if (myDepo.get(index).getType().equals("Book")) {
                    writer.write("Author of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getAuthorName() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                } else if (myDepo.get(index).getType().equals("Toy")) {
                    writer.write("Color of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getToyColor() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                } else if (myDepo.get(index).getType().equals("Stationery")) {
                    writer.write("Kind of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getStationeryType() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                }
                writer.flush();
                writer.write("------------------------------\n");
                writer.flush();
            } else {
                writer.write("SEARCH RESULTS:\n" + //
                                        "Item is not found.\n" + //
                                        "------------------------------\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Searches for an item in the inventory based on its name.
     * If an item with the given name is found, its details are written to the output file.
     *
     * @param myDepo the ArrayList containing the inventory items
     * @param Name   the name of the item to search for
     * @param writer the BufferedWriter used to write the search results to an output file
     */
   private static void searchByName(ArrayList<Library<String>> myDepo, String Name, BufferedWriter writer){
        boolean found = false;
        int index=0;
        for (int k = 0; k < myDepo.size(); k++) {
            if (myDepo.get(k).getName().equals(Name)) {
                found = true;
                index=k;
                break;
            }
        }
        try {
            if (found) {
                writer.write("SEARCH RESULTS:\n");
                if (myDepo.get(index).getType().equals("Book")) {
                    writer.write("Author of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getAuthorName() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                } else if (myDepo.get(index).getType().equals("Toy")) {
                    writer.write("Color of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getToyColor() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                } else if (myDepo.get(index).getType().equals("Stationery")) {
                    writer.write("Kind of the " + myDepo.get(index).getName() + " is " + myDepo.get(index).getStationeryType() + ". Its barcode is " + myDepo.get(index).getBarcode() + " and its price is " + formatPrice(myDepo.get(index).getPrice()));
                    writer.write("\n");
                }
                writer.flush();
                writer.write("------------------------------\n");
                writer.flush();}
            else if(!found){
                writer.write("SEARCH RESULTS:\n" + //
                                        "Item is not found.\n" + //
                                        "------------------------------\n");
            }
        } catch (Exception e) {
        }
    }
   /**
     * Sorts the list of Library objects by their types and barcodes.
     * Books are sorted first, followed by Toys, and then Stationery items.
     * Within each type, items are sorted by their barcode numbers.
     *
     * @param list the ArrayList of Library objects to be sorted
     */
   private static void sortByType(ArrayList<Library<String>>  list) {
        Collections.sort(list, new Comparator<Library<String>>() {
            public int compare(Library<String> lib1, Library<String> lib2) {
                // Compare items based on their barcode numbers
                int barcodeCompare = Integer.compare(Integer.parseInt(lib1.getBarcode()), Integer.parseInt(lib2.getBarcode()));
                // If items are of the same type, sort them based on their barcode numbers
                if (lib1.getType().equals(lib2.getType())) {
                    return barcodeCompare;
                } else {
                   // If items are of different types, sort them based on their types
                    if (lib1.getType().equals("Book")) {
                        return -1; // Books come first
                    } else if (lib1.getType().equals("Toy") && lib2.getType().equals("Stationery")) {
                        return -1; // Toys come before Stationery items
                    } else {
                        return 1; // Stationery items come last
                    }
                }
            }
        });
    }
   /**
     * Formats the price string by removing trailing zeros after the decimal point.
     * If the price contains a decimal point, it removes trailing zeros from the fractional part.
     * If the price does not contain a decimal point, it adds ".0" to the end of the price string.
     *
     * @param price the price string to be formatted
     * @return the formatted price string
     */
  private static String formatPrice(String price){
        if(price.contains(".")){
        String[] mydata=price.split("\\.");
        ArrayList<String>mynewprice=new ArrayList<>();
        String[] afterdot=mydata[1].split("");
        for(String u:afterdot){
            mynewprice.add(u);
        }
        for(int i=mynewprice.size()-1;i>0;i--){
            if(mynewprice.get(i).equals("0")){
                mynewprice.remove(i);
            }
            else{
                break;
            }
        }
        String dotAfter="";
        for(int i=0;i<mynewprice.size();i++){
            dotAfter=dotAfter+mynewprice.get(i);
        }
        return mydata[0]+"."+dotAfter;
        }
        else{
            price=price+".0";
            return price;
        }
    }
}
