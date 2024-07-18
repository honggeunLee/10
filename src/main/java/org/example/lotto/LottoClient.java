package org.example.lotto;

import java.io.*;
import java.net.Socket;

public class LottoClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5555);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String amount = "10000";
            out.println(amount);
            System.out.println("Sent amount: " + amount);

            String lottoNumbers = in.readLine();
            System.out.println("Received lotto numbers: " + lottoNumbers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
