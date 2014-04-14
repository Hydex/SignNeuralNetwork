package mieic.iart.SignLangNN.neuralnetwork;

import java.util.Random;

/**
 * Created by knoweat on 09/04/14.
 */
public class Edge {

    private Node sourceNode;
    private Node destinationNode;

    private float weight;
    private float savedValue;
    private float error;

    public Edge(Node source, Node destination) {
        this.sourceNode = source;
        this.destinationNode = destination;

        Random r = new Random();
        weight = r.nextFloat() * 2 - 1;
        error = 0;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(Node sourceNode) {
        this.sourceNode = sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void inflowValue(float val) {
        savedValue = val;
    }

    public float outflowValue() {
        return savedValue * weight;
    }

    // training functions

    public void setError(float error) {
        this.error = error;
    }

    public float backPropagateError() {
        return error * weight;
    }

    public void updateWeight() {
        weight = weight + Network.LEARNING_RATE * destinationNode.getError() * (float) (Math.exp(sourceNode.getOutput())
                / Math.pow(Math.exp(sourceNode.getOutput()) + 1, 2.0));
    }
}
