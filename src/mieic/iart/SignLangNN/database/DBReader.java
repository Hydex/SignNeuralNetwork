package mieic.iart.SignLangNN.database;

import mieic.iart.SignLangNN.backend.HandGesture;
import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.backend.Sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by knoweat on 07/04/14.
 */
public class DBReader {

    private static DBReader reader = null;
    private String databaseDir = "database/tctodd";
    private Integer foldersNum = 9;

    public static DBReader getInstance() {
        if (reader == null) {
            reader = new DBReader();
        }

        return reader;
    }

    public void read() {
        String gesturePath;

        for (int i = 0; i < foldersNum; i++) {
            gesturePath = databaseDir + i;

            File gestureFolder = new File(gesturePath);

            for (final File gesture : gestureFolder.listFiles()) {
                if (gesture.isFile()) {
                    readSample(gesture);
                }

            }
        }
    }

    private void readSample(File gesture) {
        String gestureName = gesture.getName().split("-")[0].trim();
        Sample sample = new Sample(gestureName);

        Scanner in = null;
        try {
            in = new Scanner(gesture);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (in.hasNextFloat()) {
            readHandGesture(sample, in);
        }

        Intel.getInstance().addSamples(sample);
    }

    private void readHandGesture(Sample sample, Scanner in) {
        HandGesture hand = new HandGesture();
        hand.setX(in.nextFloat());
        hand.setY(in.nextFloat());
        hand.setZ(in.nextFloat());
        hand.setRoll(in.nextFloat());
        hand.setPitch(in.nextFloat());
        hand.setYaw(in.nextFloat());
        hand.setThumbBend(in.nextFloat());
        hand.setForefingerBend(in.nextFloat());
        hand.setMiddlefingerBend(in.nextFloat());
        hand.setRingfingerBend(in.nextFloat());
        hand.setLittlefingerBend(in.nextFloat());

        sample.addLeftGesture(hand);

        hand = new HandGesture();
        hand.setX(in.nextFloat());
        hand.setY(in.nextFloat());
        hand.setZ(in.nextFloat());
        hand.setRoll(in.nextFloat());
        hand.setPitch(in.nextFloat());
        hand.setYaw(in.nextFloat());
        hand.setThumbBend(in.nextFloat());
        hand.setForefingerBend(in.nextFloat());
        hand.setMiddlefingerBend(in.nextFloat());
        hand.setRingfingerBend(in.nextFloat());
        hand.setLittlefingerBend(in.nextFloat());

        sample.addRightGesture(hand);
    }
}
