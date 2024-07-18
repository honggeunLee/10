package org.example.lotto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class LS1 {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Ready.........................");

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String amount = in.readLine();
                System.out.println("Received amount: " + amount);

                int numGames = Integer.parseInt(amount) / 1000;
                StringBuilder lottoNumbers = new StringBuilder();

                for (int i = 0; i < numGames; i++) {
                    lottoNumbers.append(generateLottoNumbers()).append("\n");
                }

                System.out.println("Generated lotto numbers:\n" + lottoNumbers);
                out.println(lottoNumbers.toString().trim());
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
