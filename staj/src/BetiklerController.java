import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BetiklerController implements Initializable {

    @FXML
    private StackPane dynamicArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showScriptPanel();  // Başlangıçta Scriptler paneli açık olsun
    }

    @FXML
    private void showScriptPanel() {
        loadPanel("script_panel.fxml");
    }

    @FXML
    private void showOutputPanel() {
        loadPanel("output_panel.fxml");
    }

    private void loadPanel(String fxmlFile) {
        try {
            Node content = FXMLLoader.load(getClass().getResource(fxmlFile));
            dynamicArea.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
