package mieic.iart.SignLangNN.neuralnetwork;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import mieic.iart.SignLangNN.frontend.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoweat on 09/04/14.
 */
public class Node {
    private float value;
    private float error;

    private List<Edge> sourceEdges;
    private List<Edge> destinationEdges;

    public Node() {
        sourceEdges = new ArrayList<>();
        destinationEdges = new ArrayList<>();
        value = 0;
        error = 0;
    }

    public boolean addSourceEdge(Edge edge) {
        for (Edge edgeIt : sourceEdges) {
            if (edgeIt.getSourceNode() == edge.getSourceNode()) {
                // already added
                return false;
            }
        }

        sourceEdges.add(edge);
        return true;
    }

    public boolean addDestinationEdge(Edge edge) {
        for (Edge edgeIt : destinationEdges) {
            if (edgeIt.getDestinationNode() == edge.getDestinationNode()) {
                // already added
                return false;
            }
        }

        destinationEdges.add(edge);
        return true;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        Log.log("Node's value set to: " + value);
    }

    public void receiveValue() {
        float weightedSum = 0;

        for (Edge e: sourceEdges) {
            weightedSum += e.outflowValue();
        }

        Log.log("Nodes value resulting from weighted edges values: " + weightedSum);

        value = weightedSum;
    }

    public float getOutput() {
        float feedVal = (float) (1.0 / (1.0 + Math.exp(- value)));
        return feedVal;
    }

    public void sendValue() {
        for (Edge e: destinationEdges) {
            e.inflowValue(getOutput());
        }
    }


    // training functions
    public void setError(float error) {
        this.error = error;
        Log.log("Error set to: " + error);
    }

    public void calcError() {
        float weightedSum = 0;

        for (Edge e: destinationEdges) {
            weightedSum += e.backPropagateError();
        }

        Log.log("Error resulting from weighted edges errors set to: " + weightedSum);
        error = weightedSum;
    }

    public float getError() {
        return error;
    }

    public void backPropagateError() {
        for (Edge e: sourceEdges) {
            e.setError(error);
        }
    }
}
