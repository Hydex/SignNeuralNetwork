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
        boolean networkFailing = true;
        int i = 0;

        long start = System.currentTimeMillis();

        System.out.println("Training...");
        while (networkFailing) {
            i++;
            int j = 0;
            networkFailing = false;
            for (Sample s : samples) {
                try {
                    double[] expectedResult = new double[Intel.getInstance().getNrUniqueTerms()];
                    expectedResult[Intel.getInstance().getSampleIndex(s.getName()) - 1] = 1;
                    network.train(s.getAverageGesture(), expectedResult);

                    double[] result = network.feedForward(s.getAverageGesture());
                    if (!Intel.getInstance().getNearestRecord(result, true).equals(s.getName())) {
                        networkFailing = true;
                        j++;
                    }

                } catch (NeuralNetwork.InvalidSampleException e) {
                    e.printStackTrace();
                } catch (NeuralNetwork.InitializedNetworkException e) {
                    System.err.println("Error parsing sample: " + s.getName());
                }
            }
            System.out.println("Session: " + i + " errors: " + j);
        }

        long end = System.currentTimeMillis();

        System.out.println("Trained " + i + " times.");
        System.out.println("Elapsed " + ((float) (end - start) / 1000.0) + " seconds.");
    }
}
