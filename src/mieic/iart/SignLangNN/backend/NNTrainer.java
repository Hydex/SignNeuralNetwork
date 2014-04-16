package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

import java.util.ArrayList;

/**
 * Created by knoweat on 14/04/14.
 */
public class NNTrainer {

    private NeuralNetwork network;

    public NNTrainer(NeuralNetwork n) {
        network = n;
    }

    public void trainNN(ArrayList<Sample> samples) {
        for (Sample s: samples) {
            try {
                network.train(s.getAverageGesture(), new double[] {s.getHash()});
            } catch (NeuralNetwork.InvalidSampleException e) {
                e.printStackTrace();
            } catch (NeuralNetwork.InitializedNetworkException e) {
                System.err.println("Error parsing sample: " + s.getName());
            }
        }

    }
}
