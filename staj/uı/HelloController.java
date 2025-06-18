package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private TextField ipField;
    @FXML private TextField portField;
    @FXML private TextField messageField;
    @FXML private TextArea logArea;

    private TCPClient client;

    @FXML
    protected void connectToServer() {
        String ip = ipField.getText();
        String portStr = portField.getText();

        try {
            int port = Integer.parseInt(portStr);
            client = new TCPClient(ip, port);
            logArea.appendText("Bağlantı başarılı: " + ip + ":" + port + "\n");
        } catch (Exception e) {
            logArea.appendText("Bağlantı hatası: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    @FXML
    protected void sendMessage() {
        if (client == null) {
            logArea.appendText("Lütfen önce bağlanın.\n");
            return;
        }

        String msg = messageField.getText();
        if (msg != null && !msg.isEmpty()) {
            client.sendMessage(msg);
            logArea.appendText("Gönderildi: " + msg + "\n");
            messageField.clear();
        }
    }
}
