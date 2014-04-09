package mieic.iart.SignLangNN.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoweat on 09/04/14.
 */
public class Network {

    private List<List<Node>> nodes;

    private boolean isInitialized;

    public Network() {
        nodes = new ArrayList<>();
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
        if (nr != parents.length || nodes.size() < 1) {
            throw new InvalidLayerException();
        }

        nodes.add(new ArrayList<Node>(nr));

        int i = 0;
        for (Node node: nodes.get(nodes.size() - 1)) {
            for (int j = 0; j < parents[i].length; j++) {
                Node parent = nodes.get(nodes.size() - 2).get(parents[i][j]);

                Edge edge = new Edge(parent, node);
                parent.addDestinationEdge(edge);
                node.addSourceEdge(edge);
            }
            i++;
        }

    }

    public void finnalize() {
        isInitialized = true;
    }

    public void train(int[] sample) throws InvalidSampleException {
        if (sample.length != nodes.get(0).size()) {
            throw  new InvalidSampleException();
        }

        // do your thing

    }

    public static class InvalidLayerException extends Exception {
    }
    public static class InvalidSampleException extends Exception {
    }
}
