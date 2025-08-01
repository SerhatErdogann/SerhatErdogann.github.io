import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ServislerController {

    @FXML
    private ListView<String> directoryListView; // Görsel olarak boş kalacak

    @FXML
    private ListView<String> messageListView;

    @FXML
    private TableView<LogEntry> logTableView; // Kayıtlar bölümünden seçilen mesajlar

    private final File baseDir = new File("saved_messages");

    @FXML
    private void initialize() {
        if (!baseDir.exists()) baseDir.mkdirs();
        refreshMessageView(); // Sadece sağ taraftaki görünüm
    }

    // ----------- BUTON İŞLEMLERİ -----------

    @FXML
    private void handleCreateDirectory() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Yeni Dizin Oluştur");
        dialog.setHeaderText("Yeni dizin adını girin:");
        dialog.setContentText("Dizin adı:");

        dialog.showAndWait().ifPresent(name -> {
            File newDir = new File(baseDir, name);
            if (newDir.exists()) {
                showAlert("Uyarı", "Bu adla zaten bir dizin var.");
                return;
            }

            if (newDir.mkdir()) {
                messageListView.getItems().add("[Dizin] " + name);
                showAlert("Başarılı", "Yeni dizin oluşturuldu: " + name);
            } else {
                showAlert("Hata", "Dizin oluşturulamadı.");
            }
        });
    }

    @FXML
    private void handleStarMessage() {
        String selectedItem = getSelectedDirectoryFromView();
        if (selectedItem == null) {
            showAlert("Uyarı", "Lütfen önce bir dizin seçin.");
            return;
        }

        // Dizin adı işlemi
        File dir = new File(baseDir, selectedItem.replace("[Dizin] ", ""));
        if (!dir.exists()) {
            showAlert("Hata", "Seçilen dizin dosya sisteminde yok.");
            return;
        }

        // Mesaj için ad girme
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mesajı Yıldızla");
        dialog.setHeaderText("Mesaj için bir ad girin:");
        dialog.setContentText("Mesaj adı:");

        dialog.showAndWait().ifPresent(name -> {
            File outFile = new File(dir, name + ".txt");

            try (FileWriter writer = new FileWriter(outFile)) {
                // Burada mesaj yazılır
                writer.write("Mesaj adı: " + name + "\n");

                // Diğer bilgileri ekleyebiliriz
                writer.write("Level: Dummy Level\n");
                writer.write("Message: Dummy Message\n");
                writer.write("Source: Dummy Source\n");
                writer.write("Time: " + System.currentTimeMillis() + "\n");

                // Listeye ekleme
                messageListView.getItems().add("  ↳ " + name + ".txt");
                showAlert("Başarılı", "Mesaj kaydedildi: " + name);
            } catch (IOException e) {
                showAlert("Hata", "Dosya yazılamadı: " + e.getMessage());
            }
        });
    }


    @FXML
    private void handleSendMessage() {
    }

    @FXML
    private void handleDeleteDirectory() {
        String selected = getSelectedDirectoryFromView();
        if (selected == null) {
            showAlert("Uyarı", "Lütfen silinecek dizini seçin.");
            return;
        }

        String dirName = selected.replace("[Dizin] ", "");
        File dir = new File(baseDir, dirName);

        if (deleteDirectoryRecursively(dir)) {
            messageListView.getItems().removeIf(item ->
                item.equals("[Dizin] " + dirName) || item.startsWith("  ↳")
            );
            showAlert("Başarılı", "Dizin silindi: " + dirName);
        } else {
            showAlert("Hata", "Dizin silinemedi.");
        }
    }

    @FXML
    private void handleConnectionSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("connection_settings.fxml"));
            DialogPane dialogPane = loader.load();
            ConnectionSettingsController controller = loader.getController();

            // İsteğe bağlı olarak ön değerleri set edebilirsin
            controller.setInitialValues("60000", true, "5000");

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Bağlantı Ayarları");
            dialog.setDialogPane(dialogPane);

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    String port = controller.getPort();
                    boolean reconnect = controller.isReconnectEnabled();
                    String period = controller.getReconnectPeriod();

                    // Test amaçlı console’a yazalım
                    System.out.println("Port: " + port);
                    System.out.println("Reconnect: " + reconnect);
                    System.out.println("Period: " + period);

                    // Burada config dosyasına yazma ya da bağlantı başlatma gibi işlemler yapılabilir
                    showAlert("Ayar Kaydedildi", "Port: " + port + "\nReconnect: " + reconnect + "\nPeriod: " + period);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Bağlantı ayarları diyalogu yüklenemedi:\n" + e.getMessage());
        }
    }

    
    

    @FXML
    private void handleLogFiltering() {
        try {
            // Log filtreleme penceresini yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("log_filter_dialog.fxml"));
            DialogPane dialogPane = loader.load();
            LogFilterController controller = loader.getController();

            // Log filtreleme penceresi
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Log Filtreleme");
            dialog.setDialogPane(dialogPane);

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    // Kullanıcının seçimlerini al
                    boolean isStatusInfoSelected = controller.isStatusInfoSelected();
                    boolean isStatusRequestSelected = controller.isStatusRequestSelected();
                    boolean isRfStatusSelected = controller.isRfStatusSelected();

                    // Bu bilgileri kullanarak logları filtrele
                    System.out.println("Durum Bilgisi: " + isStatusInfoSelected);
                    System.out.println("Durum Bilgisi İsteği: " + isStatusRequestSelected);
                    System.out.println("RFDurum: " + isRfStatusSelected);

                    // Bu seçime göre logları gösterme (örneğin, güncellenmiş logları yükleyebilirsin)
                    showAlert("Log Filtreleme", "Filtreleme tamamlandı.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Log filtreleme penceresi yüklenemedi:\n" + e.getMessage());
        }
    }


    @FXML
    private void handleRecordMarking() {
    }

    // ----------- DESTEK METOTLAR -----------
    
    private void refreshMessageView() {
        messageListView.getItems().clear();

        File[] dirs = baseDir.listFiles(File::isDirectory);
        if (dirs != null) {
            for (File dir : dirs) {
                messageListView.getItems().add("[Dizin] " + dir.getName());

                File[] messages = dir.listFiles((d, name) -> name.endsWith(".txt"));
                if (messages != null) {
                    for (File msg : messages) {
                        messageListView.getItems().add("  ↳ " + msg.getName());
                    }
                }
            }
        }
    }

    private boolean deleteDirectoryRecursively(File dir) {
        if (dir.isDirectory()) {
            File[] entries = dir.listFiles();
            if (entries != null) {
                for (File file : entries) {
                    deleteDirectoryRecursively(file);
                }
            }
        }
        return dir.delete();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String getSelectedDirectoryFromView() {
        String selected = messageListView.getSelectionModel().getSelectedItem();
        if (selected != null && selected.startsWith("[Dizin] ")) {
            return selected;
        }
        return null;
    }
}
