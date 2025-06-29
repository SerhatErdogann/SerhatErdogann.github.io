import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DigerPanelController implements Initializable {
	
    @FXML
    private StackPane dynamicContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Uygulama ilk açıldığında mesaj gezgini yüklensin
        showMessageExplorer();
    }
    
    @FXML
    private void showMessageExplorer() {
        loadContent("message_explorer.fxml");
    }

    @FXML
    private void showScripts() {
        loadContent("scripts_view.fxml");
    }

    @FXML
    private void showSentMessages() {
        loadContent("sent_messages.fxml");
    }

    private void loadContent(String fxmlFile) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlFile));
            dynamicContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
