package mieic.iart.SignLangNN.neuralnetwork;

import mieic.iart.SignLangNN.frontend.Log;

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
        Log.log("Weight set to " + weight);
    }

    public void inflowValue(float val) {
        savedValue = val;
        Log.log("Value received from previous node: " + val);
    }

    public float outflowValue() {
        float outflowValue = savedValue * weight;
        Log.log("Sent value to next node: " + outflowValue);
        return outflowValue;
    }

    // training functions

    public void setError(float error) {
        this.error = error;
    }

    public float backPropagateError() {
        float weightedError = error * weight;
        Log.log("Feeding-backward the error. Value: " + weightedError);
        return weightedError;
    }

    public void updateWeight() {
        float oldWeight = weight;
        weight = weight + NeuralNetwork.LEARNING_RATE * destinationNode.getError() * (float) (Math.exp(sourceNode.getOutput())
                / Math.pow(Math.exp(sourceNode.getOutput()) + 1, 2.0));

        Log.log("Weight changed from " + oldWeight + " to " + weight);
    }
}
