import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class Controller {

    @FXML
    public Button sendMsg;
    @FXML
    public TextArea chatWindow;
    @FXML
    public TextField text;
    @FXML
    public Button count;


    public Socket s;
    public OutputStream oPut;
    public InputStream iPut;
    public PrintWriter pw;



    public void buttonPress(ActionEvent event){

        System.out.println("User Has logged in...");

    }
    @FXML
    public void send() {
        try {
            String message = text.getText();
            message = "" + message;
            chatWindow.appendText("\n"+message );

            Socket s = new Socket("127.0.0.1",8001);
            InputStream input = s.getInputStream();
            OutputStream output = s.getOutputStream();
            PrintWriter out = new PrintWriter(output,true);
            out.println(message);

        } catch (Exception ex){
            ex.printStackTrace();



            }
        }


    //der her er vores count button  vi nåded ikke at få den til at virke.
    @FXML
    public void Count(ActionEvent actionEvent){
        try {
            String message = text.getText();
            message = "" + message;
            chatWindow.appendText("\n" + message);

            Socket s = new Socket("127.0.0.1",8001);
            InputStream input = s.getInputStream();
            OutputStream output = s.getOutputStream();
            PrintWriter out = new PrintWriter(output,true);
            out.println(message);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void count(ActionEvent actionEvent) {
    }
}

