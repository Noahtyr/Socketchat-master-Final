package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2
{
    public static void main(String[] args)
    {
        try
        {
            Socket s = new Socket("127.0.0.1", 8001);

            while(true)
            {
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
                Når man arbejder med netværk, er det vigtigt at klient og server
                har en protokol som klart fortæller hvordan kommunikationen
                mellem de to skal foregå
                I dette tilfælde ved vi, at serveren sender en velkomst
                Og vi ved, at den derefter returnerer det som den modtager.
                Vi skal derfor overholde den protokol, ved først at modtage velkomestbeskeden
                Derefter overholder serveren request/response designmønster
                */

                // Modtag velkomst
                String welcome = in.nextLine();
                System.out.println(welcome);

                // Nu kan vi så sende og modtage respektivt
                out.println("NAME: nichlas");
                // Vi udskriver nu bare direkte
                System.out.println("Svar fra svr: " + in.nextLine());

                out.println("PUT: goddag");
                // Vi udskriver nu bare direkte
                System.out.println("Svar fra svr: " + in.nextLine());

                out.println("COUNT");
                // Vi udskriver nu bare direkte
                System.out.println("Svar fra svr: " + in.nextLine());
                System.out.println("Antal fra svr: " + in.nextLine());

                out.println("GET 1");
                // Vi udskriver nu bare direkte
                System.out.println("Svar fra svr: " + in.nextLine());

                out.println("Error");
                // Vi udskriver nu bare direkte
                System.out.println(in.nextLine());

                // Denne gang lukker vi selv forbindelsen. Fordi vi kan.
                // Vi kunne også have brugt "luk ned"
                s.close();
                System.out.println("Forbindelsen lukket.");
            }
        }
        catch (IOException ex)
        {
            // We ignore this
        }
    }
}