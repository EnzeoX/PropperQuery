package com.github.utils;

import com.github.controller.WaitingController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.github.utils.QueryParameters.LINE_COUNT;

public class DataExtractor {

    private WaitingController waitingController;

    public DataExtractor(WaitingController waitingController) {
        this.waitingController = waitingController;
    }

    public static ArrayList<String> getDataFromInput() {
        System.out.println("Enter queries quantity");
        ArrayList<String> listOfQuery = new ArrayList<>();
        int queryQuantity = 0;
        try {
            queryQuantity = Integer.parseInt(ScannerInput.userInput());
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
        BufferedReader br;
        int linesCount = 0;
        boolean firstRead = false;
        ArrayList<String> listOfQuery = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("./testfile2.txt"));
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
            firstRead = false;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfQuery;
    }
}
