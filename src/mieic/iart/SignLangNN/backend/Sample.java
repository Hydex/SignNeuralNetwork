package mieic.iart.SignLangNN.backend;

import java.util.ArrayList;

/**
 * Created by knoweat on 07/04/14.
 */
public class Sample {

    String name;
    ArrayList<HandGesture> rightHandGestures;
    ArrayList<HandGesture> leftHandGestures;

    public Sample(String newName) {
        name = newName;
        rightHandGestures = new ArrayList<>();
        leftHandGestures = new ArrayList<>();
    }

    public void addLeftGesture(HandGesture gesture) {
        leftHandGestures.add(gesture);
    }

    public void addRightGesture(HandGesture gesture) {
        rightHandGestures.add(gesture);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<HandGesture> getRightHandGestures() {
        return rightHandGestures;
    }

    public void setRightHandGestures(ArrayList<HandGesture> rightHandGestures) {
        this.rightHandGestures = rightHandGestures;
    }

    public ArrayList<HandGesture> getLeftHandGestures() {
        return leftHandGestures;
    }

    public void setLeftHandGestures(ArrayList<HandGesture> leftHandGestures) {
        this.leftHandGestures = leftHandGestures;
    }
}
