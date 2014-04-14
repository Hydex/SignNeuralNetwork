package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.neuralnetwork.Network;

import java.util.ArrayList;

/**
 * Created by knoweat on 14/04/14.
 */
public class NNTrainer {

    private Network network;

    public NNTrainer(Network n) {
        network = n;
    }

    public void trainNN(ArrayList<Sample> samples) {

        for (Sample s: samples) {
            try {
                network.train(s.getAverageGesture(), new float[] {s.getHash()});
            } catch (Network.InvalidSampleException e) {
                e.printStackTrace();
            } catch (Network.InitializedNetworkException e) {
                System.err.println("Error parsing sample: " + s.getName());
            }
        }

    }
}
