import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class FORTRY {
    /**
     * This class represents a FOR-TRY production in a BNF grammar. It contains a
     * left-hand side symbol (letter) and a right-hand side vocabulary (Vocabulary).
     * 
     * @param letter     the left-hand side symbol of the FOR-TRY production
     * @param vocabulary the right-hand side vocabulary of the FOR-TRY production
     */
    private String letter;
    private String Vocabulary;
    public String getLetter() {
        return letter;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }
    public String getVocabulary() {
        return Vocabulary;
    }
    public void setVocabulary(String vocabulary) {
        Vocabulary = vocabulary;
    }
    public FORTRY(String letter, String vocabulary) {
        this.letter = letter;
        Vocabulary = "(" + vocabulary + ")";
    }
}
public class BNF {
    /**
     * This is the main method of the BNFParser class. It takes input from the user,
     * reads the input file, creates an ArrayList of FORTRY objects, finds the index
     * of the
     * S symbol in the ArrayList, and then replaces all occurrences of the S
     * symbol's
     * vocabulary with the new vocabulary. Finally, it writes the output to a file.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            // create a buffered reader to read from the input file
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            // create a buffered reader to read from the input file
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
            System.out.println(args.length);
            if (args.length != 2) {
                BufferedWriter writer2=new BufferedWriter(new FileWriter("ERROR.txt"));
                writer2.write("ERROR: This program works exactly with two command line arguments, the first one is the path to the input file whereas the second one is the path to the output file. Sample usage can be as follows: \"java8 BNFParser input.txt output.txt\". Program is going to terminate!");
                writer2.flush();
                writer2.close();
                return; 
            }
            else{
                String line;
                // read the input file line by line
                ArrayList<FORTRY> myArrayList = new ArrayList<FORTRY>();
                while ((line = reader.readLine()) != null) {
                    // split the line into two parts at the "->" symbol
                    String[] mydata = line.split("->");
                    // create a new FORTRY object with the two parts
                    FORTRY obj = new FORTRY(mydata[0], mydata[1]);
                    // add the object to the ArrayList
                    myArrayList.add(obj);
                }
                int k = 0;
                // find the index of the S symbol in the ArrayList
                for (int u = 0; u < myArrayList.size(); u++) {
                    if (myArrayList.get(u).getLetter().equals("S")) {
                        k = u;
                    }
                }
                // get the new vocabulary from the S symbol's FORTRY object
                String newVocabulary = myArrayList.get(k).getVocabulary();
                writer.write(ChangeVocabulary(myArrayList, newVocabulary));
                // flush and close the streams
                writer.flush();
                writer.close();
                reader.close();
            }
        }
        
        
        catch (Exception e) {
        }

    }
    /**
     * This method takes an ArrayList of FORTRY objects and a new vocabulary, and
     * replaces all occurrences of the S symbol's vocabulary with the new
     * vocabulary.
     * It then returns the updated BNF grammar.
     * 
     * @param myArrayList   the ArrayList of FORTRY objects
     * @param newVocabulary the new vocabulary
     * @return the updated BNF grammar
     */
    public static String ChangeVocabulary(ArrayList<FORTRY> myArrayList, String newVocabulary) {
        if (newVocabulary.equals(newVocabulary.toLowerCase())) {
            return newVocabulary;
        }
        char[] characters = newVocabulary.toCharArray();
        String degisecekharf = "";
        for (char u : characters) {
            if (Character.isUpperCase(u)) {
                degisecekharf = String.valueOf(u);
                break;
            }
        }
        int m = 0;
        for (int k = 0; k < myArrayList.size(); k++) {
            if (myArrayList.get(k).getLetter().equals(degisecekharf)) {
                m = k;
                break;
            }
        }
        newVocabulary = newVocabulary.replaceAll(degisecekharf, myArrayList.get(m).getVocabulary());
        return ChangeVocabulary(myArrayList, newVocabulary);
    }
}