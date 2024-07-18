package org.example.lotto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class LottoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Ready to receive amount...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String amount = in.readLine();
                System.out.println("Received amount: " + amount);

                String lottoNumbers = generateLottoNumbers();
                System.out.println("Generated lotto numbers: " + lottoNumbers);

                out.println(lottoNumbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateLottoNumbers() {
        Random random = new Random();
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            numbers.append(random.nextInt(45) + 1).append(" ");
        }
        return numbers.toString().trim();
    }
}
