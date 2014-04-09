package mieic.iart.SignLangNN.neuralnetwork;

/**
 * Created by knoweat on 09/04/14.
 */
public class Edge {

    private Node sourceNode;
    private Node destinationNode;

    private float weight;

    public Edge(Node source, Node destination) {
        this.sourceNode = source;
        this.destinationNode = destination;
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

}
