package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

/**
 * Created by knoweat on 14/04/14.
 */
public class NNFactory {
    public static NeuralNetwork getNeuralNetworkModel1() {
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        neuralNetwork.addInputLayer(Intel.getInstance().getNrSampleDataSources());

        int[][] parents = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}};
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

        neuralNetwork.addInputLayer(Intel.getInstance().getNrSampleDataSources());

        int[][] parents_1 = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}};

        try {
            // first hidden layer
            neuralNetwork.addLayer(10, parents_1);

            // output layer
            int nrOutputs = Intel.getInstance().getNrUniqueTerms();
            neuralNetwork.addLayer(nrOutputs, getOutputParents(10, nrOutputs));
        } catch (NeuralNetwork.InvalidLayerException e) {
            e.printStackTrace();
        }

        neuralNetwork.finalize();

        return neuralNetwork;
    }

    public static NeuralNetwork getNeuralNetworkModel3() {
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        neuralNetwork.addInputLayer(Intel.getInstance().getNrSampleDataSources());

        int[][] parents_1 = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}};
        int[][] parents_2 = {{0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6}};
        int[][] parents_3 = {{0, 1, 2, 3, 4}};
        try {
            neuralNetwork.addLayer(7, parents_1);
            neuralNetwork.addLayer(5, parents_2);
            neuralNetwork.addLayer(1, parents_3);
        } catch (NeuralNetwork.InvalidLayerException e) {
            e.printStackTrace();
        }

        neuralNetwork.finalize();

        return neuralNetwork;
    }

    private static int[][] getOutputParents(int lastLayerSize, int nrOutputs) {
        int[][] outputParents = new int[nrOutputs][lastLayerSize];
        for (int i = 0; i < nrOutputs; i++) {
            for (int j = 0; j < lastLayerSize; j++) {
                outputParents[i][j] = j;
            }
        }

        return outputParents;
    }
}
