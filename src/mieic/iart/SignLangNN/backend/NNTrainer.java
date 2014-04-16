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
        try {
            network.train(samples.get(0).getAverageGesture(), new float[] {samples.get(0).getHash()});
        } catch (NeuralNetwork.InvalidSampleException e) {
            e.printStackTrace();
        } catch (NeuralNetwork.InitializedNetworkException e) {
            System.err.println("Error parsing sample: " + samples.get(0).getName());
        }
        /*for (Sample s: samples) {
            try {
                network.train(s.getAverageGesture(), new float[] {s.getHash()});
            } catch (NeuralNetwork.InvalidSampleException e) {
                e.printStackTrace();
            } catch (NeuralNetwork.InitializedNetworkException e) {
                System.err.println("Error parsing sample: " + s.getName());
            }
        }*/

    }
}
