package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.database.DBReader;
import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

import java.util.ArrayList;

/**
 * Created by knoweat on 07/04/14.
 */
public class Intel {

    private static Intel sInstance = null;
    private ArrayList<Sample> samples;
    private ArrayList<String> uniqueTerms;

    private Intel() {
        samples = new ArrayList<>();
        uniqueTerms = new ArrayList<>();
    }

    public static Intel getInstance() {
        if (sInstance == null) {
            sInstance = new Intel();
        }
        return sInstance;
    }

    public void readDatabase() {
        DBReader.getInstance().read();

        for (Sample s : samples) {
            boolean exists = false;
            for (String name : uniqueTerms) {
                if (name.equals(s.getName())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                uniqueTerms.add(s.getName());
            }
        }
    }

    public void readAdditionalDatabase(NeuralNetwork nn) throws NeuralNetwork.InitializedNetworkException {
        DBReader.getInstance().read();

        for (Sample s : samples) {
            boolean exists = false;
            for (String name : uniqueTerms) {
                if (name.equals(s.getName())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                uniqueTerms.add(s.getName());
                if (!DBReader.getInstance().isOriginalDatabase()) {
                    nn.addOutputNeuron();
                }
            }
        }
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public void addSamples(Sample sample) {
        samples.add(sample);
    }

    public int getSampleIndex(String name) {
        for (int i = 0; i < uniqueTerms.size(); i++) {
            if (uniqueTerms.get(i).equals(name)) {
                return i + 1;
            }
        }
        return 0;
    }

    public int getNrUniqueTerms() {
        return uniqueTerms.size();
    }

    public int getNrSampleDataSources() {
        return Sample.DATA_SOURCES_NR;
    }

    public String getNearestRecord(double[] result, boolean isTraining) {
        if (result.length != uniqueTerms.size()) {
            return null;
        }

        double highestProb = 0.0;
        double secondBest = 0.0;
        String bestBet = null;
        int size = result.length;
        for (int i = 0; i < size; i++) {
            if (result[i] > highestProb) {
                bestBet = uniqueTerms.get(i);
                secondBest = highestProb;
                highestProb = result[i];
            }
        }

        if (isTraining && highestProb - secondBest < 0.3) {
            return "";
        } else {
            return bestBet;
        }
    }
}
