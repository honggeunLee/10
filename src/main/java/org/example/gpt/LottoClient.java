package org.example.gpt;

import java.io.*;
import java.net.*;

public class LottoClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter the number of Lotto sets: ");
            String input = userInput.readLine();
            out.println(input);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
