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
    private boolean isOriginalDatabase = true;

    public static DBReader getInstance() {
        if (reader == null) {
            reader = new DBReader();
        }

        return reader;
    }

    public boolean isOriginalDatabase() {
        return isOriginalDatabase;
    }

    public void read() {
        String gesturePath;

        for (int i = 1; i <= foldersNum; i++) {
            gesturePath = databaseDir + i;

            File gestureFolder = new File(gesturePath);

            for (final File gesture : gestureFolder.listFiles()) {
                if (gesture.isFile()) {
                    readSample(gesture);
                }

            }
        }

        if (isOriginalDatabase) {
            isOriginalDatabase = false;
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

        while (in.hasNextDouble()) {
            readHandGesture(sample, in);
        }

        Intel.getInstance().addSamples(sample);
    }

    public void readHandGesture(Sample sample, Scanner in) {
        HandGesture hand = new HandGesture();
        hand.setX(in.nextDouble());
        hand.setY(in.nextDouble());
        hand.setZ(in.nextDouble());
        hand.setRoll(in.nextDouble());
        hand.setPitch(in.nextDouble());
        hand.setYaw(in.nextDouble());
        hand.setThumbBend(in.nextDouble());
        hand.setForefingerBend(in.nextDouble());
        hand.setMiddlefingerBend(in.nextDouble());
        hand.setRingfingerBend(in.nextDouble());
        hand.setLittlefingerBend(in.nextDouble());

        sample.addLeftGesture(hand);

        hand = new HandGesture();
        hand.setX(in.nextDouble());
        hand.setY(in.nextDouble());
        hand.setZ(in.nextDouble());
        hand.setRoll(in.nextDouble());
        hand.setPitch(in.nextDouble());
        hand.setYaw(in.nextDouble());
        hand.setThumbBend(in.nextDouble());
        hand.setForefingerBend(in.nextDouble());
        hand.setMiddlefingerBend(in.nextDouble());
        hand.setRingfingerBend(in.nextDouble());
        hand.setLittlefingerBend(in.nextDouble());

        sample.addRightGesture(hand);
        sample.processGesture();
    }

    public String getDatabaseDir() {
        return databaseDir;
    }

    public void setDatabaseDir(String databaseDir) {
        this.databaseDir = databaseDir;
    }

    public Integer getFoldersNum() {
        return foldersNum;
    }

    public void setFoldersNum(Integer foldersNum) {
        this.foldersNum = foldersNum;
    }
}
