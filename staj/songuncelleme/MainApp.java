import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        
    	ConsoleBridge.initialize();
    	Logger rootLogger = Logger.getLogger("");
        rootLogger.addHandler(new TableLogHandler(GlobalLogHolder.logData));
    	
    	Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("Simülator Arayüzü");
        stage.setScene(scene);
        stage.show();
        
        
        Logger.getLogger("TcpLogger").log(Level.INFO, "Veri alındı. ");

        
    }

    public static void main(String[] args) {
    	System.out.println("Test çıktısı: uygulama başlıyor...");
        launch(args);
    }
}
