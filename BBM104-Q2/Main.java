import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class Classroom
{
    private String Type;
    private String clasname;
    private String shape;
    private double genişlik;
    private double uzunluk;
    private double yükseklik;
    private double areazemin;
    private double areawall;
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getClasname() {
        return clasname;
    }
    public void setClasname(String clasname) {
        this.clasname = clasname;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public double getGenişlik() {
        return genişlik;
    }
    public void setGenişlik(double genişlik) {
        this.genişlik = genişlik;
    }
    public double getUzunluk() {
        return uzunluk;
    }
    public void setUzunluk(double uzunluk) {
        this.uzunluk = uzunluk;
    }
    public double getYükseklik() {
        return yükseklik;
    }
    public void setYükseklik(double yükseklik) {
        this.yükseklik = yükseklik;
    }
    public double getAreazemin() {
        return areazemin;
    }
    public void setAreazemin(double areazemin) {
        this.areazemin = areazemin;
    }
    public double getAreawall() {
        return areawall;
    }
    public void setAreawall(double areawall) {
        this.areawall = areawall;
    } 

}
class Decoration{
    private String Type;
    private String decorationName;
    private String decorationTürü;
    private Double decorationPrice;
    private Double Area;
    private int uzunluk;
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getDecorationName() {
        return decorationName;
    }
    public void setDecorationName(String decorationName) {
        this.decorationName = decorationName;
    }
    public String getDecorationTürü() {
        return decorationTürü;
    }
    public void setDecorationTürü(String decorationTürü) {
        this.decorationTürü = decorationTürü;
    }
    public Double getDecorationPrice() {
        return decorationPrice;
    }
    public void setDecorationPrice(Double decorationPrice) {
        this.decorationPrice = decorationPrice;
    }
    public Double getArea() {
        return Area;
    }
    public void setArea(Double area) {
        Area = area;
    }
    public int getUzunluk() {
        return uzunluk;
    }
    public void setUzunluk(int uzunluk) {
        this.uzunluk = uzunluk;
    }
    public Decoration(String Type,String decorationName,String decorationTürü,Double decorationPrice){
        this.Type=Type;
        this.decorationName=decorationName;
        this.decorationTürü=decorationTürü;
        this.decorationPrice=decorationPrice;
        this.uzunluk=4;

    }
    public Decoration(String Type,String decorationName,String decorationTürü,Double decorationPrice,double Area){
        this.Type=Type;
        this.decorationName=decorationName;
        this.decorationTürü=decorationTürü;
        this.decorationPrice=decorationPrice;
        this.Area=Area;
        this.uzunluk=5;
    }
}

class Circle extends Classroom{
    private String Type;
    private String clasname;
    private String shape;
    private double genişlik;
    private double uzunluk;
    private double yükseklik;
    private double areazemin;
    private double areawall; 
    private double radius;
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getClasname() {
        return clasname;
    }
    public void setClasname(String clasname) {
        this.clasname = clasname;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public double getGenişlik() {
        return genişlik;
    }
    public void setGenişlik(double genişlik) {
        this.genişlik = genişlik;
    }
    public double getUzunluk() {
        return uzunluk;
    }
    public void setUzunluk(double uzunluk) {
        this.uzunluk = uzunluk;
    }
    public double getYükseklik() {
        return yükseklik;
    }
    public void setYükseklik(double yükseklik) {
        this.yükseklik = yükseklik;
    }
    public double getAreazemin() {
        return areazemin;
    }
    public void setAreazemin(double areazemin) {
        this.areazemin = areazemin;
    }
    public double getAreawall() {
        return areawall;
    }
    public void setAreawall(double areawall) {
        this.areawall = areawall;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public Circle(String Type,String clasname,String shape,double genişlik,double uzunluk,double yükseklik){
            this.Type=Type;
            this.clasname=clasname;
            this.shape=shape;
            this.genişlik=genişlik;
            this.uzunluk=uzunluk;
            this.radius=genişlik/2;
            this.areazemin=Math.PI*radius*radius;
            this.areawall=2*Math.PI*radius*yükseklik;
    }
}
class Rectangle extends Classroom{
    private String Type;
    private String clasname;
    private String shape;
    private double genişlik;
    private double uzunluk;
    private double yükseklik;
    private double areazemin;
    private double areawall; 
    private double radius;
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getClasname() {
        return clasname;
    }
    public void setClasname(String clasname) {
        this.clasname = clasname;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public double getGenişlik() {
        return genişlik;
    }
    public void setGenişlik(double genişlik) {
        this.genişlik = genişlik;
    }
    public double getUzunluk() {
        return uzunluk;
    }
    public void setUzunluk(double uzunluk) {
        this.uzunluk = uzunluk;
    }
    public double getYükseklik() {
        return yükseklik;
    }
    public void setYükseklik(double yükseklik) {
        this.yükseklik = yükseklik;
    }
    public double getAreazemin() {
        return areazemin;
    }
    public void setAreazemin(double areazemin) {
        this.areazemin = areazemin;
    }
    public double getAreawall() {
        return areawall;
    }
    public void setAreawall(double areawall) {
        this.areawall = areawall;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public Rectangle(String Type,String clasname,String shape,double genişlik,double uzunluk,double yükseklik){
            this.Type=Type;
            this.clasname=clasname;
            this.shape=shape;
            this.genişlik=genişlik;
            this.yükseklik=yükseklik;
            this.uzunluk=uzunluk;
            this.areazemin=genişlik*uzunluk;
            this.areawall=2*(genişlik+uzunluk)*yükseklik;
    }
}

/**
 * This class creates and processes classroom and decoration objects.
 */
public class Main
{
    /**
     * The main method represents the start of the program.
     * @param args Command line arguments
     */
    public static void main(String[] args)
       /**
     * Creates classrooms and processes decorations.
     * @param reader Reader
     * @param allClassrooms List of all classrooms
     * @param allDecorations List of all decorations
     * @param emanemaneman Temporary variable
     * @param writer Writer
     */
    {
        ArrayList<Classroom> allClassrooms=new ArrayList<>();
        ArrayList<Decoration> allDecorations=new ArrayList<>();
        double emanemaneman=0;
        try {
            BufferedReader reader=new BufferedReader(new FileReader(args[0]));
            BufferedReader reader2=new BufferedReader(new FileReader(args[1]));
            BufferedWriter writer=new BufferedWriter(new FileWriter("output.txt"));
            makeRoom(reader, allClassrooms, allDecorations, emanemaneman,writer);
            doit(reader2, allClassrooms, allDecorations, emanemaneman,writer);
           
            
        } catch (Exception e) {}
    }
            
        public static void makeRoom(BufferedReader reader, ArrayList<Classroom> allClassrooms,ArrayList<Decoration> allDecorations,double emanemaneman,BufferedWriter writer){
             /**
             * Processes wall and floor decorations for classrooms.
             * @param reader2 Reader
             * @param allClassrooms List of all classrooms
             * @param allDecorations List of all decorations
             * @param emanemaneman Temporary variable
             * @param writer Writer
             */
            try {
                
                String line;
                
                while((line=reader.readLine().trim())!=null){
                    
                    String[] mydata=line.split("\t");
                    if (mydata[0].equals("CLASSROOM")){
                       
                        if(mydata[2].equals("Circle")){
                            Circle circle=new Circle(mydata[0], mydata[1], mydata[2], Double.parseDouble(mydata[3]), Double.parseDouble(mydata[4]), Double.parseDouble(mydata[5]));
                            allClassrooms.add(circle);
                        }
                        else if(mydata[2].equals("Rectangle")){
                            Rectangle rectangle=new Rectangle(mydata[0], mydata[1], mydata[2], Double.parseDouble(mydata[3]), Double.parseDouble(mydata[4]), Double.parseDouble(mydata[5]));
                            allClassrooms.add(rectangle);
                        }
                    }
                    else if(mydata[0].equals("DECORATION")){
                       
                        if(mydata.length==5) //fayans yani tile
                        {
                            Decoration decoration=new Decoration(mydata[0], mydata[1], mydata[2], Double.parseDouble(mydata[3]),Double.parseDouble(mydata[4]));
                            allDecorations.add(decoration);
    
                        }
                        else if(mydata.length==4)
                        {
                            Decoration decoration=new Decoration(mydata[0], mydata[1], mydata[2], Double.parseDouble(mydata[3]));
                            allDecorations.add(decoration);
                        }
                    }
                    
                }
            reader.close();
                
            } catch (Exception e) {
        }
           
        
     


       
        

    }

        public static void doit(BufferedReader reader2, ArrayList<Classroom> allClassrooms,ArrayList<Decoration> allDecorations,double emanemaneman,BufferedWriter writer){
            /**
             * Processes wall and floor decorations for classrooms.
             * @param reader2 Reader for input data
             * @param allClassrooms List of all classrooms
             * @param allDecorations List of all decorations
             * @param emanemaneman Temporary variable for cost calculation
             * @param writer Writer for output data
             */


            try {
                
                String line2;
                while((line2=reader2.readLine())!=null){
                    line2=line2.trim();
                    
                
                    String[] mydata=line2.split("\t");
    
                    for (int i=0;i<allClassrooms.size();i++)
                    {
    
                        if(mydata[0].equals(allClassrooms.get(i).getClasname())&&(allClassrooms.get(i).getShape().equals("Rectangle"))){
                            
                            for(int j=0;j<allDecorations.size();j++) {
                                if(mydata[1].equals(allDecorations.get(j).getDecorationName()))//duvarlar için j 
                                {
                                    if(allDecorations.get(j).getDecorationTürü().equals("Tile")){
                                        double duvarcostcost=Math.ceil(allClassrooms.get(i).getAreawall()/allDecorations.get(j).getArea())*allDecorations.get(j).getDecorationPrice();
    
                                        for(int eman=0;eman<allDecorations.size();eman++){//zemin
                                            if (mydata[2].equals(allDecorations.get(eman).getDecorationName())&&(allDecorations.get(eman).getDecorationTürü().equals("Tile")))
                                            {    double zemincost=Math.ceil(allClassrooms.get(i).getAreazemin()/allDecorations.get(eman).getArea())*allDecorations.get(eman).getDecorationPrice();
                                                 
                                                double totalcost=duvarcostcost+zemincost;
                                                emanemaneman=emanemaneman+Math.ceil(totalcost);
                                                writer.write("Classroom "+ allClassrooms.get(i).getClasname() +" used "+(int) Math.ceil(allClassrooms.get(i).getAreawall()/allDecorations.get(eman).getArea())+ " "+allDecorations.get(eman).getDecorationTürü()+"s for walls and used " +(int) Math.ceil(allClassrooms.get(i).getAreazemin()/allDecorations.get(eman).getArea())+ " Tiles for flooring, these costed "+(int) Math.ceil(totalcost)+"TL.");
                                                writer.newLine();
                                                writer.flush();
                        
                                            }
                                        }
                                    }
                                
                            
                                
                                    else if (allDecorations.get(j).getDecorationTürü().equals("Wallpaper")||allDecorations.get(j).getDecorationTürü().equals("Paint"))
                                    {
                                        double duvarcost=allClassrooms.get(i).getAreawall()*allDecorations.get(j).getDecorationPrice();
    
                                        for(int emaneman=0;emaneman<allDecorations.size();emaneman++){ //zemin
                                            if(mydata[2].equals(allDecorations.get(emaneman).getDecorationName())&&(allDecorations.get(emaneman).getDecorationTürü().equals("Tile")))
                                            {
                                                double zemincost=Math.ceil(allClassrooms.get(i).getAreazemin()/allDecorations.get(emaneman).getArea())*allDecorations.get(emaneman).getDecorationPrice();
                                                double totalcost=zemincost+duvarcost;
                                                emanemaneman=emanemaneman+Math.ceil(totalcost);
                                                writer.write("Classroom "+ allClassrooms.get(i).getClasname() +" used "+ (int)Math.ceil(allClassrooms.get(i).getAreawall())+"m2 of "+ allDecorations.get(j).getDecorationTürü()+" for walls and used " + (int) Math.ceil(Math.ceil(allClassrooms.get(i).getAreazemin())/Math.ceil(allDecorations.get(emaneman).getArea()))+ " Tiles for flooring, these costed "+ (int)Math.ceil(totalcost)+"TL.");
                                                writer.newLine();
                                                writer.flush();

                                            } 
                                        }
                                    }
                                }  
                            }
                        }
                        else if(mydata[0].equals(allClassrooms.get(i).getClasname())&&(allClassrooms.get(i).getShape().equals("Circle"))) {
                 
                            for(int m=0;m<allDecorations.size();m++)
                            {
                                if(mydata[1].equals(allDecorations.get(m).getDecorationName())&&(allDecorations.get(m).getDecorationTürü().equals("Paint")))//duvar
                                {
                                    double duvarcost=(((allDecorations.get(m).getDecorationPrice())*(allClassrooms.get(i).getAreawall())));
                                    for(int n=0;n<allDecorations.size();n++){
                                        if(mydata[2].equals(allDecorations.get(n).getDecorationName())&&(allDecorations.get(n).getDecorationTürü().equals("Tile")))//zemin
                                        {   
                                            double zemincost=(Math.ceil(allClassrooms.get(i).getAreazemin()/allDecorations.get(n).getArea())*(allDecorations.get(n).getDecorationPrice()));
                                            Double totalcost=duvarcost+zemincost;
                                            emanemaneman=emanemaneman+Math.ceil(totalcost);
                                            writer.write("Classroom "+ allClassrooms.get(i).getClasname() +" used "+(int) Math.ceil(allClassrooms.get(i).getAreawall())+"m2 of "+ allDecorations.get(m).getDecorationTürü()+" for walls and used " + (int)Math.ceil(allClassrooms.get(i).getAreazemin()/allDecorations.get(n).getArea())+ " Tiles for flooring, these costed "+ (int)Math.ceil(totalcost)+"TL.");
                                            writer.newLine();
                                            writer.flush(); 
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
               writer.write("Total price is: "+(int)Math.ceil(emanemaneman)+"TL.");
               writer.flush();
                
                reader2.close();
            } 
            catch (Exception e)
            {

            }
        }
    }