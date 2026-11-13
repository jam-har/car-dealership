package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DealershipFileManagement {
    public Dealership getDealership() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));
            String line;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void saveDealership() {}
}
