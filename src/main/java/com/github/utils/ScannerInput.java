package com.github.utils;

import java.util.Scanner;

public class ScannerInput {

    public static String userInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
