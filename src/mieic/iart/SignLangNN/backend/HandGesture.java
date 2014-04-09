package mieic.iart.SignLangNN.backend;

/**
 * Created by knoweat on 07/04/14.
 */
public class HandGesture {
    /*
     * x position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private float x = 0;

    /*
     * y position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private float y = 0;

    /*
     * z position expressed relative to a zero point set slightly below the chin. Expressed in meters.
     */
    private float z = 0;

    /*
     * roll expressed as a value between -0.5 and 0.5 with 0 being palm down. Positive means the palm is rolled clockwise from the perspective of the signer. To get degrees, multiply by 180.
     */
    private float roll = 0;

    /*
     * pitch expressed as a value between -0.5 and 0.5 with 0 being palm flat (horizontal). Positive means the palm is pointing up. To get degrees, multiply by 180.
     */
    private float pitch = 0;

    /*
     * yaw expressed a value between -1.0 and 1.0 with 0 being palm straight ahead from the perspective of the signer. Positive means clockwise from the perspective above the signer. To get degrees, multiply by 180.
     */
    private float yaw = 0;

    /*
     * Thumb bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private float thumbBend = 0;

    /*
     * Forefinger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private float forefingerBend = 0;

    /*
     * Middle finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private float middlefingerBend = 0;

    /*
     * Ring finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private float ringfingerBend = 0;

    /*
     * Little finger bend measure between 0 and 1. 0 means totally flat, 1 means totally bent. However, the finger bend measurements are not very exact.
     */
    private float littlefingerBend = 0;

    public HandGesture(float x, float y, float z, float roll, float pitch, float yaw, float thumbBend, float forefingerBend, float middlefingerBend,
                       float ringfingerBend, float littlefingerBend) {
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getThumbBend() {
        return thumbBend;
    }

    public float getForefingerBend() {
        return forefingerBend;
    }

    public float getMiddlefingerBend() {
        return middlefingerBend;
    }

    public float getRingfingerBend() {
        return ringfingerBend;
    }

    public float getLittlefingerBend() {
        return littlefingerBend;
    }
}
