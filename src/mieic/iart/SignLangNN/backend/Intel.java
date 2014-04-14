package mieic.iart.SignLangNN.backend;

import java.util.ArrayList;

/**
 * Created by knoweat on 07/04/14.
 */
public class Intel {

    private static Intel sInstance = null;
    private ArrayList<Sample> samples;

    private Intel() {
        samples = new ArrayList<>();
    }

    public static Intel getInstance() {
        if (sInstance == null) {
            sInstance = new Intel();
        }
        return sInstance;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public void addSamples(Sample sample) {
        samples.add(sample);
    }
}
