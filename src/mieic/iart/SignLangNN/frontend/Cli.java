package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.database.DBReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wso277 on 4/14/14.
 */
public class Cli {

    public Cli() {
        menu();
    }

    public void menu() {

        Integer option = 0;
        while (option != 3) {
            System.out.println("\nSignNeuralNetwork Menu\n");
            System.out.println("1. Load new database");
            System.out.println("2. Identify a sample gesture");
            System.out.println("3. Exit\n\n");
            System.out.print("Option: ");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            try {
                option = Integer.parseInt(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (option) {
                case 1:
                    newDatabaseMenu();
                    break;
                case 2:
                    identifyGestureMenu();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Option. Try again.\n");
                    break;
            }
        }
    }

    private void newDatabaseMenu() {

        System.out.print("\nInsert database path (example: database/tctodd): ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Integer option = 0;
        String path = "";
        try {
            path = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBReader.getInstance().setDatabaseDir(path);

        System.out.println("\nInsert number of folder in database (example: tctodd1, tctodd2): ");

        try {
            option = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBReader.getInstance().setFoldersNum(option);
    }

    private void identifyGestureMenu() {
    }
}
