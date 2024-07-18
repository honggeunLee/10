package org.example.gpt;

import java.io.*;
import java.net.*;
import java.util.*;

public class LottoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port 8080");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String input = in.readLine();
                    int count = Integer.parseInt(input);
                    List<Set<Integer>> lottoNumbers = generateLottoNumbers(count);

                    out.println("Generated Lotto Numbers:");
                    for (Set<Integer> numbers : lottoNumbers) {
                        out.println(numbers);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Set<Integer>> generateLottoNumbers(int count) {
        List<Set<Integer>> lottoNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Set<Integer> numbers = new TreeSet<>();
            while (numbers.size() < 6) {
                numbers.add(random.nextInt(45) + 1);
            }
            lottoNumbers.add(numbers);
        }

        return lottoNumbers;
    }
}
