package mieic.iart.SignLangNN.neuralnetwork;

import mieic.iart.SignLangNN.frontend.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by knoweat on 09/04/14.
 */
public class NeuralNetwork implements Serializable {

    static final double LEARNING_RATE = 0.3; // 0 <= value <= 1
    public static final String NEURALNET_SER = "neuralnet.ser";

    private List<List<Node>> nodes;
    private List<Edge> edges;

    private boolean isInitialized;

    public NeuralNetwork() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        isInitialized = false;

        Log.log("Empty neural network created");
    }

    public void clearNetwork() {
        nodes.clear();
        edges.clear();
        isInitialized = false;

        Log.log("NeuralNetwork cleared");
    }

    public void addInputLayer(int nr) {
        ArrayList<Node> newLayer = new ArrayList<>();

        for (int i = 0; i < nr; i++) {
            newLayer.add(new Node());
        }

        nodes.add(0, newLayer);

        Log.log("Created " + nr + " input neurons.");
    }


    /*
        Adds a new layer to the network. The last one added before call to finishNetwork will be the output layer
     */
    public void addLayer(int nr, int[][] parents) throws InvalidLayerException {

        if (isInitialized) {
            System.err.println("Netork already closed. Please clear it before adding layers");
            return;
        }
        if (nr != parents.length) {
            System.err.println("Invalid arguments. Please match size of parents with nr.");
            throw new InvalidLayerException();
        }

        if (nodes.size() < 1) {
            System.err.println("Please first add a input layer.");
            throw new InvalidLayerException();
        }

        ArrayList<Node> newLayer = new ArrayList<>();

        for (int i = 0; i < nr; i++) {
            newLayer.add(new Node());
        }

        nodes.add(newLayer);

        Log.log("Middle layer with " + nr + " neurons added.");

        int i = 0;
        for (Node node : nodes.get(nodes.size() - 1)) {
            for (int j = 0; j < parents[i].length; j++) {
                Node parent = nodes.get(nodes.size() - 2).get(parents[i][j]);

                Edge edge = new Edge(parent, node);
                edges.add(edge);

                Log.log("Added edge from [" + (nodes.size() - 2) + "][" + parents[i][j] + "] to ["
                        + (nodes.size() - 1) + "][" + i + "]");
            }
            i++;
        }

    }

    public void finalize() {
        isInitialized = true;

        Log.log("NeuralNetwork finalized");
    }

    public void train(double[] sample, double[] expectedResults) throws InvalidSampleException, InitializedNetworkException {

        // TODO: remove
        Log.log("NeuralNetwork size: " + nodes.size());

        if (!isInitialized) {
            Log.log("NeuralNetwork not properly initialized.");
            throw new InitializedNetworkException();
        }

        if (sample.length != nodes.get(0).size()) {
            Log.log("Sample size does not correspond to the size of the input neuron layer.");
            throw new InvalidSampleException();
        }

        if (expectedResults.length != nodes.get(nodes.size() - 1).size()) {
            Log.log("Expected results array size do not correspond to the size of the output neurons layer.\n");
            throw new InvalidSampleException();
        }

        double[] currentResults = feedForward(sample);
        feedBackward(expectedResults, currentResults);

        // update network
        for (Edge e : edges) {
            e.updateWeight();
        }

    }

    public double[] feedForward(double[] sample) {

        Log.log("Feeding-forward sample" + Arrays.toString(sample) + ".");

        // input neurons
        int i = 0;
        for (Node n : nodes.get(0)) {
            n.setValue(sample[i]);
            n.sendValue();

            i++;
        }

        // middle-layer neurons
        for (int j = 1; j < nodes.size() - 1; j++) {
            List<Node> currentNodes = nodes.get(j);
            for (Node n : currentNodes) {
                n.receiveValue();
                n.sendValue();
            }
        }

        // output neurons
        double[] retVals = new double[nodes.get(nodes.size() - 1).size()];
        int k = 0;
        for (Node n : nodes.get(nodes.size() - 1)) {
            n.receiveValue();
            retVals[k] = n.getOutput();
            k++;
        }

        Log.log("Result values " + Arrays.toString(retVals) + ".");

        return retVals;
    }

    private void feedBackward(double[] expectedResults, double[] currentResults) {

        Log.log("Feeding-backward with expected values: " + Arrays.toString(expectedResults));

        int size = expectedResults.length;
        double[] outputErrors = new double[size];

        for (int i = 0; i < size; i++) {
            outputErrors[i] = /*currentResults[i] * (1 - currentResults[i]) * */(expectedResults[i] - currentResults[i]);
            Log.log("Error " + i + ": " + outputErrors[i]);
        }

        // output layer
        int i = 0;
        for (Node n : nodes.get(nodes.size() - 1)) {
            n.setError(outputErrors[i]);
            n.backPropagateError();
            i++;
        }

        // middle layers
        for (int j = nodes.size() - 2; j > 0; j--) {
            List<Node> currentNodes = nodes.get(j);
            for (Node n : currentNodes) {
                n.calcError();
                n.backPropagateError();
            }
        }
    }


    public void addOutputNeuron() throws InitializedNetworkException {
        if (!isInitialized) {
            throw new InitializedNetworkException();
        }

        Node newOutputNode = new Node();
        nodes.get(nodes.size() - 1).add(newOutputNode);

        // add parent edges to recently added node
        for (Node parent: nodes.get(nodes.size() - 2)) {
            Edge e = new Edge(newOutputNode, parent);
            edges.add(e);
        }
    }

    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(NEURALNET_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();

            Log.log("Database Saved");
        } catch (IOException i) {
            Log.log("Could not save database");
        }
    }

    public static class InvalidLayerException extends Exception {
    }

    public static class InvalidSampleException extends Exception {
    }

    public static class InitializedNetworkException extends Exception {
    }
}
