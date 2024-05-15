package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new RunnerApp();
        } catch (FileNotFoundException f) {
            System.out.println("Error: File cannot be found.");
        }
    }
}
