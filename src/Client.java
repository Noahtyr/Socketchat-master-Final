import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Vigtigt! det her er Client 1 vi har valgt at beholde den for at sammenligne den med Client 2
// vi har valgt at go væk fra en MultiThreaded server og i stedet bruge en single threaded server
// hvis du ikke kan se client2 ude til venstre så burde der gerne være en mappe der hedder com.company

public class Client {

    public static void main(String[]args){

        try{
            Socket s = new Socket("127.0.0.1",8001);

            while (true){
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();
                 Scanner humaninput = new Scanner(System.in);

                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                String welcome = in.nextLine();
                System.out.println(welcome);

                out.println("Første besked Dude");
                System.out.println("først svar fra severen " + in.nextLine());


                OutputStream output2 = s.getOutputStream();
                PrintWriter out2 = new PrintWriter(output2,true);
                out2.println("Anden besked er kommet!!!");
                System.out.println(in.nextLine());

                out2.println("Tredje Linje det virker min ven");
                System.out.println(in.nextLine());



                boolean running = true;
                while (running){

                    String xHuman = humaninput.nextLine();



                    out.println(xHuman);
                  System.out.println(humaninput.nextLine());
                }

                s.close();
                System.out.println("Forbindelsen er lukket Peace out");
            }
        }
        catch (IOException ex)
        {

        }

    }
}
