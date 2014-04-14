package mieic.iart.SignLangNN.database;

/**
 * Created by knoweat on 07/04/14.
 */
public class Database {

    private static Database dInstance = null;

    public Database getInstance() {
        if (dInstance == null) {
            return new Database();
        }
        else {
            return dInstance;
        }
    }
}
