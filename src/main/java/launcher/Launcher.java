package launcher;

import database.Bootstrap;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Launcher extends Application {
    public static boolean BOOTSTRAP = false;

    public static void main(String[] args){
        launch(args);
    }

    private static void bootstrap(){
        if (BOOTSTRAP){
            try{
                new Bootstrap().execute();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        bootstrap();

        ComponentFactory componentFactory = ComponentFactory.getInstance(false, primaryStage);
    }
}
