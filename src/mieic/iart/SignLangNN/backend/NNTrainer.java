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

        for (Sample s : samples) {
            try {
                double[] expectedResult = new double[Intel.getInstance().getNrUniqueTerms()];
                expectedResult[Intel.getInstance().getSampleIndex(s.getName())] = 1;
                network.train(s.getAverageGesture(), expectedResult);
            } catch (NeuralNetwork.InvalidSampleException e) {
                e.printStackTrace();
            } catch (NeuralNetwork.InitializedNetworkException e) {
                System.err.println("Error parsing sample: " + s.getName());
            }
        }

    }
}
