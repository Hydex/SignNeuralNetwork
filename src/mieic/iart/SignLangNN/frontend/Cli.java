package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.backend.Sample;
import mieic.iart.SignLangNN.database.DBReader;
import mieic.iart.SignLangNN.neuralnetwork.Network;

import java.io.*;
import java.util.Scanner;

/**
 * Created by wso277 on 4/14/14.
 */
public class Cli {

    public Cli() {
    }

    public void menu(Network network) {

        Integer option = 0;
        while (option != 3) {
            System.out.println("\nSignNeuralNetwork Menu\n");
            System.out.println("1. Load new database");
            System.out.println("2. Identify a sample gesture");
            System.out.println("3. Exit\n\n");
            System.out.print("Option: ");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                try {
                    option = Integer.parseInt(in.readLine());
                    break;
                }catch (NumberFormatException e1) {
                    System.err.println("Invalid input.");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            switch (option) {
                case 1:
                    newDatabaseMenu();
                    break;
                case 2:
                    identifyGestureMenu(network);
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

    private void identifyGestureMenu(Network network) {
        System.out.println("Input the file directory:");
        Scanner consoleScanner = new Scanner(System.in);
        String filePath = consoleScanner.nextLine();

        try {
            Scanner fileScanner = new Scanner(new File(filePath));

            String term = filePath.split("-")[0].trim();

            Sample sample = new Sample(term);

            DBReader.getInstance().readHandGesture(sample, fileScanner);

            float result = network.feedForward(sample.getAverageGesture())[0];

            String bestBet = Intel.getInstance().getNearestRecord(result);

            System.out.println("Neural network result: " + bestBet);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
