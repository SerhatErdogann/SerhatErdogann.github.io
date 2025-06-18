package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Sunucu " + port + " portunda başlatıldı. Bekleniyor...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Yeni istemci bağlandı: " + clientSocket.getInetAddress());

                // Her istemciyi ayrı bir thread ile işlemek mantıklı olur, şimdilik basit versiyon:
                handleClient(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("İstemciden gelen: " + message);
                }

                clientSocket.close();
                System.out.println("İstemci bağlantısı kapatıldı.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start(12345); // Client tarafındaki portla aynı olmalı
    }
}
