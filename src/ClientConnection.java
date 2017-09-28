import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.PrintWriter;


public class ClientConnection implements Runnable {
    private Socket s;
    String name = " ";


    public ClientConnection(Socket s) throws SocketException, IOException {
        this.s = s;
    }

    @Override
    public void run() {

        try {
            try {
                /*
                Vi har behov for at kommunikere med serveren. Vi opretter derfor
                en input og en output stream, og binder hver isæt til Socket'ens
                input og output stream.
                Sockets kører i full-duplex og der er dermed tovejs kommunikation
                til rådighed.
                */
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                /*
                Til at læse input streamen med bruger vi her en scanner.
                Det kunne lige så godt have været en BufferedReader
                (hvis forbindelsen der modtages fra lukker efter sig,
                ellers stopper den aldrig med at læse)
                */
                Scanner in = new Scanner(input);

                // Når vi skriver til output streamen bruger vi her en PrintWriter.
                PrintWriter out = new PrintWriter(output, true);

                /*
                Lad os sige velkommen til den der har forbundet til serveren,
                så den ved der er hul igennem.
                */
                out.println("Velkommen Min Ven :) ");

                /*
                Vi ønsker at have kontrol over hvornår forbindelsen skal lukkes
                fra denne side.
                Vi ønsker kun at lukke forbindelse, når brugeren skriver "luk ned"
                */
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
                        out.println(stream);
                    }

                }


                {
                    s.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}




