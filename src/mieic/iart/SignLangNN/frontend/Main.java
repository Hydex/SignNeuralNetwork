package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.backend.NNFactory;
import mieic.iart.SignLangNN.backend.NNTrainer;
import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by wso277 on 4/14/14.
 */
public class Main {

    private static NeuralNetwork neuralNetwork;

    public static void main(String[] args) {

        Intel.getInstance().readDatabase();

        loadNN();
        neuralNetwork.saveToFile();

        new Cli().menu(neuralNetwork);
    }

    private static void loadNN() {
        try {
            FileInputStream fileIn = new FileInputStream(NeuralNetwork.NEURALNET_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                neuralNetwork = (NeuralNetwork) in.readObject();
            } catch (ClassNotFoundException e) {

                System.out.println("Error while reading from saved neural network. Starting fresh");

                neuralNetwork = NNFactory.getNeuralNetworkModel3();

                NNTrainer trainer = new NNTrainer(neuralNetwork);
                trainer.trainNN(Intel.getInstance().getSamples());
            }

            System.out.println("Loaded Neural Network");

            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {

            System.out.println("Fresh database");

            neuralNetwork = NNFactory.getNeuralNetworkModel3();

            NNTrainer trainer = new NNTrainer(neuralNetwork);
            trainer.trainNN(Intel.getInstance().getSamples());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
