import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ScriptPanelController {

    @FXML
    private ListView<String> scriptListView;

    @FXML
    private TextArea scriptContentArea;

    private final Map<String, File> scriptMap = new HashMap<>();

    @FXML
    private void initialize() {
        scriptListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && scriptMap.containsKey(newVal)) {
                loadScriptContent(scriptMap.get(newVal));
            }
        });
    }

    @FXML
    private void handleSelectScript() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Script Dosyası Seç");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Script Files", "*.txt", "*.py", "*.sh", "*.*"));

        Window window = scriptListView.getScene().getWindow();
        File file = chooser.showOpenDialog(window);

        if (file != null) {
            String name = file.getName();
            scriptMap.put(name, file);
            if (!scriptListView.getItems().contains(name)) {
                scriptListView.getItems().add(name);
            }
            loadScriptContent(file);
        }
    }

    private void loadScriptContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            scriptContentArea.setText(sb.toString());
        } catch (IOException e) {
            scriptContentArea.setText("Dosya okunamadı: " + e.getMessage());
        }
    }
}
