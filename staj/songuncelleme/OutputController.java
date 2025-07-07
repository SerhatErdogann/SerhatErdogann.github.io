import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.PrintStream;

public class OutputController {

    @FXML
    private TextArea outputArea;

    @FXML
    private void initialize() {
    	 ConsoleBridge.attachTextArea(outputArea);
         System.out.println("OutputController yüklendi.");

    }

    // Temizleme fonksiyonu
    @FXML
    private void handleClearLogs() {
        outputArea.clear();
    }

    // TextArea'ya log yazan özel OutputStream
    private static class TextAreaOutputStream extends OutputStream {
        private final TextArea textArea;

        public TextAreaOutputStream(TextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            // Karakteri JavaFX thread'ine güvenli şekilde ekle
            javafx.application.Platform.runLater(() -> textArea.appendText(String.valueOf((char) b)));
        }
    }

    // Birden fazla çıkışa aynı anda yazabilen OutputStream
    private static class MultiOutputStream extends OutputStream {
        private final OutputStream[] streams;

        public MultiOutputStream(OutputStream... streams) {
            this.streams = streams;
        }

        @Override
        public void write(int b) throws java.io.IOException {
            for (OutputStream os : streams) {
                os.write(b);
            }
        }

        @Override
        public void flush() throws java.io.IOException {
            for (OutputStream os : streams) {
                os.flush();
            }
        }

        @Override
        public void close() throws java.io.IOException {
            for (OutputStream os : streams) {
                os.close();
            }
        }
    }
}
