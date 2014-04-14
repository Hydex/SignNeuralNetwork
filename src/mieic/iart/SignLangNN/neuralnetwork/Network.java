package mieic.iart.SignLangNN.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoweat on 09/04/14.
 */
public class Network {

    static final float LEARNING_RATE = (float) 0.5; // 0 <= value <= 1

    private List<List<Node>> nodes;
    private List<Edge> edges;

    private boolean isInitialized;

    public Network() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        isInitialized = false;
    }

    public void clearNetwork() {
        nodes.clear();
        isInitialized = false;
    }

    public void addInputLayer(int nr) {
        nodes.add(0, new ArrayList<Node>(nr));
    }


    /*
        Adds a new layer to the network. The last one added before call to finishNetwork will be the output layer
     */
    public void addLayer(int nr, int[][] parents) throws InvalidLayerException {
        if (isInitialized) {
            return;
        }
        if (nr != parents.length || nodes.size() < 1) {
            throw new InvalidLayerException();
        }

        nodes.add(new ArrayList<Node>(nr));

        int i = 0;
        for (Node node : nodes.get(nodes.size() - 1)) {
            for (int j = 0; j < parents[i].length; j++) {
                Node parent = nodes.get(nodes.size() - 2).get(parents[i][j]);

                Edge edge = new Edge(parent, node);
                parent.addDestinationEdge(edge);
                node.addSourceEdge(edge);
                edges.add(edge);
            }
            i++;
        }

    }

    public void finnalize() {
        isInitialized = true;
    }

    public void train(float[] sample, float[] expectedResults) throws InvalidSampleException {
        if (sample.length != nodes.get(0).size()
                || expectedResults.length != nodes.get(nodes.size() - 1).size()) {
            throw new InvalidSampleException();
        }

        float[] currentResults = feedForward(sample);
        feedBackward(expectedResults, currentResults);

    }

    private float[] feedForward(float[] sample) {
        // input neurons
        int i = 0;
        for (Node n : nodes.get(0)) {
            n.setValue(sample[i]);
            n.sendValue();

            i++;
        }

        // middle-layer neurons
        for (int j = 1; i < nodes.size() - 1; i++) {
            List<Node> currentNodes = nodes.get(i);
            for (Node n : currentNodes) {
                n.receiveValue();
                n.sendValue();
            }
        }

        // output neurons
        float[] retVals = new float[nodes.get(nodes.size() - 1).size()];
        int k = 0;
        for (Node n: nodes.get(nodes.size() - 1)) {
            n.receiveValue();
            retVals[k] = n.getOutput();
            k++;
        }

        return retVals;
    }

    private void feedBackward(float[] expectedResults, float[] currentResults) {
        int size = expectedResults.length;
        float[] outputErrors = new float[size];

        for (int i = 0; i < size; i++) {
            outputErrors[i] = expectedResults[i] - currentResults[i];
        }

        // output layer
        int i = 0;
        for (Node n: nodes.get(nodes.size() - 1)) {
            n.setError(outputErrors[i]);
            n.backPropagateError();
            i++;
        }

        // middle layers
        for (int j = nodes.size() - 1; j > 0; j--) {
            List<Node> currentNodes = nodes.get(j);
            for (Node n: currentNodes) {
                n.calcError();
                n.backPropagateError();
            }
        }

        // input layer
        for(Node n: nodes.get(0)) {
            n.calcError();
        }

        // update network
        for (Edge e: edges) {
            e.updateWeight();
        }
    }

    public static class InvalidLayerException extends Exception {
    }

    public static class InvalidSampleException extends Exception {
    }
}
