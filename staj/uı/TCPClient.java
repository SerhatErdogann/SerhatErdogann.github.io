package com.example.demo;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    private Socket socket;
    private PrintWriter out;

    public TCPClient(String host, int port) throws Exception {
        socket = new Socket(host, port);
        OutputStream output = socket.getOutputStream();
        out = new PrintWriter(output, true);
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void close() {
        try {
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
