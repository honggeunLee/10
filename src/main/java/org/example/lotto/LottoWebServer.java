package org.example.lotto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class LottoWebServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("HTTP Server started. Listening on port 5555...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Read request headers
                    String requestLine = in.readLine();
                    if (requestLine == null) {
                        continue;
                    }

                    // Parse request to get the HTTP method and path
                    String[] requestParts = requestLine.split(" ");
                    String httpMethod = requestParts[0];
                    String path = requestParts[1];

                    // Check if it's a GET request and handle accordingly
                    if ("GET".equalsIgnoreCase(httpMethod)) {
                        if (path.startsWith("/lotto")) {
                            handleLottoRequest(path, out);
                        } else {
                            handleNotFoundResponse(out);
                        }
                    } else {
                        handleMethodNotAllowed(out);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleLottoRequest(String path, PrintWriter out) {
        // Default to 1 game if no number is provided
        int numGames = 1;

        // Extract number from query string if present
        if (path.contains("?")) {
            String query = path.split("\\?")[1];
            if (query.startsWith("num=")) {
                try {
                    numGames = Integer.parseInt(query.substring(4));
                } catch (NumberFormatException e) {
                    // Default to 1 if parsing fails
                    numGames = 1;
                }
            }
        }

        StringBuilder lottoNumbers = new StringBuilder();

        for (int i = 0; i < numGames; i++) {
            lottoNumbers.append(generateLottoNumbers()).append("<br>");
        }

        // Write HTTP response headers
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println();

        // Write HTML content
        out.println("<html><body>");
        out.println("<h2>Generated Lotto Numbers</h2>");
        out.println("<p>" + lottoNumbers.toString() + "</p>");
        out.println("</body></html>");

        out.flush();
    }

    private static void handleNotFoundResponse(PrintWriter out) {
        out.println("HTTP/1.1 404 Not Found");
        out.println("Content-Type: text/plain");
        out.println();
        out.println("404 Not Found");
        out.flush();
    }

    private static void handleMethodNotAllowed(PrintWriter out) {
        out.println("HTTP/1.1 405 Method Not Allowed");
        out.println("Content-Type: text/plain");
        out.println();
        out.println("405 Method Not Allowed");
        out.flush();
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
