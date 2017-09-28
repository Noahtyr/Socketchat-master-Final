

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;



public class Server {



    public static void main(String[] args) {
        ArrayList array = new ArrayList();
        String name = "Guest";
        String besked = "";

        // ServerSocket oprettes og port 8001 angives som den der skal lyttes på
        ServerSocket ss;
        try {
            ss = new ServerSocket(8001);

            System.out.println("Server kører...");

            /*array.add(" test name 1 ");
            array.add(" test name 2 ");
            array.add(" test name 3 ");
            */
            while (true) {
                // Så længe der ikke er oprettet en forbindelse, venter serveren her
                // Så snart der anmodes om en forbindelse accepteres den med accept()

                Socket incoming = ss.accept();
                System.out.println("Client connected");

                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output, true);

                out.println(" velkommen");


                boolean done = false;
                while (!done && in.hasNextLine()) {
                    /*
                    Her starter scannerens arbejde. Hvis der ikke er nogle
                    linier, afventer den til der kommer en.
                    */
                    String stream = in.nextLine();
                    if (stream.equals("luk ned")) {
                        done = true;
                    } else {
                        // Når vi skriver, sender vi en linie med PrintWriter
                         out.println(stream); // svar til clienten
                        System.out.println("Modtog besked " + beskedFortolker(stream));
                        if (beskedFortolker(stream)=="NAME"){
                            name = new String(stream.getBytes()).replace("NAME:", "");
                            System.out.println("Navn er ændret til " + name);
                        }
                        if (beskedFortolker(stream)=="PUT"){
                            besked =  new String(stream.getBytes()).replace("PUT: ", "");
                            String beskedDerGemmes = name + " - " + besked;
                            array.add(beskedDerGemmes);
                            System.out.println(array.get(0) + " er tilføjet til arrayet. ");
                        }
                        if (beskedFortolker(stream)== "COUNT") {
                            int count = array.size();
                            out.println("Antal linjer i chatten er " + count);
                        }
                        if (beskedFortolker(stream)== "GET") {
                            int x = 1;
                            besked = array.get(x).toString();
                            out.println(x + ". element i arrayet er:  " + besked);
                        }

                    }
                }

                incoming.close();
                System.out.println("Forbindelsen til klienten blev lukket.");

/*               VIGTIGT! denne kode er den gamle kode jeg brugte til min Client som ikke virkede
//               jeg har valgt at beholde den for at sammenline den med den kode jeg bruger nu

                String besked = in.nextLine();
                System.out.println("modtog besked " + besked);
                String name = besked.substring(4);
                if (besked.startsWith("PUT:")) {
                    array.add(name + " ");
                    System.out.println(name + " er Tilføjet til arrayet ");
                }
                else if(besked.startsWith("NAME:")){
                    name = new String(besked.getBytes()).replace("NAME:", "");
                }
                else if (besked.startsWith("COUNT:")){
                    out.println(array.size());
                }
                else if (besked.startsWith("GET:x")){
                }
                else{
                    name = "Guest";}
                out.println(name);
                //out.print(array);
 */              /* Runnable r = new ClientConnection(incoming);
                Thread t = new Thread(r);
                t.start();*/
            }

                /*
                Den nye forbindelse sendes til vores ClientConnection object,
                hvor al logik for serveren bliver håndteret.
                Der oprettes en ny tråd, som modtager ClientConnection objektet
                og tråden startes.
                */


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String beskedFortolker(String besked){
        String beskedType = "ERROR";
        if(besked.startsWith("NAME:")){
           beskedType = "NAME";
        }
        if(besked.startsWith("PUT:")){
            beskedType = "PUT";
        }
        if(besked.startsWith("COUNT")){
            beskedType = "COUNT";
        }
        if(besked.startsWith("GET:")){
            beskedType = "GET";
        }
        return beskedType;
    }
}

