package com.github.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.github.utils.QueryParameters.LINE_COUNT;

public class DataExtractor {

    public static ArrayList<String> getDataFromInput() {
        System.out.println("Enter queries quantity");
        ArrayList<String> listOfQuery = new ArrayList<>();
        try {
            int queryQuantity = Integer.parseInt(ScannerInput.userInput());
            if (queryQuantity > LINE_COUNT || queryQuantity < 1) {
                System.out.println("The number of lines must be no more than 100,000 and no less than 1");
                listOfQuery = getDataFromInput();
            } else {
                System.out.println("Enter query:");
                for (int i = 0; i < queryQuantity; i++) {
                    listOfQuery.add(ScannerInput.userInput());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Only number input is acceptable");
            listOfQuery = getDataFromInput();
        }
        return listOfQuery;
    }

    public static ArrayList<String> getDataFromFile() {
        String st;
        int linesCount = 0;
        boolean firstRead = false;
        ArrayList<String> listOfQuery = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./testfile.txt"))) {
            st = br.readLine();
            while (st != null) {
                if (!firstRead) {
                    try {
                        linesCount = Integer.parseInt(st);
                        firstRead = true;
                    } catch (IllegalArgumentException ignore) {
                    }
                    if (linesCount > LINE_COUNT || linesCount < 0)
                        throw new IllegalArgumentException("The number of lines must be no more than 100,000");
                } else {
                    listOfQuery.add(st);
                }
                st = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listOfQuery;
    }
}
