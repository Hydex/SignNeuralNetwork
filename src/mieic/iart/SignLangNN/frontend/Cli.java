package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.backend.NNTrainer;
import mieic.iart.SignLangNN.backend.Sample;
import mieic.iart.SignLangNN.database.DBReader;
import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by wso277 on 4/14/14.
 */
public class Cli {

    public Cli() {
    }

    public void menu(NeuralNetwork network) {

        Integer option = 0;
        while (option != 4) {
            System.out.println("\nSignNeuralNetwork Menu\n");
            System.out.println("1. Load new database");
            System.out.println("2. Identify a sample gesture");
            System.out.println("3. Test neural network");
            System.out.println("4. Exit\n\n");
            System.out.print("Option: ");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try {
                    option = Integer.parseInt(in.readLine());
                    break;
                } catch (NumberFormatException e1) {
                    System.err.println("Invalid input.");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            switch (option) {
                case 1:
                    newDatabaseMenu(network);
                    break;
                case 2:
                    identifyGestureMenu(network);
                    break;
                case 3:
                    testNeuralNetworkProcess(network);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Option. Try again.\n");
                    break;
            }
        }
    }

    private void testNeuralNetworkProcess(NeuralNetwork network) {

        int totalTest = 0, successTest = 0;

        System.out.print("\nInsert path to test folder: ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String path = "";
        try {
            path = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(path);

        if (f.exists() && f.isDirectory()) {

            for (final File gesture : f.listFiles()) {
                Sample sample = null;
                if (gesture.isFile()) {
                    String gestureName = gesture.getName().split("-")[0].trim();
                    sample = new Sample(gestureName);

                    Scanner input = null;
                    try {
                        input = new Scanner(gesture);
                        input.useLocale(Locale.US);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    while (input.hasNextDouble()) {
                        DBReader.getInstance().readHandGesture(sample, input);
                    }

                }

                if (sample != null) {

                    double[] result = network.feedForward(sample.getAverageGesture());

                    String bestBet = Intel.getInstance().getNearestRecord(result, false);

                    if (bestBet.equals(sample.getName())) {
                        successTest++;
                    }

                    totalTest++;
                }

            }
        } else {
            System.err.println("Not a valid path");
        }

        System.out.println("The neural network has a " + successTest * 100.0 / (float) totalTest + "% success rate.");

    }

    private void newDatabaseMenu(NeuralNetwork network) {
        // TODO: verify thing with folders

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

        try {
            Intel.getInstance().readAdditionalDatabase(network);
        } catch (NeuralNetwork.InitializedNetworkException e) {
            e.printStackTrace();
        }

        NNTrainer trainer = new NNTrainer(network);

        trainer.trainNN(Intel.getInstance().getSamples());

    }

    private void identifyGestureMenu(NeuralNetwork network) {
        System.out.println("Input the file directory:");
        Scanner consoleScanner = new Scanner(System.in);
        String filePath = consoleScanner.nextLine();

        try {
            Scanner fileScanner = new Scanner(new File(filePath));

            String term = filePath.split("-")[0].trim();

            Sample sample = new Sample(term);

            DBReader.getInstance().readHandGesture(sample, fileScanner);

            double[] result = network.feedForward(sample.getAverageGesture());

            String bestBet = Intel.getInstance().getNearestRecord(result, false);

            System.out.println("Neural network result: " + bestBet);

        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist.");
        }
    }
}
