import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ConnectionSettingsController {

    @FXML
    private TextField portField;

    @FXML
    private CheckBox reconnectCheckBox;

    @FXML
    private TextField reconnectPeriodField;

    // Getter metodlarıyla dışarıdan erişim sağlanabilir
    public String getPort() {
        return portField.getText();
    }

    public boolean isReconnectEnabled() {
        return reconnectCheckBox.isSelected();
    }

    public String getReconnectPeriod() {
        return reconnectPeriodField.getText();
    }

    public void setInitialValues(String port, boolean reconnect, String period) {
        portField.setText(port);
        reconnectCheckBox.setSelected(reconnect);
        reconnectPeriodField.setText(period);
    }
}
