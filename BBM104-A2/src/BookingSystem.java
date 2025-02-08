import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Main class of the project.
 * 
 * @author Muhammed EKİNCİ (b2220765053)
 */
public class BookingSystem {
    static String line;
    static String inputfile;

    static ArrayList<Bus> allmybus = new ArrayList<>();

    static int check = 0;

    /**
     * Main method of the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        DOiteverything(args);
    }

    /**
     * Initializes a new voyage.
     * 
     * @param mydata    the command data
     * @param line      the command line
     * @param reader    the input reader
     * @param writer    the output writer
     * @param inputfile the input file name
     * @param allmybus  the list of all voyages
     * @param error     the error flag
     */
    public static void INIT_VOYAGE(String[] mydata, String line, BufferedReader reader, BufferedWriter writer,
            String inputfile, ArrayList<Bus> allmybus, boolean error) {
        if (!error) {
            if (!wasMakeBus(mydata, allmybus, error)) {
                try {
                    if (mydata.length == 7 && mydata[1].equals("Minibus")) {
                        if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6])) {
                            if (mydata[1].equals("Minibus") && (Integer.parseInt(mydata[2]) > 0
                                    && Integer.parseInt(mydata[5]) > 0 && Double.parseDouble(mydata[6]) > 0)) {
                                Bus bus = new Minibus(mydata[1], Integer.parseInt(mydata[2]), mydata[3], mydata[4],
                                        Integer.parseInt(mydata[5]), Double.parseDouble(mydata[6]));
                                allmybus.add(bus);
                                // Tüm listeyi ID'ye göre sırala
                                allmybus.sort(new Comparator<Bus>() {
                                    @Override
                                    public int compare(Bus bus1, Bus bus2) {
                                        return Integer.compare(bus1.Id, bus2.Id);
                                    }
                                });
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.flush();
                                writer.write("Voyage " + Integer.parseInt(mydata[2])
                                        + " was initialized as a minibus (2) voyage from " + mydata[3] + " to "
                                        + mydata[4] + " with " + formatDouble(Double.parseDouble(mydata[6]))
                                        + " TL priced " + 2 * Integer.parseInt(mydata[5])
                                        + " regular seats. Note that minibus tickets are not refundable.");
                                writer.newLine();
                                writer.flush();
                            }
                        } else {
                        }
                    } else if (mydata.length == 8 && mydata[1].equals("Standard")) {
                        if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6]) && isNumber(mydata[7])) {
                            if ((Integer.parseInt(mydata[2]) > 0 && Integer.parseInt(mydata[5]) > 0
                                    && Double.parseDouble(mydata[6]) > 0
                                    && (Integer.parseInt(mydata[7]) >= 0 && Integer.parseInt(mydata[7]) <= 100))) {
                                Bus bus = new Standard(mydata[1], Integer.parseInt(mydata[2]), mydata[3], mydata[4],
                                        Integer.parseInt(mydata[5]),
                                        Double.parseDouble(mydata[6]), Double.parseDouble(mydata[7]));
                                allmybus.add(bus);
                                allmybus.sort(new Comparator<Bus>() {
                                    @Override
                                    public int compare(Bus bus1, Bus bus2) {
                                        return Integer.compare(bus1.Id, bus2.Id);
                                    }
                                });
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.flush();
                                writer.write("Voyage " + Integer.parseInt(mydata[2])
                                        + " was initialized as a standard (2+2) voyage from " + mydata[3] + " to "
                                        + mydata[4] + " with " + formatDouble(Double.parseDouble(mydata[6]))
                                        + " TL priced " + 4 * Integer.parseInt(mydata[5])
                                        + " regular seats. Note that refunds will be " + Integer.parseInt(mydata[7])
                                        + "% less than the paid amount.");
                                writer.newLine();
                                writer.flush();
                            }
                        }
                    } else if (mydata.length == 9 && mydata[1].equals("Premium")) {
                        if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6]) && isNumber(mydata[7])
                                && isNumber(mydata[8])) {
                            if ((Integer.parseInt(mydata[2]) > 0 && Integer.parseInt(mydata[5]) > 0
                                    && Double.parseDouble(mydata[6]) > 0 && Double.parseDouble(mydata[8]) >= 0
                                    && (Integer.parseInt(mydata[7]) >= 0 && Integer.parseInt(mydata[7]) <= 100))) {
                                Bus bus = new Premium(mydata[1], Integer.parseInt(mydata[2]), mydata[3], mydata[4],
                                        Integer.parseInt(mydata[5]), Double.parseDouble(mydata[6]),
                                        Double.parseDouble(mydata[7]), Double.parseDouble(mydata[8]));
                                allmybus.add(bus);
                                allmybus.sort(new Comparator<Bus>() {
                                    @Override
                                    public int compare(Bus bus1, Bus bus2) {
                                        return Integer.compare(bus1.Id, bus2.Id);
                                    }
                                });
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.flush();
                                writer.write("Voyage " + bus.Id + " was initialized as a premium (1+2) voyage from "
                                        + mydata[3] + " to " + mydata[4] + " with " + formatDouble(bus.price)
                                        + " TL priced " + (bus.seatsOfNumber - bus.row) + " regular seats and "
                                        + formatDouble(bus.premiumPrice) + " TL priced " + Integer.parseInt(mydata[5])
                                        + " premium seats. Note that refunds will be " + Integer.parseInt(mydata[7])
                                        + "% less than the paid amount.");
                                writer.newLine();
                                writer.flush();
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Sells a ticket for a specific voyage.
     * 
     * @param mydata    the command data
     * @param reader    the input reader
     * @param writer    the output writer
     * @param line      the command line
     * @param allmybus  the list of all voyages
     * @param error     the error flag
     * @param bozansayı the number of seats to be sold
     */
    public static void SELL_TICKET(String[] mydata, BufferedReader reader, BufferedWriter writer, String line,
            ArrayList<Bus> allmybus, boolean error, String bozansayı) {
        if (!error) {
            try {
                String[] myotherdata = mydata[2].split("_");
                String kelime = mydata[2].replace("_", "-");
                boolean devammı = true;
                boolean satıldımı = false;

                for (String k : myotherdata) {
                    if (Integer.parseInt(k) < 0 || !isNumber(k)) {
                        bozansayı = k;
                        devammı = false;
                        break;

                    }
                }

                if (devammı) {

                    for (Bus mybus2 : allmybus) {
                        if (mybus2.Id == Integer.parseInt(mydata[1])) {
                            for (String u : myotherdata) {
                                if (mybus2.Seatsofanybus[(Integer.parseInt(u) - 1)].equals("X")) {
                                    satıldımı = true;

                                }
                            }

                        }

                        if (mybus2.Id == Integer.parseInt(mydata[1]) && isIDinthere(mydata, allmybus, error)
                                && isSeathere(mydata, allmybus, error) && !satıldımı) {

                            if (mybus2.Type.equals("Standard") && isIDinthere(mydata, allmybus, error)
                                    && isSeathere(mydata, allmybus, error) && !satıldımı) {
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.write("Seat " + kelime + " of the Voyage " + mybus2.Id + " from "
                                        + mybus2.boardingPlace + " to " + mybus2.landingPlace
                                        + " was successfully sold for "
                                        + formatDouble(myotherdata.length * mybus2.price) + " TL.");
                                writer.newLine();
                                writer.flush();
                                for (String m : myotherdata) {
                                    mybus2.Seatsofanybus[Integer.parseInt(m) - 1] = "X";
                                    mybus2.Revenue = mybus2.Revenue + mybus2.price;
                                }

                            } else if (mybus2.Type.equals("Premium") && isIDinthere(mydata, allmybus, error)
                                    && isSeathere(mydata, allmybus, error) && !satıldımı) {
                                int howpre = 0;
                                for (String koltukno : myotherdata) {
                                    if ((Integer.parseInt(koltukno) - 1) % 3 == 0) {
                                        howpre++;
                                    }
                                }
                                writer.write("COMMAND: " + line);
                                writer.newLine();

                                writer.write("Seat " + kelime + " of the Voyage " + mybus2.Id + " from "
                                        + mybus2.boardingPlace + " to " + mybus2.landingPlace
                                        + " was successfully sold for "
                                        + formatDouble(((myotherdata.length - howpre) * mybus2.price)
                                                + (howpre * mybus2.premiumPrice))
                                        + " TL.");
                                writer.newLine();
                                writer.flush();

                                for (String m : myotherdata) {
                                    mybus2.Seatsofanybus[Integer.parseInt(m) - 1] = "X";
                                    if ((Integer.parseInt(m) - 1) % 3 == 0) {
                                        mybus2.Revenue = mybus2.Revenue + mybus2.premiumPrice;
                                    } else {
                                        mybus2.Revenue = mybus2.Revenue + mybus2.price;
                                    }
                                }

                            } else if (mybus2.Type.equals("Minibus") && isIDinthere(mydata, allmybus, error)
                                    && isSeathere(mydata, allmybus, error) && !satıldımı) {
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.flush();
                                writer.write("Seat " + kelime + " of the Voyage " + mybus2.Id + " from "
                                        + mybus2.boardingPlace + " to " + mybus2.landingPlace
                                        + " was successfully sold for "
                                        + formatDouble(myotherdata.length * mybus2.price) + " TL.");
                                writer.newLine();
                                writer.flush();

                                for (String m : myotherdata) {
                                    mybus2.Seatsofanybus[Integer.parseInt(m) - 1] = "X";

                                    mybus2.Revenue = mybus2.Revenue + mybus2.price;
                                }
                            }

                        }

                        else if (mybus2.Id == Integer.parseInt(mydata[1]) && isIDinthere(mydata, allmybus, error)
                                && isSeathere(mydata, allmybus, error) && satıldımı) {
                            writer.write("COMMAND: " + line);
                            writer.newLine();
                            writer.write("ERROR: One or more seats already sold!\n");
                            writer.flush();

                        }
                    }

                } else if (!devammı) {
                    writer.write("COMMAND: " + line);
                    writer.newLine();
                    writer.flush();
                    writer.write("ERROR: " + bozansayı
                            + " is not a positive integer, seat number must be a positive integer!\n");

                }

            } catch (Exception e) {

            }

        }

    }

    /**
     * Refunds tickets for a specific bus voyage based on provided data.
     *
     * @param mydata   The array containing ticket data.
     * @param reader   The BufferedReader for reading input.
     * @param writer   The BufferedWriter for writing output.
     * @param line     The current command line being processed.
     * @param allmybus The ArrayList containing all bus objects.
     * @param error    A boolean indicating whether an error occurred earlier in the
     *                 program.
     */

    public static void REFUND_TICKET(String[] mydata, BufferedReader reader, BufferedWriter writer, String line,
            ArrayList<Bus> allmybus, boolean error) {
        if (!error) {

            try {
                String[] checkList = mydata[2].split("_");
                int bozansayı = 0;
                boolean devammı = true;
                for (String k : checkList) {
                    if (Integer.parseInt(k) < 0) {
                        bozansayı = Integer.parseInt(k);
                        devammı = false;
                        break;

                    }
                }

                if (devammı) {
                    if (isSoldForRefund(mydata, allmybus, error)) {
                        String[] forvocabStrings = mydata[2].split("_");
                        String kel = mydata[2].replace("_", "-");
                        ;

                        for (Bus mybus3 : allmybus) {

                            if (mybus3.Id == Integer.parseInt(mydata[1]) && isIDinthere(mydata, allmybus, error)
                                    && isSeathere(mydata, allmybus, error)
                                    && isSoldForRefund(mydata, allmybus, error)) {
                                if (mybus3.Type.equals("Minibus")) {
                                    writer.write("COMMAND: " + line);
                                    writer.newLine();
                                    writer.flush();
                                    writer.write("ERROR: Minibus tickets are not refundable!");
                                    writer.newLine();
                                    writer.flush();

                                } else if (mybus3.Type.equals("Standard")) {
                                    writer.write("COMMAND: " + line);
                                    writer.newLine();
                                    writer.flush();
                                    writer.write("Seat " + kel + " of the Voyage " + mybus3.Id + " from "
                                            + mybus3.boardingPlace + " to " + mybus3.landingPlace
                                            + " was successfully refunded for "
                                            + formatDouble(
                                                    forvocabStrings.length * (mybus3.price - mybus3.deductionAmount))
                                            + " TL.");
                                    writer.newLine();
                                    writer.flush();

                                    String[] myotherdatam = mydata[2].split("_");
                                    for (String m : myotherdatam) {
                                        mybus3.Seatsofanybus[Integer.parseInt(m) - 1] = "*";
                                        mybus3.Revenue = mybus3.Revenue - mybus3.price + mybus3.deductionAmount;
                                    }
                                } else if (mybus3.Type.equals("Premium")) {
                                    int howpre = 0;
                                    for (String koltukno : forvocabStrings) {
                                        if ((Integer.parseInt(koltukno) - 1) % 3 == 0) {
                                            howpre++;
                                        }
                                    }

                                    writer.write("COMMAND: " + line);
                                    writer.newLine();
                                    writer.flush();
                                    writer.write("Seat " + kel + " of the Voyage " + mybus3.Id + " from "
                                            + mybus3.boardingPlace + " to " + mybus3.landingPlace
                                            + " was successfully refunded for "
                                            + formatDouble(
                                                    ((howpre * (mybus3.premiumPrice - mybus3.premiumdeductionAmount))
                                                            + ((forvocabStrings.length - howpre)
                                                                    * (mybus3.price - mybus3.deductionAmount))))
                                            + " TL.");
                                    writer.newLine();
                                    writer.flush();

                                    String[] myotherdatam = mydata[2].split("_");
                                    for (String m : myotherdatam) {
                                        mybus3.Seatsofanybus[Integer.parseInt(m) - 1] = "*";
                                        if ((Integer.parseInt(m) - 1) % 3 == 0) {

                                            mybus3.Revenue = mybus3.Revenue - mybus3.premiumPrice
                                                    + mybus3.premiumdeductionAmount;
                                        } else {
                                            mybus3.Revenue = mybus3.Revenue - mybus3.price + mybus3.deductionAmount;
                                        }
                                    }
                                }
                            }
                        }

                    }

                } else if (!devammı) {
                    writer.write("COMMAND: " + line);
                    writer.newLine();
                    writer.flush();
                    writer.write("ERROR: " + bozansayı
                            + " is not a positive integer, seat number must be a positive integer!\n");

                }

            } catch (Exception e) {

            }

        }

    }

    /**
     * Cancels a voyage for a specific bus based on provided data.
     *
     * @param mydata   The array containing voyage data.
     * @param reader   The BufferedReader for reading input.
     * @param writer   The BufferedWriter for writing output.
     * @param line     The current command line being processed.
     * @param allmybus The ArrayList containing all bus objects.
     * @param error    A boolean indicating whether an error occurred earlier in the
     *                 program.
     */
    public static void CANCEL_VOYAGE(String[] mydata, BufferedReader reader, BufferedWriter writer, String line,
            ArrayList<Bus> allmybus, boolean error) {
        if (!error) {
            try {
                int ID = Integer.parseInt(mydata[1]);
                boolean devammı = true;

                if (ID < 0) {
                    devammı = false;

                }
                if (devammı) {

                    for (Bus mybus4 : allmybus) {

                        if (mybus4.Id == Integer.parseInt(mydata[1]) && isIDinthere(mydata, allmybus, error)) {
                            writer.write("COMMAND: " + line);
                            writer.newLine();
                            writer.flush();
                            writer.write("Voyage " + mydata[1] + " was successfully cancelled!");
                            writer.newLine();
                            writer.flush();
                            writer.write("Voyage details can be found below:");
                            writer.newLine();
                            writer.flush();
                            writer.write("Voyage " + mydata[1]);
                            writer.newLine();
                            writer.flush();
                            writer.write(mybus4.boardingPlace + "-" + mybus4.landingPlace);
                            writer.newLine();
                            writer.flush();

                            if (mybus4.Type.equals("Standard")) {
                                int seatadet = 0;
                                for (String u : mybus4.Seatsofanybus) {
                                    if (u.equals("X")) {
                                        seatadet++;
                                    }
                                }
                                mybus4.Revenue = mybus4.Revenue - seatadet * mybus4.price;
                                for (int i = 0; i < mybus4.Seatsofanybus.length; i++) {
                                    if (i % 4 != 3) {
                                        writer.write(mybus4.Seatsofanybus[i] + " ");
                                        writer.flush();

                                    }

                                    if (i % 4 == 1) {
                                        writer.write("| ");
                                        writer.flush();
                                    }
                                    if (i % 4 == 3) {

                                        writer.write(mybus4.Seatsofanybus[i]);
                                        writer.newLine();
                                        writer.flush();
                                    }
                                }
                            } else if (mybus4.Type.equals("Premium")) {
                                int howpre = 0;
                                int normal = 0;
                                int check = 0;
                                for (String u : mybus4.Seatsofanybus) {
                                    check++;
                                    if (u.equals("X") && check % 3 != 1) {
                                        normal++;
                                    } else if (u.equals("X") && check % 3 == 1) {
                                        howpre++;
                                    }
                                }
                                mybus4.Revenue = mybus4.Revenue - (howpre * mybus4.premiumPrice)
                                        - (normal * mybus4.price);

                                for (int i = 0; i < mybus4.Seatsofanybus.length; i++) {
                                    if (i % 3 != 2) {
                                        writer.write(mybus4.Seatsofanybus[i] + " ");
                                        writer.flush();

                                    }

                                    if (i % 3 == 0) {
                                        writer.write("| ");
                                        writer.flush();
                                    }
                                    if (i % 3 == 2) {
                                        writer.write(mybus4.Seatsofanybus[i]);
                                        writer.flush();
                                        writer.newLine();
                                        writer.flush();
                                    }
                                }
                            } else if (mybus4.Type.equals("Minibus")) {
                                int seatadet = 0;
                                for (String u : mybus4.Seatsofanybus) {
                                    if (u.equals("X")) {
                                        seatadet++;
                                    }
                                }
                                mybus4.Revenue = mybus4.Revenue - seatadet * mybus4.price;
                                for (int i = 0; i < mybus4.Seatsofanybus.length; i++) {
                                    if (i % 2 != 0) {
                                        writer.write(mybus4.Seatsofanybus[i] + " ");
                                        writer.flush();

                                    }

                                    if (i % 2 == 0) {
                                        writer.write(mybus4.Seatsofanybus[i]);
                                        writer.flush();
                                        writer.newLine();
                                    }
                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus4.Revenue));
                            writer.newLine();
                            writer.flush();

                            allmybus.remove(mybus4);
                        }
                    }

                }

                else if (!devammı) {
                    writer.write("COMMAND: " + line);

                    writer.newLine();
                    writer.write("ERROR: " + ID
                            + " is not a positive integer, ID of a voyage must be a positive integer!\n");

                }
            } catch (Exception e) {

            }

        }

    }

    /**
     * This method is used to print the details of a voyage.
     * 
     * @param mydata   The array containing voyage data.
     * @param reader   The BufferedReader for reading input.
     * @param writer   The BufferedWriter for writing output.
     * @param line     The current command line being processed.
     * @param allmybus The ArrayList containing all bus objects.
     * @param error    A boolean indicating whether an error occurred earlier in the
     *                 program.
     */
    public static void PRINT_VOYAGE(String[] mydata, BufferedReader reader, BufferedWriter writer, String line,
            ArrayList<Bus> allmybus, boolean error) {
        if (!error) {
            try {
                int ID = Integer.parseInt(mydata[1]);
                boolean devammı = true;

                if (ID < 0) {
                    devammı = false;

                }

                if (devammı) {
                    if (isIDinthere(mydata, allmybus, error)) {
                        for (Bus mybus5 : allmybus) {
                            if (mybus5.Id == Integer.parseInt(mydata[1])) {
                                writer.write("COMMAND: " + line);
                                writer.newLine();
                                writer.flush();
                                writer.write("Voyage " + mybus5.Id);
                                writer.newLine();
                                writer.flush();
                                writer.write(mybus5.boardingPlace + "-" + mybus5.landingPlace);
                                writer.newLine();
                                writer.flush();
                                if (mybus5.Type.equals("Standard")) {
                                    for (int i = 0; i < mybus5.Seatsofanybus.length; i++) {
                                        if (i % 4 != 3) {
                                            writer.write(mybus5.Seatsofanybus[i] + " ");
                                            writer.flush();

                                        }

                                        if (i % 4 == 1) {
                                            writer.write("| ");
                                            writer.flush();
                                        }
                                        if (i % 4 == 3) {

                                            writer.write(mybus5.Seatsofanybus[i]);
                                            writer.newLine();
                                            writer.flush();
                                        }
                                    }
                                } else if (mybus5.Type.equals("Premium")) {
                                    for (int i = 0; i < mybus5.Seatsofanybus.length; i++) {
                                        if (i % 3 != 2) {
                                            writer.write(mybus5.Seatsofanybus[i] + " ");
                                            writer.flush();

                                        }

                                        if (i % 3 == 0) {
                                            writer.write("| ");
                                            writer.flush();
                                        }
                                        if (i % 3 == 2) {
                                            writer.write(mybus5.Seatsofanybus[i]);
                                            writer.flush();
                                            writer.newLine();
                                            writer.flush();
                                        }
                                    }
                                } else if (mybus5.Type.equals("Minibus")) {
                                    for (int i = 0; i < mybus5.Seatsofanybus.length; i++) {
                                        if (i % 2 != 0) {
                                            writer.write(mybus5.Seatsofanybus[i] + " ");
                                            writer.flush();

                                        }

                                        if (i % 2 == 0) {
                                            writer.write(mybus5.Seatsofanybus[i]);
                                            writer.flush();
                                            writer.newLine();
                                        }
                                    }
                                }
                                writer.write("Revenue: " + formatDouble(mybus5.Revenue));
                                writer.newLine();
                                writer.flush();

                            }
                        }

                    }

                    else if (!isIDinthere(mydata, allmybus, error)) {
                        writer.write("COMMAND: " + line);
                        writer.newLine();
                        writer.write("ERROR: There is no voyage with ID of " + mydata[1] + "!\n");

                    }

                }

                else if (!devammı) {
                    writer.write("COMMAND: " + line);
                    writer.newLine();
                    writer.flush();
                    writer.write("ERROR: " + ID
                            + " is not a positive integer, ID of a voyage must be a positive integer!\n");

                }

            } catch (Exception e) {

            }

        }

    }

    /**
     * Generates a Z report based on provided data.
     *
     * @param mydata      The array containing report data.
     * @param reader      The BufferedReader for reading input.
     * @param writer      The BufferedWriter for writing output.
     * @param line        The current command line being processed.
     * @param allmybus    The ArrayList containing all bus objects.
     * @param check       An integer indicating the type of report to generate.
     * @param satirSayisi The number of lines to print in the report.
     * @param error       A boolean indicating whether an error occurred earlier in
     *                    the program.
     */
    public static void Z_REPORT(String[] mydata, BufferedReader reader, BufferedWriter writer, String line,
            ArrayList<Bus> allmybus, int check, int satirSayisi, boolean error) {

        try {

            if (check != satirSayisi) {
                if (allmybus.size() == 0) {
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("Z Report:\n----------------");
                    writer.newLine();
                    writer.flush();
                    writer.write("No Voyages Available!\n");
                    writer.write("----------------\n");
                } else {
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("Z Report:\n----------------");
                    writer.newLine();
                    writer.flush();
                    for (Bus mybus : allmybus) {

                        writer.write("Voyage " + mybus.Id);
                        writer.newLine();
                        writer.flush();
                        writer.write(mybus.boardingPlace + "-" + mybus.landingPlace);
                        writer.newLine();
                        writer.flush();
                        if (mybus.Type.equals("Standard")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 4 != 3) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                }

                                if (i % 4 == 1) {
                                    writer.write("| ");
                                }
                                if (i % 4 == 3) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();

                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");
                            writer.newLine();
                            writer.flush();
                        } else if (mybus.Type.equals("Premium")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 3 != 2) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                    writer.flush();
                                }

                                if (i % 3 == 0) {
                                    writer.write("| ");
                                    writer.flush();
                                }
                                if (i % 3 == 2) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();
                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");
                            writer.newLine();
                            writer.flush();
                        } else if (mybus.Type.equals("Minibus")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 2 != 1) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                }

                                if (i % 2 == 1) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();
                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");
                            writer.newLine();
                            writer.flush();
                        }
                    }

                }

            } else if (check == satirSayisi) {

                if (allmybus.size() == 0 && mydata[0].equals("Z_REPORT")) {
                    writer.write("COMMAND: " + line + "\n");//
                    writer.write("Z Report:\n----------------\n");
                    writer.write("No Voyages Available!\n");
                    writer.write("----------------");
                    writer.flush();
                } else if (allmybus.size() == 0 && !mydata[0].equals("Z_REPORT")) {
                    writer.write("Z Report:\n----------------\n");
                    writer.write("No Voyages Available!\n");
                    writer.write("----------------");
                    writer.flush();

                } else if (!mydata[0].equals("Z_REPORT")) {
                    {

                        writer.write("Z Report:\n----------------");
                        writer.flush();
                        for (Bus mybus : allmybus) {

                            writer.write("\nVoyage " + mybus.Id);
                            writer.newLine();
                            writer.flush();
                            writer.write(mybus.boardingPlace + "-" + mybus.landingPlace);
                            writer.newLine();
                            writer.flush();
                            if (mybus.Type.equals("Standard")) {
                                for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                    if (i % 4 != 3) {
                                        writer.write(mybus.Seatsofanybus[i] + " ");
                                    }

                                    if (i % 4 == 1) {
                                        writer.write("| ");
                                    }
                                    if (i % 4 == 3) {
                                        writer.write(mybus.Seatsofanybus[i]);
                                        writer.newLine();
                                        writer.flush();

                                    }
                                }
                                writer.write("Revenue: " + formatDouble(mybus.Revenue));
                                writer.newLine();
                                writer.flush();
                                writer.write("----------------");

                                writer.flush();
                            } else if (mybus.Type.equals("Premium")) {
                                for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                    if (i % 3 != 2) {
                                        writer.write(mybus.Seatsofanybus[i] + " ");
                                        writer.flush();
                                    }

                                    if (i % 3 == 0) {
                                        writer.write("| ");
                                        writer.flush();
                                    }
                                    if (i % 3 == 2) {
                                        writer.write(mybus.Seatsofanybus[i]);
                                        writer.newLine();
                                        writer.flush();
                                    }
                                }
                                writer.write("Revenue: " + formatDouble(mybus.Revenue));
                                writer.newLine();
                                writer.flush();
                                writer.write("----------------");

                                writer.flush();
                            } else if (mybus.Type.equals("Minibus")) {
                                for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                    if (i % 2 != 1) {
                                        writer.write(mybus.Seatsofanybus[i] + " ");
                                    }

                                    if (i % 2 == 1) {
                                        writer.write(mybus.Seatsofanybus[i]);
                                        writer.newLine();
                                        writer.flush();
                                    }
                                }
                                writer.write("Revenue: " + formatDouble(mybus.Revenue));
                                writer.newLine();
                                writer.flush();
                                writer.write("----------------");

                                writer.flush();
                            }
                        }

                    }

                } else {
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("Z Report:\n----------------");
                    writer.flush();
                    for (Bus mybus : allmybus) {

                        writer.write("\nVoyage " + mybus.Id);
                        writer.newLine();
                        writer.flush();
                        writer.write(mybus.boardingPlace + "-" + mybus.landingPlace);
                        writer.newLine();
                        writer.flush();
                        if (mybus.Type.equals("Standard")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 4 != 3) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                }

                                if (i % 4 == 1) {
                                    writer.write("| ");
                                }
                                if (i % 4 == 3) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();

                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");

                            writer.flush();
                        } else if (mybus.Type.equals("Premium")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 3 != 2) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                    writer.flush();
                                }

                                if (i % 3 == 0) {
                                    writer.write("| ");
                                    writer.flush();
                                }
                                if (i % 3 == 2) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();
                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");

                            writer.flush();
                        } else if (mybus.Type.equals("Minibus")) {
                            for (int i = 0; i < mybus.Seatsofanybus.length; i++) {
                                if (i % 2 != 1) {
                                    writer.write(mybus.Seatsofanybus[i] + " ");
                                }

                                if (i % 2 == 1) {
                                    writer.write(mybus.Seatsofanybus[i]);
                                    writer.newLine();
                                    writer.flush();
                                }
                            }
                            writer.write("Revenue: " + formatDouble(mybus.Revenue));
                            writer.newLine();
                            writer.flush();
                            writer.write("----------------");

                            writer.flush();
                        }
                    }

                }

            }
        }

        catch (Exception e) {

        }

    }

    /**
     * Formats a double number with two decimal places and replaces commas with
     * periods.
     *
     * @param number The double number to format.
     * @return The formatted number as a string.
     */
    public static String formatDouble(double number) {
        return String.format("%.2f", number).replace(",", ".");
    }

    /**
     * This method is used to calculate the number of lines in a file.
     * 
     * @param inputfile The name of the file.
     * @return The number of lines in the file.
     * @throws IOException If there is an error while reading the file.
     */
    public static int satirSayisiniBul(String inputfile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
            int satirSayisi = 0;
            String satir;
            while ((satir = br.readLine()) != null) {
                if (!satir.trim().isEmpty()) {
                    satirSayisi++;
                }
            }
            return satirSayisi;
        }
    }

    /**
     * This method is used to check if the given command is valid or not.
     * If the command is valid, it returns true. Otherwise, it returns false and
     * writes an error message to the output file.
     * 
     * @param mydata   The command that is being executed.
     * @param allmybus The list of all voyages.
     * @param error    A boolean value that indicates if there is an error or not.
     */
    public static void isThereErrorThere(String[] mydata, String line, BufferedReader reader, BufferedWriter writer,
            String inputfile, ArrayList<Bus> allmybus, boolean error) {

        try {

            if (mydata[0].equals("INIT_VOYAGE") || mydata[0].equals("SELL_TICKET") || mydata[0].equals("REFUND_TICKET")
                    || mydata[0].equals("CANCEL_VOYAGE") || mydata[0].equals("PRINT_VOYAGE")
                    || mydata[0].equals("Z_REPORT")) {

            } else {
                writer.write("COMMAND: " + line + "\n");
                writer.write("ERROR: There is no command namely " + mydata[0] + "!\n");
                error = true;
                writer.flush();

            }
            if (mydata[0].equals("INIT_VOYAGE")) {

                if (!wasMakeBus(mydata, allmybus, error)) {
                    if (mydata.length == 7) {

                        if (mydata[1].equals("Standard") || mydata[1].equals("Premium")
                                || mydata[1].equals("Minibus")) {
                            if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6])
                                    && !isNumber(mydata[3]) && !isNumber(mydata[4])) {
                                if (Integer.parseInt(mydata[2]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[2]
                                            + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    error = true;
                                }
                                if (Integer.parseInt(mydata[5]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[5]
                                            + " is not a positive integer, number of seat rows of a voyage must be a positive integer!\n");
                                    error = true;

                                }
                                if (Double.parseDouble(mydata[6]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[6]
                                            + " is not a positive number, price must be a positive number!\n");
                                    error = true;
                                }

                            } else {
                                error = true;

                            }

                        } else {
                            error = true;
                            writer.write("COMMAND: " + line + "\n");
                            writer.write("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!\n");
                            writer.flush();

                        }

                    } else if (mydata.length == 8) {
                        if (mydata[1].equals("Standard") || mydata[1].equals("Premium")
                                || mydata[1].equals("Minibus")) {
                            if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6])
                                    && !isNumber(mydata[3]) && !isNumber(mydata[4]) && isNumber(mydata[7])) {
                                if (Integer.parseInt(mydata[2]) <= 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[2]
                                            + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    error = true;
                                }
                                if (Integer.parseInt(mydata[5]) <= 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[5]
                                            + " is not a positive integer, number of seat rows of a voyage must be a positive integer!\n");
                                    error = true;

                                }
                                if (Double.parseDouble(mydata[6]) <= 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[6]
                                            + " is not a positive number, price must be a positive number!\n");
                                    error = true;
                                }
                                if (Integer.parseInt(mydata[7]) < 0 || Integer.parseInt(mydata[7]) > 100) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[7]
                                            + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!\n");
                                    error = true;

                                }

                            } else {
                                error = true;

                            }

                        } else {
                            error = true;
                            writer.write("COMMAND: " + line + "\n");
                            writer.write("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!\n");
                            writer.flush();

                        }

                    } else if (mydata.length == 9) {
                        if (mydata[1].equals("Standard") || mydata[1].equals("Premium")
                                || mydata[1].equals("Minibus")) {
                            if (isNumber(mydata[2]) && isNumber(mydata[5]) && isNumber(mydata[6])
                                    && !isNumber(mydata[3]) && !isNumber(mydata[4]) && isNumber(mydata[7])
                                    && isNumber(mydata[8])) {
                                if (Integer.parseInt(mydata[2]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[2]
                                            + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    error = true;
                                }
                                if (Integer.parseInt(mydata[5]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[5]
                                            + " is not a positive integer, number of seat rows of a voyage must be a positive integer!\n");
                                    error = true;

                                }
                                if (Double.parseDouble(mydata[6]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[6]
                                            + " is not a positive number, price must be a positive number!\n");
                                    error = true;
                                }
                                if (Integer.parseInt(mydata[7]) < 0 || Integer.parseInt(mydata[7]) > 100) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[7]
                                            + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!\n");
                                    error = true;

                                }
                                if (Integer.parseInt(mydata[8]) < 0) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write(
                                            "ERROR: -10 is not a non-negative integer, premium fee must be a non-negative integer!\n");
                                    error = true;
                                }

                            } else {
                                error = true;

                            }

                        } else {
                            error = true;
                            writer.write("COMMAND: " + line + "\n");
                            writer.write("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!\n");
                            writer.flush();

                        }

                    } else {
                        writer.write("COMMAND: " + line + "\n");
                        writer.write("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!\n");
                        writer.flush();
                        error = true;

                    }
                } else if (wasMakeBus(mydata, allmybus, error)) {
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: There is already a voyage with ID of " + mydata[2] + "!\n");

                }

            }

            else if (mydata[0].equals("SELL_TICKET")) {
                if (mydata.length == 3) {

                    if (isNumber(mydata[1]) && isNumber(mydata[1])) {

                        if (isIDinthere(mydata, allmybus, error)) {
                            if (isSeathere(mydata, allmybus, error)) {

                            } else {
                                writer.write("COMMAND: " + line + "\n");
                                writer.write("ERROR: There is no such a seat!\n");
                                error = true;

                            }

                        } else {
                            writer.write("COMMAND: " + line + "\n");
                            writer.write("ERROR: There is no voyage with ID of " + mydata[1] + "!\n");
                            error = true;

                        }

                    } else {
                        error = true;
                    }

                } else {

                    error = true;
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: Erroneous usage of \"SELL_TICKET\" command!\n");
                    writer.flush();

                }
            }

            else if (mydata[0].equals("REFUND_TICKET")) {
                if (mydata.length == 3 && isNumber(mydata[1])) {
                    if (isNumber(mydata[1])) {
                        if (Integer.parseInt(mydata[1]) > 0) {
                            if (isIDinthere(mydata, allmybus, error)) {
                                if (isSeathere(mydata, allmybus, error)) {
                                    if (isSoldForRefund(mydata, allmybus, error)) {

                                    } else {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: One or more seats are already empty!\n");
                                        error = true;

                                    }

                                } else {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: There is no such a seat!\n");
                                    error = true;

                                }

                            } else {
                                writer.write("COMMAND: " + line + "\n");
                                writer.write("ERROR: There is no voyage with ID of " + mydata[1] + "!\n");
                                error = true;

                            }
                        }

                    } else {
                        error = true;
                    }

                } else {

                    error = true;
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: Erroneous usage of \"REFUND_TICKET\" command!\n");
                    writer.flush();

                }
            }

            else if (mydata[0].equals("CANCEL_VOYAGE")) {
                if (mydata.length == 2 && isNumber(mydata[1])) {
                    if (isNumber(mydata[1])) {

                        if (Integer.parseInt(mydata[1]) > 0) {
                            if (isIDinthere(mydata, allmybus, error)) {
                                if (isSeathere(mydata, allmybus, error)) {

                                } else {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: There is no such a seat!\n");
                                    error = true;

                                }

                            } else {
                                writer.write("COMMAND: " + line + "\n");
                                writer.write("ERROR: There is no voyage with ID of " + mydata[1] + "!\n");
                                error = true;

                            }
                        }

                    } else {
                        error = true;
                    }

                } else {

                    error = true;
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: Erroneous usage of \"CANCEL_VOYAGE\" command!\n");
                    writer.flush();

                }
            } else if (mydata[0].equals("Z_REPORT")) {
                if (mydata.length == 1) {

                } else {

                    error = true;
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: Erroneous usage of \"Z_REPORT\" command!\n");
                    writer.flush();

                }
            } else if (mydata[0].equals("PRINT_VOYAGE")) {
                if (mydata.length == 2 && isNumber(mydata[1])) {
                    if (isNumber(mydata[1])) {
                        if (Integer.parseInt(mydata[0]) > 0) {
                            if (isIDinthere(mydata, allmybus, error)) {
                                if (isSeathere(mydata, allmybus, error)) {
                                } else {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: There is no such a seat!\n");
                                    error = true;
                                }
                            } else {
                                writer.write("COMMAND: " + line + "\n");
                                writer.write("ERROR: There is no voyage with ID of " + mydata[1] + "!\n");
                                error = true;
                            }
                        }
                    } else {
                        error = true;
                    }
                } else {
                    error = true;
                    writer.write("COMMAND: " + line + "\n");
                    writer.write("ERROR: Erroneous usage of \"PRINT_VOYAGE\" command!\n");
                    writer.flush();

                }
            }

        } catch (Exception e) {

        }
    }

    /**
     * This method is used to check if the voyage with the given ID is in the list
     * of voyages or not.
     * 
     * @param mydata   The command that is being executed.
     * @param allmybus The list of all voyages.
     * @param error    A boolean value that indicates if there is an error or not.
     * @return A boolean value that indicates if the voyage is in the list or not.
     */
    public static boolean isIDinthere(String[] mydata, ArrayList<Bus> allmybus, boolean error) {
        int ID = Integer.parseInt(mydata[1]);
        boolean varmı = false;
        for (Bus bus : allmybus) {
            if (bus.Id == ID) {
                varmı = true;
            }
        }
        return varmı;
    }

    /**
     * This method is used to check if the voyage with the given ID was initialized
     * or not.
     * 
     * @param mydata   The command that is being executed.
     * @param allmybus The list of all voyages.
     * @param error    A boolean value that indicates if there is an error or not.
     * @return A boolean value that indicates if the voyage was initialized or not.
     */
    public static boolean wasMakeBus(String[] mydata, ArrayList<Bus> allmybus, boolean error) {
        int ID = Integer.parseInt(mydata[2]);
        boolean yapıldımı = false;
        for (Bus bus : allmybus) {
            if (bus.Id == ID) {
                yapıldımı = true;
            }
        }

        return yapıldımı;
    }

    /**
     * This method is used to check if the seats that are being refunded are sold or
     * not.
     * 
     * @param mydata   The command that is being executed.
     * @param allmybus The list of all voyages.
     * @param error    A boolean value that indicates if there is an error or not.
     * @return A boolean value that indicates if the seats are sold or not.
     */
    public static boolean isSeathere(String[] mydata, ArrayList<Bus> allmybus, boolean error) {
        boolean varmı = true;
        String allseats[] = mydata[2].split("_");
        for (Bus bus : allmybus) {
            if (bus.Id == Integer.parseInt(mydata[1])) {
                for (String seat : allseats) {
                    if (Integer.parseInt(seat) > bus.seatsOfNumber) {
                        varmı = false;
                        error = true;
                    }
                }
            }
        }
        return varmı;
    }

    /**
     * This method is used to check if the seats that are being refunded are sold or
     * not.
     * 
     * @param mydata   The command that is being executed.
     * @param allmybus The list of all voyages.
     * @param error    A boolean value that indicates if there is an error or not.
     * @return A boolean value that indicates if the seats are sold or not.
     */
    public static boolean isSoldForRefund(String[] mydata, ArrayList<Bus> allmybus, boolean error) {
        String[] seatsforrefund = mydata[2].split("_");
        boolean issold = true;
        for (Bus bus : allmybus) {
            if (bus.Id == Integer.parseInt(mydata[1])) {
                for (String seat : seatsforrefund) {
                    if (bus.Seatsofanybus[Integer.parseInt(seat) - 1].equals("*")) {
                        issold = false;

                    }
                }

            }
        }
        return issold;
    }

    /**
     * This method is used to check if the given string is a number or not.
     * 
     * @param s The string that is being checked.
     * @return A boolean value that indicates if the string is a number or not.
     */
    public static boolean isNumber(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean checkİSSEAT(String falseseat, String[] mydata, String line, BufferedWriter writer) {
        boolean resume = true;
        try {
            if (mydata.length == 3) {
                if (mydata[0].equals("SELL_TICKET")) {
                    if (!isNumber(mydata[1])) {
                        writer.write("COMMAND: " + line + "\n");
                        writer.write("ERROR: " + mydata[2]
                                + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                    } else {
                        String[] seats = mydata[2].split("_");
                        for (String u : seats) {
                            if (!isNumber(u)) {
                                writer.write("COMMAND: " + line + "\n");
                                writer.write("ERROR: " + mydata[2]
                                        + " is not a positive integer, seat number must be a positive integer!\n");
                                resume = false;
                                break;
                            }
                        }
                    }
                } else if (mydata[0].equals("REFUND_TICKET")) {
                    if (mydata[0].equals("REFUND_TICKET")) {
                        if (!isNumber(mydata[1])) {
                            writer.write("COMMAND: " + line + "\n");
                            writer.write("ERROR: " + mydata[2]
                                    + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                        } else {
                            String[] seats = mydata[2].split("_");
                            for (String u : seats) {
                                if (!isNumber(u)) {
                                    writer.write("COMMAND: " + line + "\n");
                                    writer.write("ERROR: " + mydata[2]
                                            + " is not a positive integer, seat number must be a positive integer!\n");
                                    resume = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return resume;
    }

    public static boolean checkIDisVALİD(String falseseat, String[] mydata, String line, BufferedWriter writer) {
        boolean resume = true;
        try {
            if (mydata.length == 3) {
                if (mydata[0].equals("CANCEL_VOYAGE") || mydata[0].equals("PRINT_VOYAGE")) {
                    if (!isNumber(mydata[1])) {
                        writer.write("COMMAND: " + line + "\n");
                        writer.write("ERROR: " + mydata[2]
                                + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                        resume = false;
                    }
                }
            }
        } catch (Exception e) {

        }
        return resume;
    }

    /**
     * This method processes a series of commands specified in an input file and
     * generates
     * corresponding output based on the commands executed. It handles various error
     * cases
     * and writes error messages to the output file as necessary.
     *
     * @param args an array of command line arguments, where args[0] is the path to
     *             the input file
     *             and args[1] is the path to the output file.
     */

    public static void DOiteverything(String[] args) {
        try {
            String inputfile = args[0];
            String outputfile = args[1];
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile));
            if (args.length != 2) {

                writer.write(
                        "ERROR: This program works exactly with two command line arguments, the first one is the path to the input file whereas the second one is the path to the output file. Sample usage can be as follows: \"java8 BookingSystem input.txt output.txt\". Program is going to terminate!\n");
                writer.flush();
                writer.close();
                return;
            }

            File input = new File(inputfile);
            if (!input.exists() || !input.isFile()) {
                writer.write("ERROR: This program cannot read from the \"" + inputfile
                        + "\", either this program does not have read permission to read that file or file does not exist. Program is going to terminate!\n");
                writer.flush();
                writer.close();
                return;
            }

            File outputFile = new File(outputfile);
            if (outputFile.exists()) {
                // If output file exists, check if it is writable
                if (!outputFile.canWrite()) {
                    writer.write("ERROR: This program cannot write to the \"" + outputfile
                            + "\", please check the permissions to write that directory. Program is going to terminate!\n");
                    writer.close();
                    return;
                }
            }
            BufferedReader reader = new BufferedReader(new FileReader(inputfile));

            int satirSayisi = satirSayisiniBul(inputfile);

            if (satirSayisi == 0) {
                writer.write("COMMAND: Z_REPORT\n" + //

                        "----------------\n" + //
                        "No Voyages Available!\n" + //
                        "----------------");
                writer.flush();
            }
            while ((line = reader.readLine().trim()) != null) {
                line = line.trim();
                boolean error = false;
                String[] mydata = line.split("\t");
                String bozansayı = "";
                if (!line.isEmpty() && check < satirSayisi) {

                    check++;
                    isThereErrorThere(mydata, line, reader, writer, inputfile, allmybus, error);
                    if (!error) {
                        if (mydata[0].equals("INIT_VOYAGE")) {
                            /**
                             * Initializes a new voyage.
                             * 
                             * @param mydata    the command data
                             * @param line      the command line
                             * @param reader    the input reader
                             * @param writer    the output writer
                             * @param inputfile the input file name
                             * @param allmybus  the list of all voyages
                             * @param error     the error flag
                             */
                            String[] checklist = line.split("\t");
                            if (checklist.length == 7) {
                                if (isNumber(checklist[2]) && isNumber(checklist[5]) && isNumber(checklist[6])) {
                                    INIT_VOYAGE(mydata, line, reader, writer, inputfile, allmybus, error);
                                } else {
                                    if (!isNumber(checklist[2])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[2]
                                                + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    } else if (!isNumber(checklist[5])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[5]
                                                + " is not a positive integer, row number must be a positive integer!\n");
                                    } else if (!isNumber(checklist[6])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[6]
                                                + " is not a positive integer, price must be a positive integer!\n");
                                    }
                                }
                            } else if (checklist.length == 8) {
                                if (isNumber(checklist[2]) && isNumber(checklist[5]) && isNumber(checklist[6])
                                        && isNumber(checklist[7])) {
                                    INIT_VOYAGE(mydata, line, reader, writer, inputfile, allmybus, error);
                                } else {
                                    if (!isNumber(checklist[2])) {

                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[2]
                                                + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    } else if (!isNumber(checklist[5])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[5]
                                                + " is not a positive integer, row number be a positive integer!\n");
                                    } else if (!isNumber(checklist[6])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[6]
                                                + " is not a positive integer, price be a positive integer!\n");
                                    } else if (!isNumber(checklist[7])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[7]
                                                + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!\\n");
                                    }
                                }
                            } else if (checklist.length == 9) {
                                if (isNumber(checklist[2]) && isNumber(checklist[5]) && isNumber(checklist[6])
                                        && isNumber(checklist[7]) && isNumber(checklist[8])) {
                                    INIT_VOYAGE(mydata, line, reader, writer, inputfile, allmybus, error);
                                } else {
                                    if (!isNumber(checklist[2])) {

                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[2]
                                                + " is not a positive integer, ID of a voyage must be a positive integer!\n");
                                    } else if (!isNumber(checklist[5])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[5]
                                                + " is not a positive integer, row number be a positive integer!\n");
                                    } else if (!isNumber(checklist[6])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[6]
                                                + " is not a positive integer, price be a positive integer!\n");
                                    } else if (!isNumber(checklist[7])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[7]
                                                + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!\\n");
                                    } else if (!isNumber(checklist[8])) {
                                        writer.write("COMMAND: " + line + "\n");
                                        writer.write("ERROR: " + checklist[8]
                                                + " is not an integer that is in range of [0, 100], Premimum Fee must be an integer that is in range of [0, 100]!\\\\n");
                                    }
                                }
                            }
                        } else if (mydata[0].equals("SELL_TICKET")) {

                            /**
                             * Sells a ticket.
                             * 
                             * @param mydata    the command data
                             * @param reader    the input reader
                             * @param writer    the output writer
                             * @param line      the command line
                             * @param allmybus  the list of all voyages
                             * @param error     the error flag
                             * @param bozansayı the number of seats to sell
                             */
                            if (checkİSSEAT(inputfile, mydata, line, writer)) {
                                SELL_TICKET(mydata, reader, writer, line, allmybus, error, bozansayı);
                            }
                        } else if (mydata[0].equals("REFUND_TICKET")) {
                            /**
                             * Refunds a ticket.
                             * 
                             * @param mydata   the command data
                             * @param reader   the input reader
                             * @param writer   the output writer
                             * @param line     the command line
                             * @param allmybus the list of all voyages
                             * @param error    the error flag
                             */
                            if (checkİSSEAT(inputfile, mydata, line, writer)) {

                                REFUND_TICKET(mydata, reader, writer, line, allmybus, error);
                            }

                        } else if (mydata[0].equals("CANCEL_VOYAGE")) {
                            /**
                             * Cancels a voyage.
                             * 
                             * @param mydata   the command data
                             * @param reader   the input reader
                             * @param writer   the output writer
                             * @param line     the command line
                             * @param allmybus the list of all voyages
                             * @param error    the error flag
                             */
                            if (checkIDisVALİD(inputfile, mydata, line, writer)) {
                                CANCEL_VOYAGE(mydata, reader, writer, line, allmybus, error);
                            }
                        } else if (mydata[0].equals("PRINT_VOYAGE")) {
                            /**
                             * Prints a voyage.
                             * 
                             * @param mydata   the command data
                             * @param reader   the input reader
                             * @param writer   the output writer
                             * @param line     the command line
                             * @param allmybus the list of all voyages
                             * @param error    the error flag
                             */
                            if (checkIDisVALİD(inputfile, mydata, line, writer)) {

                                PRINT_VOYAGE(mydata, reader, writer, line, allmybus, error);
                            }

                        } else if (mydata[0].equals("Z_REPORT") && mydata.length == 1) {
                            /**
                             * Prints a report.
                             * 
                             * @param mydata      the command data
                             * @param reader      the input reader
                             * @param writer      the output writer
                             * @param line        the command line
                             * @param allmybus    the list of all voyages
                             * @param check       the current line number
                             * @param satirSayisi the total number of lines
                             * @param error       the error flag
                             */
                            Z_REPORT(mydata, reader, writer, line, allmybus, check, satirSayisi, error);
                        }
                        if (check == satirSayisi && !mydata[0].equals("Z_REPORT")) {
                            Z_REPORT(mydata, reader, writer, line, allmybus, check, satirSayisi, error);
                        }
                    }
                }
            }
            writer.close();

            reader.close();
        } catch (Exception e) {
        }
    }

}