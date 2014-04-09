package mieic.iart.SignLangNN.backend;

/**
 * Created by knoweat on 07/04/14.
 */
public class Intel {

    private static Intel sInstance = null;

    private Intel() {

    }

    public static Intel getInstance() {
        if (sInstance == null) {
            sInstance = new Intel();
        }
        return sInstance;
    }
}
