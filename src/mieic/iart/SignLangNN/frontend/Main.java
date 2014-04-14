package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.neuralnetwork.Network;

/**
 * Created by wso277 on 4/14/14.
 */
public class Main {

    public static void main(String[] args) {

        Intel.getInstance().readDatabase();

        Network neuralNetwork = new Network();

        neuralNetwork.addInputLayer(22);

        int[][] parents_1 = {{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21}};
        try {
            neuralNetwork.addLayer(1, parents_1);
        } catch (Network.InvalidLayerException e) {
            e.printStackTrace();
        }

        neuralNetwork.finalize();

        float[] expected = {Intel.getInstance().getSamples().get(0).getHash()};
        try {
            neuralNetwork.train(Intel.getInstance().getSamples().get(0).getAverageGesture(), expected);
        } catch (Network.InvalidSampleException e) {
            e.printStackTrace();
        } catch (Network.InitializedNetworkException e) {
            e.printStackTrace();
        }

        new Cli().menu(neuralNetwork);
    }

}
