import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class LogFilterController {

    @FXML
    private CheckBox statusInfoCheckBox;

    @FXML
    private CheckBox statusRequestCheckBox;

    @FXML
    private CheckBox rfStatusCheckBox;

    public boolean isStatusInfoSelected() {
        return statusInfoCheckBox.isSelected();
    }

    public boolean isStatusRequestSelected() {
        return statusRequestCheckBox.isSelected();
    }

    public boolean isRfStatusSelected() {
        return rfStatusCheckBox.isSelected();
    }

    // Bu metod, OK butonuna basıldığında çağrılacak
    @FXML
    private void handleApplyFilter() {
        // Burada filtrelemeyi yapacak kodu yazacağız
        System.out.println("Durum Bilgisi: " + isStatusInfoSelected());
        System.out.println("Durum Bilgisi İsteği: " + isStatusRequestSelected());
        System.out.println("RFDurum: " + isRfStatusSelected());
        
        // Filtremeyi başlat, örneğin logları filtrele:
        // LogFilterEngine.filterLogs(isStatusInfoSelected(), isStatusRequestSelected(), isRfStatusSelected());
        
        // Pencereyi kapat
        ((javafx.stage.Stage) statusInfoCheckBox.getScene().getWindow()).close();
    }
}
