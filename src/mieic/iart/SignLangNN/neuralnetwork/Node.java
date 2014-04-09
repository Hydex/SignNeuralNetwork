package mieic.iart.SignLangNN.neuralnetwork;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoweat on 09/04/14.
 */
public class Node {
    private float value;

    private List<Edge> sourceEdges;
    private List<Edge> destinationEdges;

    public Node() {
        sourceEdges = new ArrayList<>();
        destinationEdges = new ArrayList<>();
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

    

}
