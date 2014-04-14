package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.database.DBReader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by knoweat on 07/04/14.
 */
public class Intel {

    private static Intel sInstance = null;
    private ArrayList<Sample> samples;
    private HashMap<String, Integer> results;

    private Intel() {
        samples = new ArrayList<>();
    }

    public static Intel getInstance() {
        if (sInstance == null) {
            sInstance = new Intel();
        }
        return sInstance;
    }

    public void readDatabase() {
        DBReader.getInstance().read();

        for (int i=0; i < samples.size(); i++) {
            results.put(samples.get(i).getName(), samples.get(i).processName());
        }
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public void addSamples(Sample sample) {
        samples.add(sample);
    }
}
