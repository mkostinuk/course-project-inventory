package org.example.app.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AccountService {
    private static final String ACCOUNT_FILE = "account_balance.txt";

    public static double loadBalance() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(ACCOUNT_FILE));
            return Double.parseDouble(lines.getFirst());
        } catch (IOException | NumberFormatException e) {
            return 0.0;
        }
    }

    public static void saveBalance(double balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            writer.write(String.valueOf(balance));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
