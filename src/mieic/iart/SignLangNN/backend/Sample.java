package mieic.iart.SignLangNN.backend;

import java.util.ArrayList;

/**
 * Created by knoweat on 07/04/14.
 */
public class Sample {

    private String name;
    private Double hash;
    private ArrayList<HandGesture> rightHandGestures;
    private ArrayList<HandGesture> leftHandGestures;
    private double[] averageGesture;

    public Sample(String newName) {
        name = newName;
        rightHandGestures = new ArrayList<>();
        leftHandGestures = new ArrayList<>();
        averageGesture = new double[22];
    }

    public Double processName() {
        hash = 7.0;
        for (int i = 0; i < name.length(); i++) {
            hash = hash * 7 + name.charAt(i);
        }

        int decimals = 0;
        double tmpHash = 0.0;
        while(tmpHash > 1) {
            tmpHash /= 10;
            decimals++;
        }

        hash /= Math.pow(10, decimals);

        System.out.println("HASH: " + hash);

        return hash;
    }

    public void processGesture() {
        double[] values = new double[22];

        int i = 0;
        for (; i < leftHandGestures.size(); i++) {
            values[0] += leftHandGestures.get(i).getX();
            values[1] += leftHandGestures.get(i).getY();
            values[2] += leftHandGestures.get(i).getZ();
            values[3] += leftHandGestures.get(i).getRoll();
            values[4] += leftHandGestures.get(i).getPitch();
            values[5] += leftHandGestures.get(i).getYaw();
            values[6] += leftHandGestures.get(i).getThumbBend();
            values[7] += leftHandGestures.get(i).getForefingerBend();
            values[8] += leftHandGestures.get(i).getMiddlefingerBend();
            values[9] += leftHandGestures.get(i).getRingfingerBend();
            values[10] += leftHandGestures.get(i).getLittlefingerBend();

            values[11] += rightHandGestures.get(i).getX();
            values[12] += rightHandGestures.get(i).getY();
            values[13] += rightHandGestures.get(i).getZ();
            values[14] += rightHandGestures.get(i).getRoll();
            values[15] += rightHandGestures.get(i).getPitch();
            values[16] += rightHandGestures.get(i).getYaw();
            values[17] += rightHandGestures.get(i).getThumbBend();
            values[18] += rightHandGestures.get(i).getForefingerBend();
            values[19] += rightHandGestures.get(i).getMiddlefingerBend();
            values[20] += rightHandGestures.get(i).getRingfingerBend();
            values[21] += rightHandGestures.get(i).getLittlefingerBend();
        }

        for (int j = 0; j < averageGesture.length; j++) {
            averageGesture[j] = values[j] / (double) i;
        }
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

    public double[] getAverageGesture() {
        return averageGesture;
    }

    public void setAverageGesture(double[] newMediumGesture) {
        averageGesture = newMediumGesture;
    }

    public Double getHash() {
        return hash;
    }

    public void setHash(Double newHash) {
        hash = newHash;
    }
}
