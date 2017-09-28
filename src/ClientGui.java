import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientGui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("socketGUI.fxml"));
        primaryStage.setTitle("Chitter Chatter By Noah Juaquin");
        primaryStage.setScene(new Scene(root, 615, 430));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
