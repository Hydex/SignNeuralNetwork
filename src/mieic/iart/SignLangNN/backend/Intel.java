package mieic.iart.SignLangNN.backend;

import mieic.iart.SignLangNN.database.DBReader;
import mieic.iart.SignLangNN.frontend.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by knoweat on 07/04/14.
 */
public class Intel {

    private static Intel sInstance = null;
    private ArrayList<Sample> samples;
    private HashMap<String, Float> results;

    private Intel() {
        samples = new ArrayList<>();
        results = new HashMap<>();
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

    public String getNearestRecord(float estimatedHash) {
        float lowestError = Float.MAX_VALUE;
        String bestBet = "none";
        for (int i = 0; i < results.size(); i++) {
            float error = Math.abs(estimatedHash - (float) results.values().toArray()[i]);
            if (error < lowestError) {
                lowestError = error;
                bestBet = (String) results.keySet().toArray()[i];
            }
        }

        Log.log("Bet: " + bestBet + "\nError: " + lowestError);
        return bestBet;
    }
}
