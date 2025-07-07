import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class ConsoleBridge {
    private static final List<String> buffer = new LinkedList<>();
    private static TextArea attachedArea;

    private static PrintStream originalOut = System.out;
    private static PrintStream originalErr = System.err;

    public static void initialize() {
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                String s = String.valueOf((char) b);

                // Konsolda da göster
                originalOut.print(s); // Eclipse Console

                // Hafızaya al
                synchronized (buffer) {
                    buffer.add(s);
                    if (buffer.size() > 10000) buffer.remove(0);
                }

                // TextArea varsa yaz
                if (attachedArea != null) {
                    Platform.runLater(() -> attachedArea.appendText(s));
                }
            }
        }, true);

        System.setOut(ps);
        System.setErr(ps); // istersek originalErr de yazabiliriz
    }

    public static void attachTextArea(TextArea area) {
        attachedArea = area;
        synchronized (buffer) {
            Platform.runLater(() -> {
                for (String s : buffer) {
                    area.appendText(s);
                }
            });
        }
    }

    public static void detachTextArea() {
        attachedArea = null;
    }
}
