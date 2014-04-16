package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

/**
 * Created by knoweat on 14/04/14.
 */
public class NNFactory {
    public static NeuralNetwork getNeuralNetworkModel1() {
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        neuralNetwork.addInputLayer(22);

        int[][] parents = {{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21}};
        try {
            neuralNetwork.addLayer(1, parents);
        } catch (NeuralNetwork.InvalidLayerException e) {
            e.printStackTrace();
        }

        neuralNetwork.finalize();

        return neuralNetwork;
    }

    public static NeuralNetwork getNeuralNetworkModel2() {
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        neuralNetwork.addInputLayer(22);

        int[][] parents_1 = {{0,1,2,3,4,5,6,7,8,9,10,11},{12,13,14,15,16,17,18,19,20,21}};
        int[][] parents_2 = {{0, 1}};
        try {
            neuralNetwork.addLayer(2, parents_1);
            neuralNetwork.addLayer(1, parents_2);
        } catch (NeuralNetwork.InvalidLayerException e) {
            e.printStackTrace();
        }

        neuralNetwork.finalize();

        return neuralNetwork;
    }
}
