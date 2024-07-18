package org.example.lotto;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class LC1 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5555);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("INCERT MONEY: ");
            String amount = scanner.nextLine();
            out.println(amount);

            String lottoNumbers;
            while ((lottoNumbers = in.readLine()) != null) {
                System.out.println("Lotto Number:\n" + lottoNumbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
