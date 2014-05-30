package mieic.iart.SignLangNN.backend;

/**
 * Created by knoweat on 07/04/14.
 */
public class HandGesture {
    /*
     * x position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private double x = 0;

    /*
     * y position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private double y = 0;

    /*
     * z position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private double z = 0;

    /*
     * roll expressed as a value between -0.5 and 0.5 with 0 being palm down. Positive means the palm is rolled clockwise from the perspective of the signer. To get degrees, multiply by 180.
     */
    private double roll = 0;

    /*
     * pitch expressed as a value between -0.5 and 0.5 with 0 being palm flat (horizontal). Positive means the palm is pointing up. To get degrees, multiply by 180.
     */
    private double pitch = 0;

    /*
     * yaw expressed a value between -1.0 and 1.0 with 0 being palm straight ahead from the perspective of the signer. Positive means clockwise from the perspective above the signer. To get degrees, multiply by 180.
     */
    private double yaw = 0;

    /*
     * Thumb bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private double thumbBend = 0;

    /*
     * Forefinger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private double forefingerBend = 0;

    /*
     * Middle finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private double middlefingerBend = 0;

    /*
     * Ring finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private double ringfingerBend = 0;

    /*
     * Little finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private double littlefingerBend = 0;

    public HandGesture() {

    }

    public HandGesture(double x, double y, double z, double roll, double pitch, double yaw, double thumbBend, double forefingerBend, double middlefingerBend,
                       double ringfingerBend, double littlefingerBend) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        this.thumbBend = thumbBend;
        this.forefingerBend = forefingerBend;
        this.middlefingerBend = middlefingerBend;
        this.ringfingerBend = ringfingerBend;
        this.littlefingerBend = littlefingerBend;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getRoll() {
        return roll;
    }

    public double getPitch() {
        return pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public double getThumbBend() {
        return thumbBend;
    }

    public double getForefingerBend() {
        return forefingerBend;
    }

    public double getMiddlefingerBend() {
        return middlefingerBend;
    }

    public double getRingfingerBend() {
        return ringfingerBend;
    }

    public double getLittlefingerBend() {
        return littlefingerBend;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public void setThumbBend(double thumbBend) {
        this.thumbBend = thumbBend;
    }

    public void setForefingerBend(double forefingerBend) {
        this.forefingerBend = forefingerBend;
    }

    public void setMiddlefingerBend(double middlefingerBend) {
        this.middlefingerBend = middlefingerBend;
    }

    public void setRingfingerBend(double ringfingerBend) {
        this.ringfingerBend = ringfingerBend;
    }

    public void setLittlefingerBend(double littlefingerBend) {
        this.littlefingerBend = littlefingerBend;
    }
}
