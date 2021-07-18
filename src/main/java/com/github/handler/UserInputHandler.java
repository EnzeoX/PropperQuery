package com.github.handler;

import com.github.utils.DataExtractor;
import com.github.utils.ScannerInput;

public class UserInputHandler {

    public void functionSelector() {
        System.out.println("From where should be input?\n -File (F)\n -User input (U)");
        String inputType = ScannerInput.userInput();
        if (inputType.equalsIgnoreCase("F")) {
            fileInputFunction();
        } else if (inputType.equalsIgnoreCase("U")) {
            userInputFunction();
        } else {
            System.out.println("No match for this function");
        }
    }

    private void userInputFunction() {
        QueryHandler.querySelector(DataExtractor.getDataFromInput());
    }

    private void fileInputFunction() {
        QueryHandler.querySelector(DataExtractor.getDataFromFile());
    }
}
